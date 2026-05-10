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
public class InfernoTrapAbility extends BlazeAbility {
    
    public InfernoTrapAbility() {
        super("Ловушка Ада");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 35, 0, 0, 0, 0)) {
            Location loc = player.getTargetBlock(null, 15).getLocation().add(0, 1, 0);
            
            // Установка "ловушки"
            ArmorStand trap = (ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
            trap.setVisible(false);
            trap.setGravity(false);
            
            new BukkitRunnable() {
                int count = 0;
                
                @Override
                public void run() {
                    if (count++ >= 20 * 30 || trap.isDead()) { // 30 секунд
                        trap.remove();
                        cancel();
                        return;
                    }
                    
                    // Активация при приближении врага
                    for (Entity entity : trap.getNearbyEntities(3, 3, 3)) {
                        if (entity instanceof LivingEntity && entity != player) {
                            Location center = trap.getLocation();
                            center.getWorld().createExplosion(center, 3f, false, false);
                            trap.remove();
                            cancel();
                            break;
                        }
                    }
                    
                    // Визуальный эффект ожидания
                    if (count % 10 == 0) {
                        trap.getWorld().spawnParticle(Particle.SMOKE_NORMAL, 
                            trap.getLocation(), 2, 0.3, 0.1, 0.3, 0.01);
                    }
                }
            }.runTaskTimer(Main.getInstance(), 0, 1);
        }
    }
}