package com.playinfinitycraft.infinitycraft;

import com.playinfinitycraft.infinitycraft.database.Cache;
import com.playinfinitycraft.infinitycraft.database.Database;
import com.playinfinitycraft.infinitycraft.events.ItemRightClick;
import com.playinfinitycraft.infinitycraft.events.PlayerJoin;
import com.playinfinitycraft.infinitycraft.events.PlayerLeave;
import com.playinfinitycraft.infinitycraft.events.PlayerMessage;
import com.playinfinitycraft.infinitycraft.general.commands.BaseCommands;
import com.playinfinitycraft.infinitycraft.gui.PlayerMenuUtility;
import com.playinfinitycraft.infinitycraft.gui.commands.KillCommand;
import com.playinfinitycraft.infinitycraft.gui.commands.SuicideCommand;
import com.playinfinitycraft.infinitycraft.gui.listeners.MenuListener;
import com.playinfinitycraft.infinitycraft.items.ItemManager;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.sql.SQLException;
import java.util.HashMap;

public final class InfinityCraft extends JavaPlugin {

    private static final HashMap<Player, PlayerMenuUtility> playerMenuUtilityMap = new HashMap<>();
    public Database db = new Database();
//    public Redis rd = new Redis(db);
    public Cache cache = new Cache(db);
    public static InfinityCraft plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic

        plugin = this;

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        // DATABASE STUFF

        try {
            db.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            cache.connect();
        } catch (JedisConnectionException | SQLException e) {
            e.printStackTrace();
        }

        //ITEMS
        ItemManager.init();


        // COMANDS
        getCommand("suicide").setExecutor(new SuicideCommand());
        getCommand("killplayer").setExecutor(new KillCommand());
        getCommand("ic").setExecutor(new BaseCommands());

        // EVENTS
        getServer().getPluginManager().registerEvents(new MenuListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoin(db, cache), this);
        getServer().getPluginManager().registerEvents(new ItemRightClick(), this);
        getServer().getPluginManager().registerEvents(new PlayerMessage(), this);
        getServer().getPluginManager().registerEvents(new PlayerLeave(cache), this);

        try {
            db.fetchAllFactions();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        try {
            db.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            cache.disconnect();
        } catch (JedisConnectionException e) {
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
