package me.rochblondiaux.gameanalytics.model.event.implementation;

import java.util.UUID;

import com.google.gson.annotations.SerializedName;

import lombok.Builder;
import lombok.Getter;
import me.rochblondiaux.gameanalytics.GameAnalytics;
import me.rochblondiaux.gameanalytics.model.event.EventCategory;

@Getter
public class ProgressionEvent extends Event {

    @SerializedName("event_id")
    private String eventId;
    @SerializedName("attempt_num")
    private int attempt;
    private int score;

    @Builder
    public ProgressionEvent(String device, String userId, long clientTs, String osVersion, String manufacturer, String platform, UUID sessionId, int sessionNumber, String custom01, String custom02, String custom03, String build, String eventId, int attempt, int score) {
        super(EventCategory.PROGRESSION, 2, device, userId, clientTs, GameAnalytics.VERSION, osVersion, manufacturer, platform, sessionId, sessionNumber, custom01, custom02, custom03, build);
        this.eventId = eventId;
        this.attempt = attempt;
        this.score = score;
    }
}
