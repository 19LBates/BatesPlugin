package com.batesy;

import org.bukkit.entity.Creeper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class CreeperGriefListener implements Listener {

    @EventHandler
    public void onCreeperExplode(EntityExplodeEvent event) {
        if (!(event.getEntity() instanceof Creeper)) {
            return;
        }

        BatesPlugin.getInstance().getLogger().info("Creeper explosion detected at " + event.getEntity().getLocation() + " — creeper-grief config = " + BatesPlugin.getInstance().getConfig().getBoolean("creeper-grief"));

        if (!BatesPlugin.getInstance().getConfig().getBoolean("creeper-grief")) {
            event.blockList().clear();
        }
    }
}
