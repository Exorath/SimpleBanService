package com.exorath.simplebanservice.service;

import com.exorath.service.commons.azureStorageProvider.AzureStorageProvider;
import com.exorath.service.commons.tableNameProvider.TableNameProvider;
import com.exorath.simplebanservice.Player;
import com.exorath.simplebanservice.Service;
import com.exorath.simplebanservice.Success;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.*;


/**
 * Created by toonsev on 11/3/2016.
 */
public class AzureTableStorageService implements Service {

    private CloudTableClient db;
    private CloudTable table;

    public AzureTableStorageService(AzureStorageProvider storageProvider, TableNameProvider tableNameProvider) throws Exception {
        this.db = storageProvider.getAccount().createCloudTableClient();
        this.table = db.getTableReference(tableNameProvider.getTableName());
        table.createIfNotExists();
    }

    @Override
    public Player getPlayer(String uuid) {
        try {

            Object o = table.execute(new TableQuery(PlayerTableEntity.class).select(new String[]{"Banned"})).iterator().next();
            TableResult result = table.execute(TableOperation.retrieve(uuid, PlayerTableEntity.ROW_KEY, PlayerTableEntity.class));
            PlayerTableEntity tableEntity = result.<PlayerTableEntity>getResultAsType();
            if (tableEntity == null)//Not found
                return new Player(uuid, false, null, null);
            return tableEntity.toPlayer();
        } catch (Exception e) {
            e.printStackTrace();
            return new Player(uuid, null, null, null);
        }
    }

    @Override
    public Success updatePlayer(Player player) {
        if (player.getUuid() == null)
            return new Success(false, "No valid uuid provided");
        if (player.getBanned() == null)
            return new Success(false, "No valid banned parameter provided");
        try {
            return player.getBanned() ? putPlayer(player) : removePlayer(player);
        } catch (Exception e) {
            e.printStackTrace();
            return new Success(false, e.getMessage());
        }
    }

    private Success removePlayer(Player player) throws StorageException {
        System.out.println(player.getUuid());
        try {
            TableResult result = table.execute(TableOperation.delete(new DynamicTableEntity(player.getUuid(), PlayerTableEntity.ROW_KEY, "*", null)));
            if (result.getHttpStatusCode() != 204)
                return new Success(false, "Update returned status code " + result.getHttpStatusCode());
        } catch (TableServiceException e) {
            if (e.getHttpStatusCode() == 404)
                return new Success(true);//successfully deleted entity
            throw e;
        }
        return new Success(true);
    }

    private Success putPlayer(Player player) throws StorageException {
        PlayerTableEntity tableEntity = new PlayerTableEntity(player);
        TableResult result = table.execute(TableOperation.insertOrReplace(tableEntity));
        if (result.getHttpStatusCode() != 204)
            return new Success(false, "Update returned status code " + result.getHttpStatusCode());
        return new Success(true);
    }
}
