package me.rochblondiaux.gameanalytics.adapter;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import me.rochblondiaux.gameanalytics.model.event.implementation.ResourceEvent;
import me.rochblondiaux.gameanalytics.utils.JsonUtil;

public class ResourceEventSerializer implements JsonSerializer<ResourceEvent> {

    @Override
    public JsonElement serialize(ResourceEvent resourceEvent, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        JsonUtil.writeCommon(resourceEvent, jsonObject);
        jsonObject.addProperty("build", resourceEvent.build());
        jsonObject.addProperty("event_id", "%s:%s:%s:%s".formatted(capitalize(resourceEvent.flowType().name()), capitalize(resourceEvent.currency().name()), resourceEvent.itemType(), resourceEvent.itemId()));
        jsonObject.addProperty("amount", resourceEvent.amount());
        return jsonObject;
    }

    private String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

}
