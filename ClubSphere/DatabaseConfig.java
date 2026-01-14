package com.mycompany.clubsphere.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabaseConfig {
    private static HikariDataSource dataSource;

    static {
        try {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl("jdbc:mysql://localhost:3307/event_management?useSSL=false&allowPublicKeyRetrieval=true");
            config.setUsername("root");
            config.setPassword("mysql");
            config.setDriverClassName("com.mysql.cj.jdbc.Driver");

            config.setMaximumPoolSize(10);
            config.setMinimumIdle(2);
            config.setPoolName("ClubSpherePool");

            dataSource = new HikariDataSource(config);

        } catch (Exception e) {
            System.err.println("DatabaseConfig static init failed: "
                               + e.getClass().getName() + " - " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize HikariCP", e);
        }
    }

    public static HikariDataSource getDataSource() {
        return dataSource;
    }
}
