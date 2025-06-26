package me.rochblondiaux.gameanalytics.adapter;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import me.rochblondiaux.gameanalytics.model.event.implementation.SessionEndEvent;
import me.rochblondiaux.gameanalytics.utils.JsonUtil;

public class SessionEndSerializer implements JsonSerializer<SessionEndEvent> {

    @Override
    public JsonElement serialize(SessionEndEvent event, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        JsonUtil.writeCommon(event, jsonObject);
        jsonObject.addProperty("length", event.length());
        return jsonObject;
    }
}
