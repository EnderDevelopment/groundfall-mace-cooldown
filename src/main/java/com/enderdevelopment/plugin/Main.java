package com.enderdevelopment.plugin;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {
    private static final long COOLDOWN_TICKS = 60L * 20L;
    private final Set<UUID> pendingLanding = new HashSet<>();
    private final Set<UUID> airborneAfterHit = new HashSet<>();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        pendingLanding.clear();
        airborneAfterHit.clear();
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onMaceHit(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player player)
                || !isMace(player.getInventory().getItemInMainHand())) {
            return;
        }
        UUID playerId = player.getUniqueId();
        pendingLanding.add(playerId);
        if (!player.isOnGround()) {
            airborneAfterHit.add(playerId);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        UUID playerId = player.getUniqueId();
        if (!pendingLanding.contains(playerId)) {
            return;
        }
        if (!player.isOnGround()) {
            airborneAfterHit.add(playerId);
            return;
        }
        if (airborneAfterHit.contains(playerId)) {
            applyCooldown(player);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        UUID playerId = event.getPlayer().getUniqueId();
        pendingLanding.remove(playerId);
        airborneAfterHit.remove(playerId);
    }

    private void applyCooldown(Player player) {
        player.setCooldown(Material.MACE, (int) COOLDOWN_TICKS);
        UUID playerId = player.getUniqueId();
        pendingLanding.remove(playerId);
        airborneAfterHit.remove(playerId);
    }

    private boolean isMace(ItemStack item) {
        return item != null && item.getType() == Material.MACE;
    }
}
