package com.playinfinitycraft.infinitycraft.database;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import redis.clients.jedis.*;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

@SuppressWarnings("CStyleArrayDeclaration")
public class Cache {

    private final Database db;
    private final Redis redis = new Redis();

    public Cache(Database db) {
        this.db = db;
    }

    public void init() throws SQLException {
        this.loadFactions();
    }

    public void connect() throws SQLException {
        redis.connect();
        this.init();
    }

    public void disconnect() throws SQLException {
        if (!redis.getConnection().isClosed()) {
            redis.getConnection().destroy();
        }
    }

    public void unloadCache() throws SQLException {
        this.unloadFactions();
    }

    public void loadFactions() throws SQLException {

        ResultSet rs = db.fetchAllFactions();

        factions(rs);


    }

    public void reloadFaction(int id) throws SQLException {

        ResultSet rs = db.fetchFactionById(id);

        factions(rs);

    }

    public void unloadFactions() throws SQLException {
//        realzing after spending several days on this problem that I dont even need and really wont ever need it. Whats new
//        If you cant tell by the comments, I spend way too much time on this ffs
//        Jedis jedis = redis.getConnection().getResource();
//        ResultSet factionids = this.db.fetchAllFactionsId();
//        this.db.fetchAllFactionsId().getFetchSize();
//        Bukkit.getLogger().info(String.valueOf(this.db.fetchAllFactionsId().le));
//        Pipeline p = jedis.pipelined();
//        ScanParams scanParams = new ScanParams();
//        ScanResult<String> factionkeys = jedis.scan("0", scanParams.match("faction:*"));
//        factionkeys.getCursor().length();
//        factionkeys.getResult().size();
//        Bukkit.getLogger().info("faction keys get result size");
//        Bukkit.getLogger().info(String.valueOf(factionkeys.getResult().size()));
//        Response<Set<String>> factionkeys = p.keys("faction:*");
//        factionkeys.getResult().listIterator();

//        int rowcount = 0;
//        if (factionids.last()) {
//            rowcount = factionids.getRow();
//            factionids.beforeFirst();
//        }
//        Bukkit.getLogger().info("row count");
//        while (factionids.next()) {
//            Bukkit.getLogger().info(String.valueOf(rowcount));
//        }
//        Bukkit.getLogger().info(String.valueOf(factionkeys.getCursor().length()));
//        Bukkit.getLogger().info(factionkeys.getResult().listIterator().next());
//        Bukkit.getLogger().info(factionkeys.getResult().listIterator().next());
//        Bukkit.getLogger().info(factionkeys.getResult().listIterator().next());
//        while (factionkeys.getResult().listIterator().hasNext()) {
//            Bukkit.getLogger().info(factionkeys.getResult().listIterator().next());
//        }
//        for (int i = 0; i < factionkeys.getResult().size(); i++) {
//            Bukkit.getLogger().info("list iterator next");
//            Bukkit.getLogger().info(factionkeys.getResult().listIterator().next());
//            Bukkit.getLogger().info("list iterator by index i");
//            Bukkit.getLogger().info(String.valueOf(factionkeys.getResult().listIterator(i).next()));
//        }
//
//        p.sync();
//        p.close();
//        jedis.close();
//        try {
//            Response<Set<String>> factionkeys = p.keys("faction:*");
//
//            for (int i = 0; i < factionkeys.get().size(); i++) {
//                p.del(factionkeys.get(i));
//            }
//
//            p.sync();
//        } catch (Exception e) {
//            Bukkit.getLogger().info(e.getMessage());
//        } finally {
//            if (p != null) {
//                p.close();
//                jedis.close();
//            }
//        }
    }

    public void loadPlayer(Player player) {

    }

    public void unloadPlayer() {

    }

    private void factions(ResultSet rs) throws SQLException {

        if (rs != null) {
            Jedis jedis = redis.getConnection().getResource();
            Pipeline p = jedis.pipelined();
            while (rs.next()) {
                try {
                    String factionID = "faction:" + rs.getString(1);
                    p.hset(factionID, "name", rs.getString(2));
                    p.hset(factionID, "tag", rs.getString(3));
                    p.hset(factionID, "membercount", rs.getString(4));
                    p.hset(factionID, "npc", rs.getString(5));
                    p.sync();
                } catch (Exception e) {
                    Bukkit.getLogger().info(e.getMessage());
                } finally {
                    if (p != null) {
                        p.close();
                        jedis.close();
                    }
                }
            }
        }

    }


}

