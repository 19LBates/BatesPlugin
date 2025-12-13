package com.batesy;

import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    private final BatesPlugin plugin;
    private final MiniMessage mm = MiniMessage.miniMessage();
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

        event.joinMessage(mm.deserialize(raw, Placeholder.parsed("player", event.getPlayer().getName())));
    }
}
