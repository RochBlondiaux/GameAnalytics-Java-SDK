package me.rochblondiaux.gameanalytics.adapter;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import me.rochblondiaux.gameanalytics.model.event.implementation.ResourceEvent;

public class ResourceEventSerializer implements JsonSerializer<ResourceEvent> {

    @Override
    public JsonElement serialize(ResourceEvent resourceEvent, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("category", resourceEvent.category().name().toLowerCase());
        jsonObject.addProperty("v", resourceEvent.version());
        jsonObject.addProperty("device", resourceEvent.device());
        jsonObject.addProperty("user_id", resourceEvent.userId());
        jsonObject.addProperty("client_ts", resourceEvent.clientTs());
        jsonObject.addProperty("sdk_version", resourceEvent.sdkVersion());
        jsonObject.addProperty("os_version", resourceEvent.osVersion());
        jsonObject.addProperty("manufacturer", resourceEvent.manufacturer());
        jsonObject.addProperty("platform", resourceEvent.platform());
        jsonObject.addProperty("session_id", resourceEvent.sessionId().toString());
        jsonObject.addProperty("session_num", resourceEvent.sessionNumber());
        jsonObject.addProperty("custom_01", resourceEvent.custom01());
        jsonObject.addProperty("custom_02", resourceEvent.custom02());
        jsonObject.addProperty("custom_03", resourceEvent.custom03());
        jsonObject.addProperty("build", resourceEvent.build());
        jsonObject.addProperty("event_id", "%s:%s:%s:%s".formatted(resourceEvent.flowType().name().toLowerCase(), resourceEvent.currency().name().toLowerCase(), resourceEvent.itemType(), resourceEvent.amount()));
        jsonObject.addProperty("amount", resourceEvent.amount());
        return jsonObject;
    }

}
