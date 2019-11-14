/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.biopark.queijos.atualizador.util;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationInfo;
import org.flywaydb.core.api.MigrationInfoService;

/**
 *
 * @author Renato
 */
public class FlywayDatabase {
    
    public static void updateDataBase(String dbUrl, String user, String paswd, String sqlScriptLocations) {

        Flyway flyway = Flyway.configure()
                .dataSource(dbUrl, user, paswd)
                .locations(sqlScriptLocations)
                .baselineOnMigrate(true)
                .load();
        
        
        flyway.migrate();
        /**
         * Find which versions are not applied yet.
         */
        
        flyway.getConfiguration().getLocations();
        
        MigrationInfoService migrationInfoService = flyway.info();
        MigrationInfo[] migrationInfo = migrationInfoService.all();

        for (MigrationInfo mi : migrationInfo) {
            String version = mi.getVersion().toString();
            String state = mi.getState().isApplied() + "";
            System.out.println(String.format("Is target version %s applied? %s", version, state));
        }
        
//        progress.lbStatus.setText("Versão de banco aplicada: "+migrationInfo[migrationInfo.length-1].getVersion().toString());
//        progress.repaint();
//        sleep(2000);
    }
    
}
