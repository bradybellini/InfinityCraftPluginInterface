package com.playinfinitycraft.infinitycraft.events;

import com.playinfinitycraft.infinitycraft.InfinityCraft;
import com.playinfinitycraft.infinitycraft.database.Database;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.SQLException;

public class PlayerJoin implements Listener {

    private final Database db;

    public PlayerJoin(Database db) {
        this.db = db;
    }


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) throws SQLException {
        Bukkit.getLogger().info("Player " + event.getPlayer().getDisplayName() + " has joined");
        db.insertPlayer(event.getPlayer());

    }

}
