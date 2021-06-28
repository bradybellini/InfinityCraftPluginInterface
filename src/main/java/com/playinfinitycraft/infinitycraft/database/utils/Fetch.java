package com.playinfinitycraft.infinitycraft.database.utils;

import com.playinfinitycraft.infinitycraft.database.Postgres;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;


public class Fetch {

    public ResultSet allFactions(Postgres psql) throws SQLException {

        PreparedStatement ps;
        ps = psql.getConnection().prepareStatement("SELECT id, name, tag, member_count, npc FROM faction",
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

        ResultSet rs = ps.executeQuery();
        return rs;
    }

    public ResultSet allFactionsId(Postgres psql) throws SQLException {

        PreparedStatement ps;
        ps = psql.getConnection().prepareStatement("SELECT id FROM faction");

        ResultSet rs = ps.executeQuery();
        return rs;
    }

    public ResultSet factionById(Postgres psql, int id) throws SQLException {

        PreparedStatement ps;
        ps = psql.getConnection().prepareStatement("SELECT id, name, tag, member_count, npc from faction WHERE id = ?");
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        return rs;

    }

}
