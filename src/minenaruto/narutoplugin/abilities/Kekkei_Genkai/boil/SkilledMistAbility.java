package minenaruto.narutoplugin.abilities.Kekkei_Genkai.boil;

import minenaruto.narutoplugin.abilities.AbilityListener;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.main.Main;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class SkilledMistAbility extends BoilAbility {
    
    public SkilledMistAbility() {
        super("Искусный Туман");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 35, 0, 0, 0, 0)) {
            Location center = player.getLocation();
            
            // Создание тумана
            new BukkitRunnable() {
                int radius = 0;
                
                @Override
                public void run() {
                    if (radius >= 7) {
                        cancel();
                        return;
                    }
                    
                    for (double angle = 0; angle < 2*Math.PI; angle += Math.PI/8) {
                        Location point = center.clone().add(Math.cos(angle)*radius, 0.5, Math.sin(angle)*radius);
                        point.getWorld().spawnParticle(Particle.CLOUD, point, 2, 0.3, 0.5, 0.3, 0);
                    }
                    
                    // Эффекты для врагов
                    for (Entity entity : center.getWorld().getNearbyEntities(center, radius, 2, radius)) {
                        if (entity instanceof LivingEntity && entity != player) {
                            ((LivingEntity) entity).addPotionEffect(
                                new PotionEffect(PotionEffectType.WEAKNESS, 40, 1));
                            entity.getWorld().spawnParticle(Particle.WATER_BUBBLE, 
                                entity.getLocation().add(0,1,0), 3, 0.3,0.3,0.3,0);
                        }
                    }
                    
                    radius++;
                }
            }.runTaskTimer(Main.getInstance(), 0, 5);
            
            player.playSound(center, Sound.AMBIENT_UNDERWATER_ENTER, 1, 0.7f);
        }
    }
}