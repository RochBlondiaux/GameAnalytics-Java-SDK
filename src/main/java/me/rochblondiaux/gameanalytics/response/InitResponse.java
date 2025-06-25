package me.rochblondiaux.gameanalytics.response;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import me.rochblondiaux.gameanalytics.model.config.ConfigEntry;

public record InitResponse(@SerializedName("server_ts") long serverTimestamp,
                           @SerializedName("configs") List<ConfigEntry> entries,
                           String key,
                           String value,
                           @SerializedName("start_ts") long startTimestamp,
                           @SerializedName("end_ts") long endTimestamp,
                           @SerializedName("configs_hash") String configHash,
                           @SerializedName("ab_id") String abId,
                           @SerializedName("ab_value") String abValue) {
}
