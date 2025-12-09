package com.batesy;

import org.bukkit.plugin.java.JavaPlugin;

public class BatesPlugin extends JavaPlugin {

    private static BatesPlugin instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        getLogger().info("BatesPlugin enabled! Hello!");
        getCommand("skib").setExecutor(new SkibCommand());
        getCommand("creepergrief").setExecutor(new CreeperGriefCommand());
        getServer().getPluginManager().registerEvents(new CreeperGriefListener(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("BatesPlugin disabled! Bye!");
    }

    public static BatesPlugin getInstance() {
        return instance;
    }

}
