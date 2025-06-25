package me.rochblondiaux.gameanalytics;

import java.util.List;
import java.util.UUID;

import me.rochblondiaux.gameanalytics.model.event.implementation.DesignEvent;
import me.rochblondiaux.gameanalytics.model.event.implementation.ErrorEvent;
import me.rochblondiaux.gameanalytics.model.event.implementation.ProgressionEvent;
import me.rochblondiaux.gameanalytics.request.InitRequest;

public class Test {

    public static void main(String[] args) {
        GameAnalytics analytics = new GameAnalytics(
                "3fa7da8052ba26f4f97ead31cfd49015",
                "f881bcd4d5df1f793e2995daf5f6dd3e1344eb88",
                GameAnalytics.Environment.PRODUCTION
        );

        System.out.println("Sending init request...");
        analytics.init(1, InitRequest.builder()
                        .build("1.0")
                        .userId("LordKiwix")
                        .osVersion("1.21.3")
                        .platform("Java")
                        .sdkVersion(GameAnalytics.VERSION)
                        .build())
                .whenComplete((response, throwable) -> {
                    if (throwable != null) {
                        System.err.println("Failed to initialize: " + throwable.getMessage());
                        return;
                    }

                    String configHash = response.configHash();
                    System.out.println("Initialization successful! Hash: " + configHash);

                    System.out.println("Sending events...");
                    UUID sessionId = UUID.randomUUID();
                    analytics.sendEvents(List.of(
                            ErrorEvent.builder()
                                    .userId("LordKiwix")
                                    .sessionId(sessionId)
                                    .sessionNumber(1)
                                    .device("iPhone 6")
                                    .osVersion("ios 8.1")
                                    .manufacturer("apple")
                                    .platform("ios")
                                    .clientTs(System.currentTimeMillis())
                                    .severity(ErrorEvent.Type.ERROR)
                                    .message("An error occurred while processing the request")
                                    .build(),
                            DesignEvent.builder()
                                    .userId("LordKiwix")
                                    .sessionId(sessionId)
                                    .sessionNumber(1)
                                    .device("iPhone 6")
                                    .osVersion("ios 8.1")
                                    .manufacturer("apple")
                                    .platform("ios")
                                    .clientTs(System.currentTimeMillis())
                                    .eventId("test:test:test")
                                    .value(10)
                                    .build(),
                            ProgressionEvent.builder()
                                    .userId("LordKiwix")
                                    .sessionId(sessionId)
                                    .sessionNumber(1)
                                    .device("iPhone 6")
                                    .osVersion("ios 8.1")
                                    .manufacturer("apple")
                                    .platform("ios")
                                    .clientTs(System.currentTimeMillis())
                                    .eventId("Start:PirateIsland:SandyHills")
                                    .attempt(1)
                                    .score(1)
                                    .build()
                    )).whenComplete((aVoid, throwable1) -> {
                        if (throwable1 != null) {
                            System.err.println("Failed to send events: " + throwable1.getMessage());
                        } else {
                            System.out.println("Events sent successfully!");
                        }
                    });
                });
    }

}
