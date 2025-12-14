package com.batesy;

import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class BatesPlugin extends JavaPlugin {

    private MiniMessage mm;

    @Override
    public void onEnable() {
        mm = MiniMessage.miniMessage();
        saveDefaultConfig();
        getLogger().info("BatesPlugin enabled! Hello!");
        Objects.requireNonNull(getCommand("skib")).setExecutor(new SkibCommand());
        Objects.requireNonNull(getCommand("grief")).setExecutor(new GriefCommand(this));
        Objects.requireNonNull(getCommand("bates")).setExecutor(new BatesCommand(this));
        getServer().getPluginManager().registerEvents(new GriefListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(this), this);
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
                Placeholder.parsed("version", this.getDescription().getVersion())
        );
    }
}
