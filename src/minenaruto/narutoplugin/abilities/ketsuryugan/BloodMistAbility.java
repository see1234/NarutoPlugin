package minenaruto.narutoplugin.abilities.ketsuryugan;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import minenaruto.narutoplugin.main.Main;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.*;

public class BloodMistAbility extends KetsuryuganAbility {
    public BloodMistAbility() {
        item = new Item(Material.DIAMOND_HOE, 75, "§7[§6Naruto§7] §cКэтсюрюган Кровавый Туман",
                List.of("§7Использование:§f ПКМ;§7Смена способности:§f ПКМ+SHIFT".split(";")));
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (checkChakra(player, 40)) {
            createBloodMist(player);
            player.sendMessage("§cКэтсюрюган: §4Кровавый туман создан!");
            player.playSound(player.getLocation(), Sound.AMBIENT_CAVE, 1, 0.1f);
        }
    }

    private void createBloodMist(Player player) {
        Location center = player.getLocation();
        
        new BukkitRunnable() {
            int duration = 0;
            
            @Override
            public void run() {
                if (duration++ >= 30) { // 15 секунд
                    cancel();
                    return;
                }
                
                // Создание тумана
                for (int i = 0; i < 30; i++) {
                    double x = Math.random() * 10 - 5;
                    double z = Math.random() * 10 - 5;
                    Location loc = center.clone().add(x, 0, z);
                    player.getWorld().spawnParticle(Particle.REDSTONE, loc, 1, 
                            new Particle.DustOptions(Color.fromRGB(150, 0, 0), 2));
                }
                
                // Эффекты для врагов в тумане
                for (Entity entity : center.getWorld().getNearbyEntities(center, 5, 3, 5)) {
                    if (entity instanceof LivingEntity && entity != player) {
                        ((LivingEntity) entity).addPotionEffect(
                                new PotionEffect(PotionEffectType.BLINDNESS, 20 * 3, 0));
                        ((LivingEntity) entity).addPotionEffect(
                                new PotionEffect(PotionEffectType.CONFUSION, 20 * 5, 0));
                    }
                }
            }
        }.runTaskTimer(Main.getInstance(), 0, 10);
    }
}