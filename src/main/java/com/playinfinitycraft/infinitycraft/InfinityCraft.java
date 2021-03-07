package com.playinfinitycraft.infinitycraft;

import com.playinfinitycraft.infinitycraft.gui.PlayerMenuUtility;
import com.playinfinitycraft.infinitycraft.gui.commands.KillCommand;
import com.playinfinitycraft.infinitycraft.gui.commands.SuicideCommand;
import com.playinfinitycraft.infinitycraft.gui.listeners.MenuListener;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class InfinityCraft extends JavaPlugin {

    private static final HashMap<Player, PlayerMenuUtility> playerMenuUtilityMap = new HashMap<>();

    private static InfinityCraft plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic

        plugin = this;

        getCommand("suicide").setExecutor(new SuicideCommand());
        getCommand("killplayer").setExecutor(new KillCommand());

        getServer().getPluginManager().registerEvents(new MenuListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
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
