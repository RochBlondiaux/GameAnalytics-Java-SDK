package me.rochblondiaux.gameanalytics.utils;

import com.google.gson.JsonObject;

import lombok.experimental.UtilityClass;
import me.rochblondiaux.gameanalytics.model.event.implementation.Event;

@UtilityClass
public class JsonUtil {

    public void writeCommon(Event event, JsonObject object) {
        object.addProperty("category", event.category().name().toLowerCase());
        object.addProperty("v", event.version());
        object.addProperty("device", event.device());
        object.addProperty("user_id", event.userId());
        if (event.clientTs() > 0)
            object.addProperty("client_ts", event.clientTs());
        object.addProperty("sdk_version", event.sdkVersion());
        object.addProperty("os_version", event.osVersion());
        object.addProperty("manufacturer", event.manufacturer());
        object.addProperty("platform", event.platform());
        if (event.sessionId() != null)
            object.addProperty("session_id", event.sessionId().toString());
        object.addProperty("session_num", event.sessionNumber());
        if (event.custom01() != null)
            object.addProperty("custom_01", event.custom01());
        if (event.custom02() != null)
            object.addProperty("custom_02", event.custom02());
        if (event.custom03() != null)
            object.addProperty("custom_03", event.custom03());
    }
}
