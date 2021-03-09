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
//"CONSTRAINT player_id_unique UNIQUE (id)" +
        ps.executeUpdate();

        Bukkit.getLogger().info("Player Table Created");
    }

    public void factionRankTable(Postgres psql) throws SQLException {
        PreparedStatement ps;

        ps = psql.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS public.faction_ranks " +
                "(id SERIAL NOT NULL, name varchar, CONSTRAINT faction_ranks_pkey PRIMARY KEY (id))");

        ps.executeUpdate();

        Bukkit.getLogger().info("Faction Ranks Table Created");
    }

    public void factionTypeTable(Postgres psql) throws SQLException {
        PreparedStatement ps;

        ps = psql.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS public.faction_type " +
                "(id SERIAL NOT NULL, name varchar, CONSTRAINT faction_type_pkey PRIMARY KEY (id))");

        ps.executeUpdate();

        Bukkit.getLogger().info("Faction Type Table Created");
    }

    public void factionTable(Postgres psql) throws SQLException {
        PreparedStatement ps;

        ps = psql.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS public.faction " +
                "(id SERIAL NOT NULL, name varchar, tag varchar, member_count int DEFAULT 1, " +
                "faction_type_id int, npc bool DEFAULT false, CONSTRAINT faction_id_unique UNIQUE (id), " +
                "CONSTRAINT faction_pkey PRIMARY KEY (id), CONSTRAINT faction_name_unique UNIQUE (name), " +
                "CONSTRAINT faction_tag_unique UNIQUE (tag), " +
                "CONSTRAINT faction_type_id_fkey FOREIGN KEY(faction_type_id) " +
                "REFERENCES faction_type(id) ON DELETE CASCADE)");

        ps.executeUpdate();

        Bukkit.getLogger().info("Faction Table Created");
    }

    public void factionMembersTable(Postgres psql) throws SQLException {
        PreparedStatement ps;

        ps = psql.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS public.faction_members " +
                "(player_id int, faction_rank_id int, faction_id int, " +
                "CONSTRAINT player_id_fkey FOREIGN KEY(player_id) REFERENCES player(id) ON DELETE CASCADE, " +
                "CONSTRAINT rank_id_fkey FOREIGN KEY(faction_rank_id) REFERENCES faction_ranks(id) ON DELETE CASCADE, " +
                "CONSTRAINT faction_id_fkey FOREIGN KEY(faction_id) REFERENCES faction(id) ON DELETE CASCADE)");

        ps.executeUpdate();

        Bukkit.getLogger().info("Faction Members Table Created");
    }


}


