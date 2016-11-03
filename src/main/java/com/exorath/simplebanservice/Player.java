package com.exorath.simplebanservice;

import com.google.gson.annotations.SerializedName;

/**
 * Created by toonsev on 11/3/2016.
 */
public class Player {
    private transient String uuid;

    @SerializedName("banned")
    private Boolean banned;
    @SerializedName("reason")
    private String reason;
    @SerializedName("expiry")
    private Long expiry;

    public Player(String uuid, Boolean banned, String reason, Long expiry){
        this.uuid = uuid;
        this.banned = banned;
        this.reason = reason;
        this.expiry = expiry;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public Boolean getBanned() {
        return banned;
    }

    public String getReason() {
        return reason;
    }

    public Long getExpiry() {
        return expiry;
    }
}
