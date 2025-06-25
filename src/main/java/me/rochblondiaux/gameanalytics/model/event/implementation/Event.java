package me.rochblondiaux.gameanalytics.model.event.implementation;

import java.util.UUID;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.rochblondiaux.gameanalytics.GameAnalytics;
import me.rochblondiaux.gameanalytics.model.event.EventCategory;

@AllArgsConstructor
@Getter
public abstract class Event {

    private EventCategory category;
    @SerializedName("v")
    private int version = 2;
    private String device;
    @SerializedName("user_id")
    private String userId;
    @SerializedName("client_ts")
    private long clientTs;
    @SerializedName("sdk_version")
    private String sdkVersion = GameAnalytics.VERSION;
    @SerializedName("os_version")
    private String osVersion;
    @SerializedName("manufacturer")
    private String manufacturer;
    @SerializedName("platform")
    private String platform;
    @SerializedName("session_id")
    private UUID sessionId;
    @SerializedName("session_num")
    private int sessionNumber;
    @SerializedName("custom_01")
    private String custom01;
    @SerializedName("custom_02")
    private String custom02;
    @SerializedName("custom_03")
    private String custom03;
    private String build;



}
