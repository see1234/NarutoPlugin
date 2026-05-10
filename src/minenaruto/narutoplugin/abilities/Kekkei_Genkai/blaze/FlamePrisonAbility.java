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
public class FlamePrisonAbility extends BlazeAbility {
    
    public FlamePrisonAbility() {
        super("Пламенная Тюрьма");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 60, 0, 0, 0, 0)) {
            Location center = player.getTargetBlock(null, 10).getLocation();
            
            // Создание огненного куба
            for (double x = -3; x <= 3; x += 0.5) {
                for (double y = 0; y <= 4; y += 0.5) {
                    for (double z = -3; z <= 3; z += 0.5) {
                        if (x == -3 || x == 3 || y == 0 || y == 4 || z == -3 || z == 3) {
                            Location partLoc = center.clone().add(x, y, z);
                            partLoc.getWorld().spawnParticle(Particle.FLAME, partLoc, 1, 0, 0, 0, 0);
                        }
                    }
                }
            }
            
            // Ловля мобов внутри
            new BukkitRunnable() {
                int duration = 0;
                
                @Override
                public void run() {
                    if (duration++ >= 20 * 8) { // 8 секунд
                        cancel();
                        return;
                    }
                    
                    for (Entity entity : center.getWorld().getNearbyEntities(center, 3, 2, 3)) {
                        if (entity instanceof LivingEntity && entity != player) {
                            // Урон и отталкивание от стен
                            if (entity.getLocation().distance(center) > 2.8) {
                                Vector push = entity.getLocation().toVector()
                                    .subtract(center.toVector()).normalize().multiply(0.3);
                                entity.setVelocity(push);
                                ((LivingEntity) entity).damage(3);
                            }
                            
                            entity.setFireTicks(20 * 2);
                        }
                    }
                    
                    // Эффекты частиц
                    if (duration % 5 == 0) {
                        center.getWorld().spawnParticle(Particle.LAVA, center, 10, 3, 2, 3, 0);
                    }
                }
            }.runTaskTimer(Main.getInstance(), 0, 1);
        }
    }
}