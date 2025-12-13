package com.batesy;

import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {
    private final BatesPlugin plugin;
    private final MiniMessage mm = MiniMessage.miniMessage();
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

        event.quitMessage(mm.deserialize(raw, Placeholder.parsed("player", event.getPlayer().getName())));
    }
}
