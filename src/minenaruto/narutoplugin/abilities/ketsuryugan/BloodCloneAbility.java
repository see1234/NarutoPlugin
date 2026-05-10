package minenaruto.narutoplugin.abilities.ketsuryugan;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import minenaruto.narutoplugin.main.Main;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.*;

public class BloodCloneAbility extends KetsuryuganAbility {
    public BloodCloneAbility() {
        item = new Item(Material.DIAMOND_HOE, 75, "§7[§6Naruto§7] §cКэтсюрюган Кровавые Клоны",
                List.of("§7Использование:§f ПКМ;§7Смена способности:§f ПКМ+SHIFT".split(";")));
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (checkChakra(player, 35)) {
            spawnBloodClones(player);
            player.sendMessage("§cКэтсюрюган: §4Кровавые клоны созданы!");
            player.playSound(player.getLocation(), Sound.ENTITY_SLIME_SQUISH, 1, 0.5f);
        }
    }

    private void spawnBloodClones(Player player) {
        for (int i = 0; i < 3; i++) {
            ArmorStand clone = (ArmorStand) player.getWorld().spawnEntity(
                    player.getLocation().add(Math.random() * 3 - 1.5, 0, Math.random() * 3 - 1.5), 
                    EntityType.ARMOR_STAND);
            
            clone.setVisible(false);
            clone.setCustomName("§cКровавый Клон");
            clone.setCustomNameVisible(true);
            clone.setGravity(false);
            
            // Эффекты частиц
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (clone.isDead()) {
                        cancel();
                        return;
                    }
                    player.getWorld().spawnParticle(Particle.DRIP_LAVA,
                            clone.getLocation().add(0, 1, 0), 2, 0.2, 0.2, 0.2, 0);
                }
            }.runTaskTimer(Main.getInstance(), 0, 5);
            
            // Поведение клонов
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (clone.isDead()) {
                        cancel();
                        return;
                    }
                    
                    // Поиск ближайшего врага
                    LivingEntity target = findNearestEnemy(player, clone.getLocation(), 10);
                    if (target != null) {
                        Vector direction = target.getLocation().toVector()
                                .subtract(clone.getLocation().toVector()).normalize();
                        clone.teleport(clone.getLocation().add(direction.multiply(0.3)));
                        
                        // Атака при приближении
                        if (clone.getLocation().distance(target.getLocation()) < 1.5) {
                            target.damage(4, player);
                            clone.remove();
                            cancel();
                        }
                    }
                }
            }.runTaskTimer(Main.getInstance(), 0, 5);
            
            // Автоматическое исчезновение через 30 сек
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (!clone.isDead()) {
                        clone.getWorld().spawnParticle(Particle.BLOCK_CRACK, 
                                clone.getLocation(), 20, 0.5, 0.5, 0.5, 0, Material.REDSTONE_BLOCK.createBlockData());
                        clone.remove();
                    }
                }
            }.runTaskLater(Main.getInstance(), 20 * 30);
        }
    }

    private LivingEntity findNearestEnemy(Player owner, Location loc, double radius) {
        LivingEntity nearest = null;
        double nearestDistance = radius;
        
        for (Entity entity : loc.getWorld().getNearbyEntities(loc, radius, radius, radius)) {
            if (entity instanceof LivingEntity && entity != owner && 
                    entity.getLocation().distance(loc) < nearestDistance) {
                nearest = (LivingEntity) entity;
                nearestDistance = entity.getLocation().distance(loc);
            }
        }
        return nearest;
    }
}