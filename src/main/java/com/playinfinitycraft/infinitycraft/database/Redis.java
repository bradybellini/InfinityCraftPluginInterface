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

    private  Database db;
    private JedisPool jedisPool;


    public Redis(Database db) {
        this.db = db;
    }

    public Redis() {

    }

    public void init() throws SQLException {

    }



    private JedisPoolConfig config() {
        final JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMinIdle(5);
        jedisPoolConfig.setMaxIdle(15);
        jedisPoolConfig.setMaxTotal(50);

        return jedisPoolConfig;
    }

    public void connect() {
        jedisPool = new JedisPool(this.config(), InfinityCraft.getPlugin().getConfig().getString("redis-host"),
                InfinityCraft.getPlugin().getConfig().getInt("redis-port"),1000,
                InfinityCraft.getPlugin().getConfig().getString("redis-pass"));
    }


    public JedisPool getConnection() {
        return jedisPool;
    }


}
