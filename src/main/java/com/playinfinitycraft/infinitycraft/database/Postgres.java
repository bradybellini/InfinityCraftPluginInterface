package com.playinfinitycraft.infinitycraft.database;

import com.playinfinitycraft.infinitycraft.files.ConfigFile;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Postgres {

    private Connection conn;

    public boolean isConnected() {
        return (conn != null);
    }

    public void connect(String poolname) throws SQLException {
        if (!isConnected()) {
            HikariDataSource ds = new HikariDataSource();
            ds.setJdbcUrl(ConfigFile.get().getString("jdbcurl"));
            ds.setUsername(ConfigFile.get().getString("dbusername"));
            ds.setPassword(ConfigFile.get().getString("dbpassword"));
            ds.setDriverClassName("PostgreSQL");
            ds.setPoolName(poolname);

            conn = ds.getConnection();
        }

    }

    public void disconnect() throws SQLException {
        if (isConnected()) {
            conn.close();
        }
    }

    public Connection getConnection() {
        return conn;
    }








}
