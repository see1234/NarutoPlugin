package minenaruto.narutoplugin.abilities.Kekkei_Genkai.boil;

import minenaruto.narutoplugin.abilities.AbilityListener;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.main.Main;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class ScaldingGeyserAbility extends BoilAbility {
    
    public ScaldingGeyserAbility() {
        super("Ошпаривающий Гейзер");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 45, 0, 0, 0, 0)) {
            Location target = player.getTargetBlock(null, 12).getLocation().add(0.5, 0, 0.5);
            
            // Анимация гейзера
            new BukkitRunnable() {
                int height = 0;
                
                @Override
                public void run() {
                    if (height >= 6) {
                        // Финальный взрыв
                        target.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, target.clone().add(0,3,0), 1);
                        for (Entity entity : target.getWorld().getNearbyEntities(target, 2, 4, 2)) {
                            if (entity instanceof LivingEntity && entity != player) {
                                ((LivingEntity) entity).damage(12);
                                entity.setVelocity(new Vector(0, 1, 0));
                            }
                        }
                        cancel();
                        return;
                    }
                    
                    // Столб пара
                    for (int y = 0; y <= height; y++) {
                        Location point = target.clone().add(0, y, 0);
                        point.getWorld().spawnParticle(Particle.CLOUD, point, 5, 0.3,0,0.3,0.1);
                    }
                    
                    height++;
                }
            }.runTaskTimer(Main.getInstance(), 0, 3);
            
            player.playSound(target, Sound.BLOCK_LAVA_POP, 1, 0.5f);
        }
    }
}