package com.playinfinitycraft.infinitycraft.files;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigFile {

    private static File file;

    private static FileConfiguration infinityConfig;

    public static void setup() throws IOException {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("InfinityCraft").getDataFolder(), "infinityconfig.yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                Bukkit.getLogger().info(e.toString());
            }
        }
        infinityConfig = YamlConfiguration.loadConfiguration(file);

    }

    public static FileConfiguration get() {
        return infinityConfig;
    }

    public static void save() throws IOException {
        infinityConfig.save(file);
    }

    public static void reload() {
        infinityConfig = YamlConfiguration.loadConfiguration(file);
    }

}
