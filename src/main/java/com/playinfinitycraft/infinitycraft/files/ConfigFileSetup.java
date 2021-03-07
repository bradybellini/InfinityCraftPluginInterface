package com.playinfinitycraft.infinitycraft.files;

import java.io.IOException;

public class ConfigFileSetup {


    public static void setup() throws IOException {
        ConfigFile.setup();
        ConfigFile.get().addDefault("jdbcurl", "jdbc:postgresql://");
        ConfigFile.get().addDefault("dbusername", "username");
        ConfigFile.get().addDefault("dbpassword", "password");
        ConfigFile.get().options().copyDefaults(true);
        ConfigFile.save();
    }



}
