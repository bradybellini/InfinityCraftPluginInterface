package com.playinfinitycraft.infinitycraft.events;

import com.playinfinitycraft.infinitycraft.database.Cache;
import com.playinfinitycraft.infinitycraft.database.Redis;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import redis.clients.jedis.Jedis;

public class PlayerLeave implements Listener {

    private Cache cache;

    public PlayerLeave(Cache cache) {

        this.cache = cache;

    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
//        try (Jedis jedis = cache.getConnection().getResource()) {
//            jedis.del("player");
//        }
    }

}
