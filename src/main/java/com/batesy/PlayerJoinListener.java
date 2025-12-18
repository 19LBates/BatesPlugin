package com.batesy;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    private final BatesPlugin plugin;
    private final ConfigManager configMgr;

    public PlayerJoinListener(BatesPlugin plugin, ConfigManager configMgr) {
        this.plugin = plugin;
        this.configMgr = configMgr;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (!configMgr.getBool("join-message.enabled")) {
            return;
        }

        String raw = configMgr.getStr("join-message.message");

        if (raw == null || raw.isBlank()) {
            event.joinMessage(null);
            return;
        }

        event.joinMessage(plugin.mm().deserialize(raw, plugin.playerPlaceholders(event.getPlayer())));
    }
}
