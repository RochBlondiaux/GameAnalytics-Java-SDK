package me.rochblondiaux.gameanalytics;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.rochblondiaux.gameanalytics.adapter.*;
import me.rochblondiaux.gameanalytics.model.event.implementation.*;
import me.rochblondiaux.gameanalytics.request.InitRequest;
import me.rochblondiaux.gameanalytics.response.InitResponse;
import me.rochblondiaux.gameanalytics.utils.GzipUtil;
import me.rochblondiaux.gameanalytics.utils.HmacUtil;
import okhttp3.*;

@Getter
public class GameAnalytics {

    public static final String PRODUCTION_ENDPOINT = "api.gameanalytics.com";
    public static final String SANDBOX_ENDPOINT = "sandbox-api.gameanalytics.com";
    public static final String VERSION = "rest api v2";
    private static final Gson GSON = new GsonBuilder()
            .registerTypeHierarchyAdapter(ResourceEvent.class, new ResourceEventSerializer())
            .registerTypeHierarchyAdapter(BusinessEvent.class, new BusinessEventSerializer())
            .registerTypeHierarchyAdapter(ErrorEvent.class, new ErrorEventSerializer())
            .registerTypeHierarchyAdapter(DesignEvent.class, new DesignEventSerializer())
            .registerTypeHierarchyAdapter(ProgressionEvent.class, new ProgressionEventSerializer())
            .create();

    private final String gameKey;
    private final String secretKey;
    private final String endpoint;

    private final OkHttpClient httpClient;

    public GameAnalytics(String gameKey, String secretKey, Environment environment) {
        this(gameKey, secretKey, environment, new OkHttpClient());
    }

    public GameAnalytics(String gameKey, String secretKey, Environment environment, OkHttpClient httpClient) {
        this.gameKey = gameKey;
        this.secretKey = secretKey;
        this.endpoint = environment.endpoint();
        this.httpClient = httpClient;
    }

    public CompletableFuture<InitResponse> init(int interval, @NotNull InitRequest request) {
        return init(interval, null, request);
    }

    public CompletableFuture<InitResponse> init(int interval, @Nullable String configHash, @NotNull InitRequest request) {
        HttpUrl.Builder url = new HttpUrl.Builder()
                .scheme("https")
                .host(endpoint)
                .addPathSegment("remote_configs")
                .addPathSegment("v1")
                .addPathSegment("init")
                .addQueryParameter("game_key", this.gameKey)
                .addQueryParameter("interval_seconds", String.valueOf(interval));
        if (configHash != null)
            url.addQueryParameter("configs_hash", configHash);

        return sendRequest(url.build(), request, InitResponse.class);
    }

    public CompletableFuture<Void> sendEvents(List<Event> events) {
        if (events.isEmpty())
            return CompletableFuture.completedFuture(null);

        HttpUrl.Builder url = new HttpUrl.Builder()
                .scheme("https")
                .host(endpoint)
                .addPathSegment("v2")
                .addPathSegment(this.gameKey)
                .addPathSegment("events");

        return sendRequest(url.build(), events, Void.class);
    }

    private <T> CompletableFuture<T> sendRequest(HttpUrl url, Object content, Class<T> responseType) {
        String jsonContent = GSON.toJson(content);
        System.out.println("Sending request to " + url + " with content: " + jsonContent);
        return sendRequest(url, jsonContent, responseType);
    }

    private <T> CompletableFuture<T> sendRequest(HttpUrl url, String content, Class<T> responseType) {
        byte[] compressedContent;
        try {
            compressedContent = GzipUtil.gzip(content);
        } catch (Exception e) {
            throw new RuntimeException("Failed to compress content", e);
        }

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", HmacUtil.generateHmac(compressedContent, secretKey))
                .addHeader("Content-Type", "application/json")
                .addHeader("Content-Length", String.valueOf(compressedContent.length))
                .addHeader("Content-Encoding", "gzip")
                .post(RequestBody.create(compressedContent, MediaType.parse("application/json")))
                .build();

        CompletableFuture<T> future = new CompletableFuture<>();
        httpClient.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        future.completeExceptionally(e);
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        if (!response.isSuccessful()) {
                            try (ResponseBody body = response.body()) {
                                if (body == null) {
                                    future.completeExceptionally(new IOException("Unexpected response code: " + response.code() + " - " + response.message()));
                                    return;
                                }

                                String errorBody = body.string();
                                future.completeExceptionally(new IOException("Unexpected response code: " + response.code() + " - " + errorBody));
                            }
                            return;
                        }

                        String responseBody = response.body().string();
                        if (responseBody.isEmpty()) {
                            future.complete(null);
                        } else {
                            System.out.println("Response: " + responseBody);
                            if (responseType == Void.class) {
                                future.complete(null);
                                return;
                            }

                            T result = GSON.fromJson(responseBody, responseType);
                            future.complete(result);
                        }
                    }
                });
        return future;
    }

    @RequiredArgsConstructor
    @Getter
    public enum Environment {
        PRODUCTION(PRODUCTION_ENDPOINT),
        SANDBOX(SANDBOX_ENDPOINT);

        private final String endpoint;
    }
}
