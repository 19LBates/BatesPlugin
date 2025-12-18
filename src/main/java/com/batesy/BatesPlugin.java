package com.batesy;

import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class BatesPlugin extends JavaPlugin {

    private MiniMessage mm;
    private ConfigManager configMgr;

    @Override
    public void onEnable() {
        mm = MiniMessage.miniMessage();
        configMgr = new ConfigManager(this);
        configMgr.onEnable();

        //Register commands
        Objects.requireNonNull(getCommand("skib")).setExecutor(new SkibCommand(this));
        Objects.requireNonNull(getCommand("grief")).setExecutor(new GriefCommand(this));
        Objects.requireNonNull(getCommand("bates")).setExecutor(new BatesCommand(this));

        //Register listeners
        getServer().getPluginManager().registerEvents(new GriefListener(this, configMgr), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this, configMgr), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(this, configMgr), this);

        getLogger().info("BatesPlugin enabled! Hello!");
    }

    @Override
    public void onDisable() {
        getLogger().info("BatesPlugin disabled! Bye!");
    }

    public MiniMessage mm() {
        return mm;
    }

    public TagResolver playerPlaceholders(Player player) {
        return TagResolver.resolver(
                Placeholder.component("player", player.name())
        );
    }

    public TagResolver pluginPlaceholders() {
        //no paper alternative for getDescription() yet
        //noinspection deprecation
        return TagResolver.resolver(
                Placeholder.parsed("version", getDescription().getVersion())
        );
    }
}
