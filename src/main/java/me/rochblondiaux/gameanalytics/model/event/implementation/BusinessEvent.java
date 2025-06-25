package me.rochblondiaux.gameanalytics.model.event.implementation;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import me.rochblondiaux.gameanalytics.GameAnalytics;
import me.rochblondiaux.gameanalytics.model.event.EventCategory;

@Getter
public class BusinessEvent extends Event {

    private String itemType;
    private String itemId;
    private int amount;
    private String currency;
    private int transactionNumber;
    private CartType cartType;
    private ReceiptInformation receiptInformation;

    @Builder
    public BusinessEvent(String device, String userId, long clientTs, String osVersion, String manufacturer, String platform, UUID sessionId, int sessionNumber, String custom01, String custom02, String custom03, String build,
                         String itemType, String itemId, int amount, String currency, int transactionNumber, CartType cartType, ReceiptInformation receiptInformation) {
        super(EventCategory.BUSINESS, 2, device, userId, clientTs, GameAnalytics.VERSION, osVersion, manufacturer, platform, sessionId, sessionNumber, custom01, custom02, custom03, build);
        this.amount = amount;
        this.currency = currency;
        this.transactionNumber = transactionNumber;
        this.cartType = cartType;
        this.receiptInformation = receiptInformation;
        this.itemType = itemType;
        this.itemId = itemId;
    }

    public record ReceiptInformation(Store store, String receipt, String signature) {

        public enum Store {
            APPLE,
            GOOGLE_PLAY,
            UNKNOWN
        }
    }

    public enum CartType {
        MENU_SHOP,
        END_OF_LEVEL_SHOP
    }
}
