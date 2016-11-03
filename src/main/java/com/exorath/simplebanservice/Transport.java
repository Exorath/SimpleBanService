package com.exorath.simplebanservice;

import com.exorath.service.commons.portProvider.PortProvider;
import com.google.gson.Gson;
import spark.Route;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.put;

/**
 * Created by toonsev on 11/3/2016.
 */
public class Transport {
    private static final Gson GSON = new Gson();

    public static void setup(Service service, PortProvider portProvider){
        port(portProvider.getPort());
        get("/players/:uuid", Transport.getGetPlayerRoute(service), GSON::toJson);
        put("/players/:uuid", Transport.getUpdatePlayerRoute(service),GSON::toJson);
    }

    public static Route getGetPlayerRoute(Service service){
        return (req, res) -> service.getPlayer(req.params("uuid"));
    }

    public static Route getUpdatePlayerRoute(Service service){
        return (req, res) -> {
            Player player = GSON.fromJson(req.body(), Player.class);
            player.setUuid(req.params("uuid"));
            return service.updatePlayer(player);
        };
    }
}
