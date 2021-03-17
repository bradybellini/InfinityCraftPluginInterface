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

    public void disconnect() {
        if (!redis.getConnection().isClosed()) {
            this.unloadFactions();
            redis.getConnection().destroy();

        }
    }

    public void loadFactions() throws SQLException {

        ResultSet rs = db.fetchAllFactions();

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

    public void unloadFactions() {
//        TODO currently getting this error while trying to shutdown the pool? https://prnt.sc/10mtfea
        Jedis jedis = redis.getConnection().getResource();
        Pipeline p = jedis.pipelined();
        ScanParams scanParams = new ScanParams();
        ScanResult<String> factionkeys = jedis.scan("0", scanParams.match("faction:*"));
//        Response<Set<String>> factionkeys = p.keys("faction:*");
        factionkeys.getResult().listIterator();
        Bukkit.getLogger().info(String.valueOf(factionkeys.getCursor().length()));
        Bukkit.getLogger().info(factionkeys.getResult().listIterator().next());

        p.sync();
        p.close();
        jedis.close();
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

}
