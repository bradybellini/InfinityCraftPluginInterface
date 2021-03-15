package com.playinfinitycraft.infinitycraft.events;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.permissions.Permission;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.bukkit.Bukkit.getServer;

public class PlayerMessage implements Listener {

    @EventHandler
    public static void onPlayerMessage(AsyncPlayerChatEvent event) {

        if (!event.getPlayer().hasPermission("InfinityCraft.chat.global")) {
            if (!event.getMessage().startsWith("/")) {
                String message = event.getMessage();
                event.setCancelled(true);
                Object[] onlinePlayers = new Object[getServer().getOnlinePlayers().size()];
                onlinePlayers = Bukkit.getServer().getOnlinePlayers().toArray();
                for (int i = 0; i < onlinePlayers.length; i++) {
                    Player player = (Player) onlinePlayers[i];
                    if (player.getLocation().distanceSquared(event.getPlayer().getLocation()) < Math.pow(25, 2)) {
                        player.sendMessage(event.getPlayer().getUniqueId(), message);
                    }
                }
            }
        }

    }

}
