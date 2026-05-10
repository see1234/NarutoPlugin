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

public class BoilingJetFistAbility extends BoilAbility {
    
    public BoilingJetFistAbility() {
        super("Кипящий Удар");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 25, 0, 0, 0, 0)) {
            // Эффект удара
            Location loc = player.getEyeLocation();
            Vector direction = loc.getDirection().normalize();
            
            player.setVelocity(direction.multiply(1.2).setY(0.3));
            player.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, loc, 20, 0.5,0.5,0.5,0.1);
            player.playSound(loc, Sound.BLOCK_LAVA_EXTINGUISH, 1, 1);
            
            // Урон при попадании
            new BukkitRunnable() {
                @Override
                public void run() {
                    for (Entity entity : player.getNearbyEntities(2, 2, 2)) {
                        if (entity instanceof LivingEntity && entity != player) {
                            ((LivingEntity) entity).damage(8);
                            entity.setFireTicks(20 * 2);
                            entity.getWorld().spawnParticle(Particle.WATER_SPLASH, 
                                entity.getLocation(), 15, 0.5,0.5,0.5,0.2);
                        }
                    }
                }
            }.runTaskLater(Main.getInstance(), 2);
        }
    }
}