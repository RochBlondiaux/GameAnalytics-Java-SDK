package me.rochblondiaux.gameanalytics.adapter;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import me.rochblondiaux.gameanalytics.model.event.implementation.DesignEvent;
import me.rochblondiaux.gameanalytics.utils.JsonUtil;

public class DesignEventSerializer implements JsonSerializer<DesignEvent> {

    @Override
    public JsonElement serialize(DesignEvent event, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        JsonUtil.writeCommon(event, jsonObject);
        jsonObject.addProperty("build", event.build());
        jsonObject.addProperty("event_id", event.eventId());
        if (event.value() != 0)
            jsonObject.addProperty("value", event.value());
        return jsonObject;
    }

}
