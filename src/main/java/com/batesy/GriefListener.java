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
    private final ConfigManager configMgr;

    public GriefListener(BatesPlugin plugin, ConfigManager configMgr) {
        this.plugin = plugin;
        this.configMgr = configMgr;
    }

    @EventHandler
    public void onChangeBlock(EntityChangeBlockEvent event) {
        EntityType type = event.getEntityType();
        if (type == EntityType.PLAYER) return;
        String key = type.toString().toLowerCase();

        if (!configMgr.getBool("grief." + key)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onExplode(EntityExplodeEvent event) {
        Entity entity = event.getEntity();
        EntityType type = entity.getType();
        if (type == EntityType.PLAYER) return;
        String key = type.toString().toLowerCase();

        //Entity is a projectile
        if (entity instanceof Projectile projectile) {
            ProjectileSource source = projectile.getShooter();
            if (!(source instanceof Entity shooter)) return;

            EntityType shooterType = shooter.getType();
            String shooterKey = shooterType.toString().toLowerCase();

            if (!configMgr.getBool("grief." + shooterKey)) {
                int size = event.blockList().size();
                System.out.println("Prevented " + size + " blocks from being destroyed by " + shooterType + "'s " + shooterKey);
                event.blockList().clear();
            }

            return;
        }

        //Entity itself explodes
        if (!configMgr.getBool("grief." + key)) {
            event.blockList().clear();
        }
    }

    @EventHandler
    public void onPickupItem(EntityPickupItemEvent event) {
        EntityType type = event.getEntityType();
        if (type == EntityType.PLAYER) return;
        String key = type.toString().toLowerCase();

        if (!configMgr.getBool("grief." + key)) {
            event.setCancelled(true);
        }
    }
}
