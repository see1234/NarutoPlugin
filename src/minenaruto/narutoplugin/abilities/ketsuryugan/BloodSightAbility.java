package minenaruto.narutoplugin.abilities.ketsuryugan;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import minenaruto.narutoplugin.main.Main;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.List;

public class BloodSightAbility extends KetsuryuganAbility {
    public BloodSightAbility() {
        item = new Item(Material.DIAMOND_HOE, 75, "§7[§6Naruto§7] §cКэтсюрюган Кровавое Зрение",
                List.of("§7Использование:§f ПКМ;§7Смена способности:§f ПКМ+SHIFT".split(";")));
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (checkChakra(player, 25)) {
            activateBloodSight(player);
            player.sendMessage("§cКэтсюрюган: §4Кровавое зрение активировано!");
            player.playSound(player.getLocation(), Sound.ENTITY_EVOKER_PREPARE_ATTACK, 1, 1);
        }
    }

    private void activateBloodSight(Player player) {
        // Эффекты для игрока
        player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 20 * 60, 0));
        player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 20 * 60, 0));
        
        // Подсветка врагов
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!player.isOnline() || !player.hasPotionEffect(PotionEffectType.GLOWING)) {
                    cancel();
                    return;
                }
                
                for (Entity entity : player.getNearbyEntities(30, 30, 30)) {
                    if (entity instanceof LivingEntity && entity != player) {
                        // Подсветка через границы
                        drawBloodLine(player.getEyeLocation(),
                                ((LivingEntity) entity).getEyeLocation(), 
                                player);
                    }
                }
            }
        }.runTaskTimer(Main.getInstance(), 0, 20);
        
        // Автоматическое отключение через 1 минуту
        new BukkitRunnable() {
            @Override
            public void run() {
                player.sendMessage("§cКэтсюрюган: §4Кровавое зрение деактивировано");
                player.playSound(player.getLocation(), Sound.BLOCK_BEACON_DEACTIVATE, 1, 1);
            }
        }.runTaskLater(Main.getInstance(), 20 * 60);
    }
    private void drawBloodLine(Location from, Location to, Player player) {
        World world = from.getWorld();
        double distance = from.distance(to);
        Vector direction = to.toVector().subtract(from.toVector()).normalize();

        new BukkitRunnable() {
            double current = 0;

            @Override
            public void run() {
                if (current >= distance) {
                    cancel();
                    return;
                }

                Vector increment = direction.clone().multiply(current);
                Location point = from.clone().add(increment);

                world.spawnParticle(Particle.REDSTONE, point, 1,
                        new Particle.DustOptions(Color.RED, 1));

                current += 0.3;
            }
        }.runTaskTimer(Main.getInstance(), 0, 1);
    }
}