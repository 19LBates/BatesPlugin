package com.batesy;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    private final BatesPlugin plugin;
    public PlayerJoinListener(BatesPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (!plugin.getConfig().getBoolean("join-message.enabled")) {
            return;
        }

        String raw = plugin.getConfig().getString("join-message.message");

        if (raw == null || raw.isBlank()) {
            event.joinMessage(null);
            return;
        }

        event.joinMessage(plugin.mm().deserialize(raw, plugin.playerPlaceholders(event.getPlayer())));
    }
}
