package me.rochblondiaux.gameanalytics;

import me.rochblondiaux.gameanalytics.request.InitRequest;

public class Test {

    public static void main(String[] args) {
        GameAnalytics analytics = new GameAnalytics(
                "3fa7da8052ba26f4f97ead31cfd49015",
                "f881bcd4d5df1f793e2995daf5f6dd3e1344eb88",
                GameAnalytics.Environment.SANDBOX
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
                });
    }

}
