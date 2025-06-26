package me.rochblondiaux.gameanalytics.adapter;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import me.rochblondiaux.gameanalytics.model.event.implementation.BusinessEvent;
import me.rochblondiaux.gameanalytics.utils.JsonUtil;

public class BusinessEventSerializer implements JsonSerializer<BusinessEvent> {

    @Override
    public JsonElement serialize(BusinessEvent event, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        JsonUtil.writeCommon(event, jsonObject);
        jsonObject.addProperty("build", event.build());
        jsonObject.addProperty("event_id", "%s:%s".formatted(event.itemType(), event.itemId()));
        jsonObject.addProperty("amount", event.amount());
        jsonObject.addProperty("currency", event.currency());
        jsonObject.addProperty("transaction_num", event.transactionNumber());
        jsonObject.addProperty("cart_type", event.cartType().name().toLowerCase());
        if (event.receiptInformation() != null)
            jsonObject.add("receipt_info", jsonSerializationContext.serialize(event.receiptInformation(), BusinessEvent.ReceiptInformation.class));
        return jsonObject;
    }

}
