package com.batesy;

import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;

import java.util.List;
import java.util.Map;

public class GriefListener implements Listener {

    private final BatesPlugin plugin;
    public GriefListener(BatesPlugin plugin) {
        this.plugin = plugin;
    }
    private static final Map<EntityType, String> blockChangeMobs = Map.of(
            EntityType.ENDERMAN, "enderman",
            EntityType.SHEEP, "sheep",
            EntityType.WITHER, "wither",
            EntityType.ZOMBIE, "zombie"
    );

    @EventHandler
    public void onBlockChange(EntityChangeBlockEvent event) {
        String key = blockChangeMobs.get(event.getEntityType());

        if (key == null) {
            return;
        }

        if (!plugin.getConfig().getBoolean("grief." + key)) {
            event.setCancelled(true);
        }
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
        if (event.getEntityType() != EntityType.FIREBALL || !(((Fireball) event.getEntity()).getShooter() instanceof Ghast)) {
            return;
        }

        if (!plugin.getConfig().getBoolean("grief.ghast")) {
            event.blockList().clear();
        }
    }

    @EventHandler
    public void onVillagerPickupItem(EntityPickupItemEvent event) {
        if (event.getEntityType() != EntityType.VILLAGER) {
            return;
        }

        if (!plugin.getConfig().getBoolean("grief.villager")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onWitherSkullExplode(EntityExplodeEvent event) {
        if (event.getEntityType() != EntityType.WITHER_SKULL || !(((WitherSkull) event.getEntity()).getShooter() instanceof Wither)) {
            return;
        }

        if (!plugin.getConfig().getBoolean("grief.wither")) {
            event.blockList().clear();
        }
    }
}
