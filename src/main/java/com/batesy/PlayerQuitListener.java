package com.batesy;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {
    private final BatesPlugin plugin;
    private final ConfigManager configMgr;

    public PlayerQuitListener(BatesPlugin plugin, ConfigManager configMgr) {
        this.plugin = plugin;
        this.configMgr = configMgr;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (!configMgr.getBool("quit-message.enabled")) {
            return;
        }

        String raw = configMgr.getStr("quit-message.message");

        if (raw == null || raw.isBlank()) {
            event.quitMessage(null);
            return;
        }

        event.quitMessage(plugin.mm().deserialize(raw, plugin.playerPlaceholders(event.getPlayer())));
    }
}
