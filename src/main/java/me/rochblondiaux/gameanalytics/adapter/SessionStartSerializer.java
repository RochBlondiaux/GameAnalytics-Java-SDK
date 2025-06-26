package me.rochblondiaux.gameanalytics.adapter;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import me.rochblondiaux.gameanalytics.model.event.implementation.SessionEndEvent;
import me.rochblondiaux.gameanalytics.model.event.implementation.SessionStartEvent;
import me.rochblondiaux.gameanalytics.utils.JsonUtil;

public class SessionStartSerializer implements JsonSerializer<SessionStartEvent> {

    @Override
    public JsonElement serialize(SessionStartEvent event, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        JsonUtil.writeCommon(event, jsonObject);
        return jsonObject;
    }
}
