package com.playinfinitycraft.infinitycraft.database.utils;

import com.playinfinitycraft.infinitycraft.database.Postgres;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.function.Supplier;

public class Fetch {

    public ResultSet allFactions(Postgres psql) throws SQLException {

        PreparedStatement ps;
        ps = psql.getConnection().prepareStatement("SELECT name, tag FROM faction");

        ResultSet rs = ps.executeQuery();

        return rs;

    }

}
