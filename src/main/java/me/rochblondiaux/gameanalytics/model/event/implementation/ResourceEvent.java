package me.rochblondiaux.gameanalytics.model.event.implementation;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import me.rochblondiaux.gameanalytics.GameAnalytics;
import me.rochblondiaux.gameanalytics.model.event.EventCategory;

@Getter
public class ResourceEvent extends Event {

    private FlowType flowType;
    private Currency currency;
    private String itemType;
    private String itemId;
    private int amount;

    @Builder
    public ResourceEvent(String device, String userId, long clientTs, String osVersion, String manufacturer, String platform, UUID sessionId, int sessionNumber, String custom01, String custom02, String custom03, String build, FlowType flowType, Currency currency, String itemType, String itemId, int amount) {
        super(EventCategory.RESOURCE, 2, device, userId, clientTs, GameAnalytics.VERSION, osVersion, manufacturer, platform, sessionId, sessionNumber, custom01, custom02, custom03, build);
        this.flowType = flowType;
        this.currency = currency;
        this.itemType = itemType;
        this.amount = amount;
        this.itemId = itemId;
    }

    public enum FlowType {
        SINK,
        SOURCE
    }

    public enum Currency {
        BOOST,
        COINS,
        GEMS,
        LIVES,
        STARS
    }
}
