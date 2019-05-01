package org.softwire.training.groupproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GroupProjectMain {
    public static void main(String[] args) {
        FlywayDatabaseMigrator.migrateDatabase();
        SpringApplication.run(GroupProjectMain.class, args);
    }
}
