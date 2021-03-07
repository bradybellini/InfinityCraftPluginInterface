package com.playinfinitycraft.infinitycraft.database;

import org.bukkit.Bukkit;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class SQLExecutor {

    public Postgres SQL;

    public void create(String sql, String consolemessage) throws SQLException {
        PreparedStatement ps;

        ps = this.SQL.getConnection().prepareStatement(sql);
        ps.executeUpdate();
        Bukkit.getLogger().info(consolemessage);
    }
    

}
