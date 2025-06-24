package me.rochblondiaux.gameanalytics.model;

import com.google.gson.annotations.SerializedName;

public record ConfigEntry(String key, String value, @SerializedName("start_ts") long startTimestamp,
                          @SerializedName("end_ts") long endTimestamp) {

    public boolean hasExpired(long currentTimestamp) {
        return endTimestamp > 0 && currentTimestamp > endTimestamp;
    }

    public boolean isActive(long currentTimestamp) {
        return startTimestamp <= currentTimestamp && !hasExpired(currentTimestamp);
    }
}
