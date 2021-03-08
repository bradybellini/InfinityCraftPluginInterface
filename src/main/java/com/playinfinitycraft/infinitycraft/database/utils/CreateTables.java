package com.playinfinitycraft.infinitycraft.database.utils;

import com.playinfinitycraft.infinitycraft.database.Postgres;
import org.bukkit.Bukkit;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateTables {

    public void playerTable(Postgres psql) throws SQLException {
        PreparedStatement ps;

        ps = psql.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS public.player " +
                "(id SERIAL NOT NULL, uuid uuid NOT NULL, CONSTRAINT player_pkey PRIMARY KEY (id), " +
                "CONSTRAINT player_uuid_unique UNIQUE (uuid))");

        ps.executeUpdate();

        Bukkit.getLogger().info("Player Table Created");
    }
// TODO test this table, have not generated it yet ADD ON DELETE ACTION
    public void factionTable(Postgres psql) throws SQLException {
        PreparedStatement ps;

        ps = psql.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS public.faction " +
                "(id SERIAL NOT NULL, name varchar, member_count int DEFAULT 1, tag varchar, player_id int, " +
                "CONSTRAINT faction_pkey PRIMARY KEY (id), CONSTRAINT faction_name_unique UNIQUE (name), " +
                "CONSTRAINT faction_tag_unique UNIQUE, CONSTRAINT player_id_fkey FOREIGN KEY(player_id) " +
                "REFERENCES player(id))");


        ps.executeUpdate();

        Bukkit.getLogger().info("Faction Table Created");

    }


}


