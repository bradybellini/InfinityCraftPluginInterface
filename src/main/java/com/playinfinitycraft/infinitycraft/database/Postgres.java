package com.playinfinitycraft.infinitycraft.database;

import com.playinfinitycraft.infinitycraft.InfinityCraft;
import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.SQLException;

public class Postgres {

    private Connection conn;

    public boolean isConnected() {
        return (conn != null);
    }

    public void connect(String poolname) throws SQLException {
        if (!isConnected()) {

            Bukkit.getLogger().info("Connection attempted");

            HikariDataSource ds = new HikariDataSource();
            ds.setJdbcUrl(InfinityCraft.getPlugin().getConfig().getString("jdbcurl"));
            ds.setUsername(InfinityCraft.getPlugin().getConfig().getString("dbusername"));
            ds.setPassword(InfinityCraft.getPlugin().getConfig().getString("dbpassword"));
            ds.setDriverClassName("org.postgresql.Driver");
            ds.setPoolName(poolname);

            conn = ds.getConnection();
            Bukkit.getLogger().info("Connected");


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
