package minenaruto.narutoplugin.abilities.Kekkei_Genkai.boil;

import minenaruto.narutoplugin.abilities.AbilityListener;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.main.Main;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
public class SteamBarrierAbility extends BoilAbility {
    
    public SteamBarrierAbility() {
        super("Паровой Барьер");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 40, 0, 0, 0, 0)) {
            // Создание защитного поля
            new BukkitRunnable() {
                int duration = 0;
                
                @Override
                public void run() {
                    if (duration++ >= 20 * 15) { // 15 секунд
                        cancel();
                        return;
                    }
                    
                    // Визуальные эффекты
                    for (double angle = 0; angle < 2*Math.PI; angle += Math.PI/6) {
                        Location point = player.getLocation().clone()
                            .add(Math.cos(angle)*3, 0.5, Math.sin(angle)*3);
                        player.getWorld().spawnParticle(Particle.CLOUD, point, 2, 0.1,0.1,0.1,0.05);
                    }
                    
                    // Защита от снарядов
                    for (Entity entity : player.getNearbyEntities(3.5, 3.5, 3.5)) {
                        if (entity instanceof Projectile) {
                            entity.remove();
                            player.getWorld().spawnParticle(Particle.WATER_SPLASH, 
                                entity.getLocation(), 5, 0.2,0.2,0.2,0.1);
                        }
                    }
                }
            }.runTaskTimer(Main.getInstance(), 0, 5);
            
            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20*15, 1));
            player.playSound(player.getLocation(), Sound.BLOCK_BUBBLE_COLUMN_WHIRLPOOL_AMBIENT, 1, 1);
        }
    }
}