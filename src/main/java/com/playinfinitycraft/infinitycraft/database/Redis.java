package com.playinfinitycraft.infinitycraft.database;

import com.playinfinitycraft.infinitycraft.InfinityCraft;
import org.bukkit.Bukkit;
import redis.clients.jedis.Connection;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Redis {

//    private String rHost =  InfinityCraft.getPlugin().getConfig().getString("redis-host");
//    private Integer rPort = InfinityCraft.getPlugin().getConfig().getInt("redis-port");
//    private String rPass = InfinityCraft.getPlugin().getConfig().getString("redis-pass");

    private  Database db;
    private JedisPool jedisPool;


    public Redis(Database db) {
        this.db = db;
    }

    public Redis() {

    }

    public void init() throws SQLException {
        loadFactions();



    }

    public void loadFactions() throws SQLException {

        ResultSet rs = db.fetchAllFactions();
//    TODO need to add in the name and tag into a set, using the name:tag value store.
        if (rs != null) {
            while (rs.next()) {
                ResultSetMetaData rsmd = rs.getMetaData();
                try (Jedis jedis = this.getConnection().getResource()) {
                    jedis.sadd("factions", rs.getString(1) + ":" + rs.getString(2));
                }
                Bukkit.getLogger().info(rs.getString(1) + ":" + rs.getString(2));
//                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
//
//                    Bukkit.getLogger().info(rs.getString(i));
//
//                }
            }
        }

    }


    private JedisPoolConfig config() {
        final JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMinIdle(5);
        jedisPoolConfig.setMaxIdle(15);
        jedisPoolConfig.setMaxTotal(50);

        return jedisPoolConfig;
    }

    public void connect() {

        jedisPool = new JedisPool(this.config(), InfinityCraft.getPlugin().getConfig().getString("redis-host")
                , InfinityCraft.getPlugin().getConfig().getInt("redis-port")
                , 1000, InfinityCraft.getPlugin().getConfig().getString("redis-pass"));



    }


    public JedisPool getConnection() {
        return jedisPool;
    }


}
