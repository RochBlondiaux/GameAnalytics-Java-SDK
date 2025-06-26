package me.rochblondiaux.gameanalytics.model.event.implementation;

import java.util.UUID;

import lombok.Builder;
import me.rochblondiaux.gameanalytics.GameAnalytics;
import me.rochblondiaux.gameanalytics.model.event.EventCategory;

public class SessionStartEvent extends Event {

    @Builder
    public SessionStartEvent(int version, String device, String userId, long clientTs, String osVersion, String manufacturer, String platform, UUID sessionId, int sessionNumber, String custom01, String custom02, String custom03, String build) {
        super(EventCategory.USER, version, device, userId, clientTs, GameAnalytics.VERSION, osVersion, manufacturer, platform, sessionId, sessionNumber, custom01, custom02, custom03, build);
    }
}
