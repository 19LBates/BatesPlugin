package com.batesy;

import org.bukkit.plugin.java.JavaPlugin;

public class BatesPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("BatesPlugin enabled! Hello!");
        getCommand("skib").setExecutor(new SkibCommand());
    }

    @Override
    public void onDisable() {
        getLogger().info("BatesPlugin disabled! Bye!");
    }

}
