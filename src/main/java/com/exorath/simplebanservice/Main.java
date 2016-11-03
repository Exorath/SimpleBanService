package com.exorath.simplebanservice;

import com.exorath.service.commons.azureStorageProvider.AzureStorageProvider;
import com.exorath.service.commons.portProvider.PortProvider;
import com.exorath.service.commons.tableNameProvider.TableNameProvider;
import com.exorath.simplebanservice.service.AzureTableStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by toonsev on 11/3/2016.
 */
public class Main {
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);
    private Service svc;

    public Main() throws Exception {
        this.svc = new AzureTableStorageService(AzureStorageProvider.getEnvironmentAzureStorageProvider(), TableNameProvider.getEnvironmentTableNameProvider());//Todo: write service impl
        LOG.info("Service " + this.svc.getClass() + " instantiated");

        Transport.setup(svc, PortProvider.getEnvironmentPortProvider());
        LOG.info("HTTP transport setup");
    }
    public static void main(String[] args){
        try {
            new Main();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
