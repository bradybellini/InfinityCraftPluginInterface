package com.playinfinitycraft.infinitycraft.events;

import com.playinfinitycraft.infinitycraft.database.Cache;
import com.playinfinitycraft.infinitycraft.database.Database;
import com.playinfinitycraft.infinitycraft.database.Redis;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import redis.clients.jedis.Jedis;

import java.sql.SQLException;

public class PlayerJoin implements Listener {

    private final Database db;
    private final Cache cache;

    public PlayerJoin(Database db, Cache cache) {

        this.db = db;
        this.cache = cache;
    }


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) throws SQLException {
        Bukkit.getLogger().info("Player " + event.getPlayer().getDisplayName() + " has joined");
        db.insertPlayer(event.getPlayer());

//        try (Jedis jedis = rd.getConnection().getResource()) {
//            jedis.set("player", event.getPlayer().getDisplayName());
//        }
//        event.getPlayer().setResourcePack("https://cdn.discordapp.com/attachments/623699580390998038/818374497563312128/Infinity_Textures.zip");

    }

}
