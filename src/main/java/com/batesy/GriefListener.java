package com.batesy;

import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

public class GriefListener implements Listener {

    private final BatesPlugin plugin;
    public GriefListener(BatesPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCreeperExplode(EntityExplodeEvent event) {
        if (!(event.getEntity() instanceof Creeper)) {
            return;
        }

        if (!plugin.getConfig().getBoolean("grief.creeper")) {
            event.blockList().clear();
        }
    }

    @EventHandler
    public void onGhastFireballExplode(EntityExplodeEvent event) {
        if (!(event.getEntity() instanceof Fireball) || !(((Fireball) event.getEntity()).getShooter() instanceof Ghast)) {
            return;
        }

        if (!plugin.getConfig().getBoolean("grief.ghast")) {
            event.blockList().clear();
        }
    }

    @EventHandler
    public void onWitherSkullExplode(EntityExplodeEvent event) {
        if (!(event.getEntity() instanceof WitherSkull) || !(((WitherSkull) event.getEntity()).getShooter() instanceof Wither)) {
            return;
        }

        if (!plugin.getConfig().getBoolean("grief.wither")) {
            event.blockList().clear();
        }
    }

    @EventHandler
    public void onWitherBlockBreak(EntityChangeBlockEvent event) {
        if (!(event.getEntity() instanceof Wither)) return;

        if (!plugin.getConfig().getBoolean("grief.wither")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onEndermanChangeBlock(EntityChangeBlockEvent event) {
        if (!(event.getEntity() instanceof Enderman)) {
            return;
        }

        if (!plugin.getConfig().getBoolean("grief.enderman")) {
            event.setCancelled(true);
        }
    }
}
