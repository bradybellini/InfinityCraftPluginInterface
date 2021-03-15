package com.playinfinitycraft.infinitycraft.events;

import com.playinfinitycraft.infinitycraft.database.Redis;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import redis.clients.jedis.Jedis;

public class PlayerLeave implements Listener {

    private Redis rd;

    public PlayerLeave(Redis rd) {

        this.rd = rd;

    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        try (Jedis jedis = rd.getConnection().getResource()) {
            jedis.del("player");
        }
    }

}
