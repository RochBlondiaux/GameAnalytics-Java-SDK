package me.rochblondiaux.gameanalytics.adapter;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import me.rochblondiaux.gameanalytics.model.event.implementation.DesignEvent;

public class DesignEventSerializer implements JsonSerializer<DesignEvent> {

    @Override
    public JsonElement serialize(DesignEvent event, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("category", event.category().name().toLowerCase());
        jsonObject.addProperty("v", event.version());
        jsonObject.addProperty("device", event.device());
        jsonObject.addProperty("user_id", event.userId());
        jsonObject.addProperty("client_ts", event.clientTs());
        jsonObject.addProperty("sdk_version", event.sdkVersion());
        jsonObject.addProperty("os_version", event.osVersion());
        jsonObject.addProperty("manufacturer", event.manufacturer());
        jsonObject.addProperty("platform", event.platform());
        jsonObject.addProperty("session_id", event.sessionId().toString());
        jsonObject.addProperty("session_num", event.sessionNumber());
        jsonObject.addProperty("custom_01", event.custom01());
        jsonObject.addProperty("custom_02", event.custom02());
        jsonObject.addProperty("custom_03", event.custom03());
        jsonObject.addProperty("build", event.build());
        jsonObject.addProperty("event_id", event.eventId());
        if (event.value() != 0)
            jsonObject.addProperty("value", event.value());
        return jsonObject;
    }

}
