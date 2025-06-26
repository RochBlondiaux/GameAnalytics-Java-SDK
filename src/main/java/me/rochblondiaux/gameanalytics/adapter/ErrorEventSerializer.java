package me.rochblondiaux.gameanalytics.adapter;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import me.rochblondiaux.gameanalytics.model.event.implementation.ErrorEvent;
import me.rochblondiaux.gameanalytics.utils.JsonUtil;

public class ErrorEventSerializer implements JsonSerializer<ErrorEvent> {

    @Override
    public JsonElement serialize(ErrorEvent event, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        JsonUtil.writeCommon(event, jsonObject);
        jsonObject.addProperty("build", event.build());
        jsonObject.addProperty("severity", event.severity().name().toLowerCase());
        jsonObject.addProperty("message", event.message());
        return jsonObject;
    }

}
