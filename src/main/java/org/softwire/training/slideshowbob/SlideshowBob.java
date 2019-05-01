package org.softwire.training.slideshowbob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SlideshowBob {
    public static void main(String[] args) {
        FlywayDatabaseMigrator.migrateDatabase();
        SpringApplication.run(SlideshowBob.class, args);
    }
}
