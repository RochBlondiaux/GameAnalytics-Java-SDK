package me.rochblondiaux.gameanalytics.model.event.implementation;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import me.rochblondiaux.gameanalytics.GameAnalytics;
import me.rochblondiaux.gameanalytics.model.event.EventCategory;

@Getter
public class ErrorEvent extends Event{

    private Type severity;
    private String message;

    @Builder
    public ErrorEvent(String device, String userId, long clientTs, String osVersion, String manufacturer, String platform, UUID sessionId, int sessionNumber, String custom01, String custom02, String custom03, String build, Type severity, String message) {
        super(EventCategory.ERROR, 2, device, userId, clientTs, GameAnalytics.VERSION, osVersion, manufacturer, platform, sessionId, sessionNumber, custom01, custom02, custom03, build);
        this.severity = severity;
        this.message = message;
    }

    public enum Type {
        DEBUG,
        INFO,
        WARNING,
        ERROR,
        CRITICAL;
    }
}
