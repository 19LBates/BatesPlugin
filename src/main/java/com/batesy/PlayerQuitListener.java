package com.batesy;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {
    private final BatesPlugin plugin;
    public PlayerQuitListener(BatesPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (!plugin.getConfig().getBoolean("quit-message.enabled")) {
            return;
        }

        String raw = plugin.getConfig().getString("quit-message.message");

        if (raw == null || raw.isBlank()) {
            event.quitMessage(null);
            return;
        }

        event.quitMessage(plugin.mm().deserialize(raw, plugin.playerPlaceholders(event.getPlayer())));
    }
}
