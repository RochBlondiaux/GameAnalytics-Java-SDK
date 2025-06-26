package me.rochblondiaux.gameanalytics.adapter;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import me.rochblondiaux.gameanalytics.model.event.implementation.DesignEvent;
import me.rochblondiaux.gameanalytics.model.event.implementation.ProgressionEvent;
import me.rochblondiaux.gameanalytics.utils.JsonUtil;

public class ProgressionEventSerializer implements JsonSerializer<ProgressionEvent> {

    @Override
    public JsonElement serialize(ProgressionEvent event, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        JsonUtil.writeCommon(event, jsonObject);
        jsonObject.addProperty("build", event.build());
        jsonObject.addProperty("event_id", event.eventId());
        jsonObject.addProperty("attempt_num", event.attempt());
        jsonObject.addProperty("score", event.score());
        return jsonObject;
    }

}
