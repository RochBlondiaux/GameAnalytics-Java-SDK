package me.rochblondiaux.gameanalytics.model.config;

import org.jetbrains.annotations.Nullable;

import com.google.gson.annotations.SerializedName;

public record ConfigEntry(String key, String value, @SerializedName("start_ts") long startTimestamp,
                          @SerializedName("end_ts") @Nullable Long endTimestamp) {

    public boolean hasExpired(long currentTimestamp) {
        return endTimestamp != null && endTimestamp > 0 && currentTimestamp > endTimestamp;
    }

    public boolean isActive(long currentTimestamp) {
        return startTimestamp <= currentTimestamp && !hasExpired(currentTimestamp);
    }
}
