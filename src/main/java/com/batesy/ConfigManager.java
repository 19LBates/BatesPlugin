package com.batesy;

public class ConfigManager {
    private final BatesPlugin plugin;


    public ConfigManager(BatesPlugin plugin) {
        this.plugin = plugin;
    }

    public void onEnable() {
        plugin.saveDefaultConfig();
        plugin.getConfig().options().copyDefaults(true);
        plugin.saveConfig();
    }

    public boolean getBool(String key) {
        return plugin.getConfig().getBoolean(key, true);
    }

    public void setBool(String key, boolean value) {
        plugin.getConfig().set(key, value);
    }

    public String getStr(String key) {
        return plugin.getConfig().getString(key);
    }

}
