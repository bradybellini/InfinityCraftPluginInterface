package com.playinfinitycraft.infinitycraft.database;

import com.playinfinitycraft.infinitycraft.database.utils.CreateTables;
import com.playinfinitycraft.infinitycraft.database.utils.Fetch;
import com.playinfinitycraft.infinitycraft.database.utils.Insert;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {

    private final Postgres postgres = new Postgres();
    private final CreateTables create = new CreateTables();
    private final Insert insert = new Insert();
    private final Fetch fetch = new Fetch();

    public void init() throws SQLException {
        this.createTables();
    }

    public void connect() throws SQLException {
        postgres.connect("infinity-1");
        Bukkit.getLogger().info("Connected to db with pool name infinity-1");

    }

    public void disconnect() throws SQLException {
        postgres.disconnect();
    }

    public void createTables() throws SQLException {
        create.playerTable(postgres);
        create.factionTypeTable(postgres);
        create.factionRankTable(postgres);
        create.factionTable(postgres);
        create.factionMembersTable(postgres);
    }

    public void insertPlayer(Player player) throws SQLException {
        insert.player(postgres, player);

    }

    public ResultSet fetchAllFactions() throws SQLException {
        return fetch.allFactions(postgres);
    }

    // db.insert.player()

}
