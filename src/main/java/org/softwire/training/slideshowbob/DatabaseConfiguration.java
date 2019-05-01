package org.softwire.training.slideshowbob;

public class DatabaseConfiguration {
    private final String hostname = System.getenv("DB_HOSTNAME");
    private final String database = System.getenv("DB_NAME");
    private final String user = System.getenv("DB_USER");
    private final String password = System.getenv("DB_PASS");
    private final String connectionString = "jdbc:mysql://" + hostname + "/" + database + "?user=" + user + "&password=" + password + "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT&useSSL=false";

    public String getConnectionString() {
        return connectionString;
    }

    public String getHostname() {
        return hostname;
    }

    public String getDatabaseName() {
        return database;
    }

    public String getUsername() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
