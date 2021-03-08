package com.playinfinitycraft.infinitycraft;

import com.playinfinitycraft.infinitycraft.database.Database;
import com.playinfinitycraft.infinitycraft.events.PlayerJoin;
import com.playinfinitycraft.infinitycraft.files.ConfigFileSetup;
import com.playinfinitycraft.infinitycraft.gui.PlayerMenuUtility;
import com.playinfinitycraft.infinitycraft.gui.commands.KillCommand;
import com.playinfinitycraft.infinitycraft.gui.commands.SuicideCommand;
import com.playinfinitycraft.infinitycraft.gui.listeners.MenuListener;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public final class InfinityCraft extends JavaPlugin {

    private static final HashMap<Player, PlayerMenuUtility> playerMenuUtilityMap = new HashMap<>();
    public Database db = new Database();
    public static InfinityCraft plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic

        plugin = this;

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        try {
            db.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        // COMANDS
        getCommand("suicide").setExecutor(new SuicideCommand());
        getCommand("killplayer").setExecutor(new KillCommand());

        // EVENTS
        getServer().getPluginManager().registerEvents(new MenuListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoin(db), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        try {
            db.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static PlayerMenuUtility getPlayerMenuUtility(Player p) {
        PlayerMenuUtility playerMenuUtility;

        if(playerMenuUtilityMap.containsKey(p)) {
            return playerMenuUtilityMap.get(p);
        } else {

            playerMenuUtility = new PlayerMenuUtility(p);
            playerMenuUtilityMap.put(p, playerMenuUtility);

            return playerMenuUtility;

        }
    }

    public static InfinityCraft getPlugin() {
        return plugin;
    }

}
