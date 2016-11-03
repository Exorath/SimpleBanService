package com.exorath.simplebanservice;

/**
 * Created by toonsev on 11/3/2016.
 */
public interface Service {

    Player getPlayer(String uuid);

    Success updatePlayer(Player player);
}
