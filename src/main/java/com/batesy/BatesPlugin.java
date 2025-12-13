package com.batesy;

import org.bukkit.plugin.java.JavaPlugin;

public class BatesPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        saveDefaultConfig();
        getLogger().info("BatesPlugin enabled! Hello!");
        getCommand("skib").setExecutor(new SkibCommand());
        getCommand("creepergrief").setExecutor(new CreeperGriefCommand(this));
        getServer().getPluginManager().registerEvents(new CreeperGriefListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("BatesPlugin disabled! Bye!");
    }
}
