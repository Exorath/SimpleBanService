package com.exorath.simplebanservice.service;

import com.exorath.simplebanservice.Player;
import com.microsoft.azure.storage.table.TableServiceEntity;

/**
 * Created by toonsev on 11/3/2016.
 */
public class PlayerTableEntity extends TableServiceEntity {
    public static final String ROW_KEY = "exorath";
    Boolean banned;
    String reason;
    Long expiry;

    public PlayerTableEntity(){

    }
    public PlayerTableEntity(Player player) {
        super(player.getUuid(), ROW_KEY);

        this.banned = player.getBanned();
        this.reason = player.getReason();
        this.expiry = player.getExpiry();
    }

    public void setExpiry(Long expiry) {
        this.expiry = expiry;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setBanned(Boolean banned) {
        this.banned = banned;
    }

    public Long getExpiry() {
        return expiry;
    }

    public String getReason() {
        return reason;
    }

    public Boolean getBanned() {
        return banned;
    }

    public Player toPlayer(){
        return new Player(getPartitionKey(), banned, reason, expiry);
    }
}
