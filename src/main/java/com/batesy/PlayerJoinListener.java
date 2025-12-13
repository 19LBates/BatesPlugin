package com.batesy;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
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
        event.joinMessage(MiniMessage.miniMessage().deserialize("<gradient:#41FF6D:#72BAFF>" + event.getPlayer().getName() + " hopped on</gradient>"));
    }
}
