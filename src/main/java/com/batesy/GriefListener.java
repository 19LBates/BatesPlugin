package com.batesy;

import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.projectiles.ProjectileSource;

public class GriefListener implements Listener {

    private final BatesPlugin plugin;

    public GriefListener(BatesPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onChangeBlock(EntityChangeBlockEvent event) {
        EntityType type = event.getEntityType();
        if (type == EntityType.PLAYER) return;

        if (plugin.config().isGriefMobDisabled(type)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onExplode(EntityExplodeEvent event) {
        Entity entity = event.getEntity();
        EntityType type = entity.getType();
        if (type == EntityType.PLAYER) return;

        //Entity is a projectile
        if (entity instanceof Projectile projectile) {
            ProjectileSource source = projectile.getShooter();
            if (!(source instanceof Entity shooter)) return;

            EntityType shooterType = shooter.getType();
            if (plugin.config().isGriefMobDisabled(shooterType)) {
                event.blockList().clear();
            }

            return;
        }

        //Entity itself explodes
        if (plugin.config().isGriefMobDisabled(type)) {
            event.blockList().clear();
        }
    }

    @EventHandler
    public void onPickupItem(EntityPickupItemEvent event) {
        EntityType type = event.getEntityType();
        if (type == EntityType.PLAYER) return;

        if (plugin.config().isGriefMobDisabled(type)) {
            event.setCancelled(true);
        }
    }
}
