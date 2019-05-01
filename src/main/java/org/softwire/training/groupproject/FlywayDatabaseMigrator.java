package org.softwire.training.groupproject;

import org.flywaydb.core.Flyway;

class FlywayDatabaseMigrator {
    static void migrateDatabase() {

        DatabaseConfiguration dc = new DatabaseConfiguration();

        String connectionString = "jdbc:mysql://" +
                dc.getHostname() +
                "?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT&useSSL=false&allowPublicKeyRetrieval=true";

        // Create the FlywayCreator instance and point it to the database
        Flyway flyway = Flyway
                .configure()
                .dataSource(connectionString, dc.getUsername(), dc.getPassword())
                .schemas(dc.getDatabaseName())
                .load();

        // Start the migration
        flyway.migrate();
    }
}
