/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifprbiopark.atualizador_queijo_desktop.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationInfo;
import org.flywaydb.core.api.MigrationInfoService;
import org.flywaydb.core.api.configuration.FluentConfiguration;

/**
 *
 * @author Renato
 */
public class FlywayDatabase {

    public void updateDataBase(String dbUrl, String user, String paswd, String sqlScriptLocations, boolean firstVersion) {

        FluentConfiguration config = Flyway.configure();

        config.dataSource(dbUrl, user, paswd);
        if (firstVersion) {
            File tmpDir = new File(sqlScriptLocations.replace("filesystem:", "") + "/V1.0.0__Script_Inicial.sql");

            if (tmpDir.exists()) {

                String sql = readLineByLineJava8(tmpDir.getPath());
                config.initSql(sql);
                config.baselineOnMigrate(true);
            }

        } else {
            config.locations(sqlScriptLocations);
        }

        Flyway flyway = config.load();

        flyway.migrate();

        /**
         * Find which versions are not applied yet.
         */
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

    private String readLineByLineJava8(String filePath) {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }

}