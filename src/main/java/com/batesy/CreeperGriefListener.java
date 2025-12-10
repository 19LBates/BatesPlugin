package com.batesy;

import org.bukkit.entity.Creeper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class CreeperGriefListener implements Listener {

    private final BatesPlugin plugin;
    public CreeperGriefListener(BatesPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCreeperExplode(EntityExplodeEvent event) {
        if (!(event.getEntity() instanceof Creeper)) {
            return;
        }

        if (!plugin.getConfig().getBoolean("creeper-grief")) {
            event.blockList().clear();
        }
    }
}
