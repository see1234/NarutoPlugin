package minenaruto.narutoplugin.abilities.Kekkei_Genkai.blaze;

import minenaruto.narutoplugin.abilities.AbilityListener;
import minenaruto.narutoplugin.abilities.Kekkei_Genkai.blaze.BlazeAbility;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.main.Main;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
public class ScorchingWaveAbility extends BlazeAbility {
    
    public ScorchingWaveAbility() {
        super("Опаляющая Волна");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 40, 0, 0, 0, 0)) {
            Vector direction = player.getLocation().getDirection();
            Location start = player.getEyeLocation();
            
            new BukkitRunnable() {
                double distance = 0;
                
                @Override
                public void run() {
                    if (distance >= 15) {
                        cancel();
                        return;
                    }
                    
                    Location point = start.clone().add(direction.clone().multiply(distance));
                    point.getWorld().spawnParticle(Particle.FLAME, point, 5, 0.3, 0.3, 0.3, 0.05);
                    
                    // Урон и поджигание
                    for (Entity entity : point.getWorld().getNearbyEntities(point, 1.5, 1.5, 1.5)) {
                        if (entity instanceof LivingEntity && entity != player) {
                            ((LivingEntity) entity).damage(6);
                            entity.setFireTicks(20 * 3);
                        }
                    }
                    
                    distance += 0.8;
                }
            }.runTaskTimer(Main.getInstance(), 0, 1);
        }
    }
}