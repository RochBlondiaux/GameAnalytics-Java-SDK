package me.rochblondiaux.gameanalytics.request;

import com.google.gson.annotations.SerializedName;

import lombok.Builder;

@Builder(toBuilder = true)
public record InitRequest(@SerializedName("user_id") String userId, String platform,
                          @SerializedName("os_version") String osVersion, String build,
                          @SerializedName("sdk_version") String sdkVersion,
                          @SerializedName("random_salt") String randomSalt) {
}
