package minenaruto.narutoplugin.abilities.ketsuryugan;

import minenaruto.narutoplugin.ParticleEffect;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import minenaruto.narutoplugin.main.Main;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.List;

public class BloodDragonAbility extends KetsuryuganAbility {
    public BloodDragonAbility() {
        item = new Item(Material.DIAMOND_HOE, 75, "§7[§6Naruto§7] §cКэтсюрюган Кровавый Дракон",
                List.of("§7Использование:§f ПКМ;§7Смена способности:§f ПКМ+SHIFT".split(";")));
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (checkChakra(player, 50)) {
            summonBloodDragon(player);
            player.sendMessage("§cКэтсюрюган: §4Кровавый дракон призван!");
            player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 0.5f, 0.8f);
        }
    }

    private void summonBloodDragon(Player player) {
        Location start = player.getLocation().add(0, 2, 0);
        Vector direction = player.getLocation().getDirection().normalize();
        
        // Создаем "голову" дракона
        ArmorStand head = (ArmorStand) start.getWorld().spawnEntity(start, EntityType.ARMOR_STAND);
        head.setVisible(false);
        head.setGravity(false);
        head.setSmall(false);
        
        new BukkitRunnable() {
            int segments = 0;
            int length = 0;
            
            @Override
            public void run() {
                if (length++ >= 20) {
                    cancel();
                    head.remove();
                    return;
                }
                
                // Движение головы вперед
                head.teleport(head.getLocation().add(direction.clone().multiply(0.8)));
                
                // Создание "тела" дракона
                if (segments++ % 3 == 0) {
                    Location segmentLoc = head.getLocation().clone()
                            .subtract(direction.clone().multiply(length * 0.3));
                    createDragonSegment(segmentLoc);
                }
                
                // Эффекты и урон
                player.getWorld().spawnParticle(Particle.DRIP_LAVA,
                        head.getLocation(), 5, 0.3, 0.3, 0.3, 0);
                
                for (Entity entity : head.getNearbyEntities(1.5, 1.5, 1.5)) {
                    if (entity instanceof LivingEntity && entity != player) {
                        ((LivingEntity) entity).damage(8, player);
                        ((LivingEntity) entity).setFireTicks(20 * 3);
                    }
                }
            }
        }.runTaskTimer(Main.getInstance(), 0, 2);
    }

    private void createDragonSegment(Location loc) {
        loc.getWorld().spawnParticle(Particle.REDSTONE, loc, 3, 
                new Particle.DustOptions(Color.RED, 2));
        loc.getWorld().spawnParticle(Particle.DAMAGE_INDICATOR, loc, 2, 0.2, 0.2, 0.2, 0);
    }
}