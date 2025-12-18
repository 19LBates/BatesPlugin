package com.batesy;

import org.bukkit.entity.EntityType;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConfigManager {
    private final BatesPlugin plugin;
    private final Set<EntityType> disabledGriefMobs = new HashSet<>();

    public ConfigManager(BatesPlugin plugin) {
        this.plugin = plugin;
    }

    public void load() {
        plugin.saveDefaultConfig();
        plugin.getConfig().options().copyDefaults(true);
        plugin.saveConfig();

        for (String s : plugin.getConfig().getStringList("grief.disabled-mobs")) {
            try {
                EntityType type = EntityType.valueOf(s.toUpperCase());
                disabledGriefMobs.add(type);
            } catch (IllegalArgumentException e) {
                plugin.getLogger().warning("Invalid entity type in grief.disabled-mobs: " + s);
            }
        }
    }

    public void save() {
        List<String> disabledGriefMobList = disabledGriefMobs.stream()
                .map(EntityType::name)
                .map(String::toLowerCase)
                .sorted()
                .toList();
        plugin.getConfig().set("grief.disabled-mobs", disabledGriefMobList);
        plugin.saveConfig();
    }

    public boolean isGriefMobDisabled(EntityType type) {
        return disabledGriefMobs.contains(type);
    }

    public void disableMobGrief(EntityType type) {
        boolean changed = disabledGriefMobs.add(type);
        if (changed) save();
    }

    public void enableMobGrief(EntityType type) {
        boolean changed = disabledGriefMobs.remove(type);
        if (changed) save();
    }

    public boolean isJoinMessageEnabled() {
        return plugin.getConfig().getBoolean("join-message.enabled");
    }

    public String getJoinMessage() {
        return plugin.getConfig().getString("join-message.message");
    }

    public boolean isQuitMessageEnabled() {
        return plugin.getConfig().getBoolean("quit-message.enabled");
    }

    public String getQuitMessage() {
        return plugin.getConfig().getString("quit-message.message");
    }
}
