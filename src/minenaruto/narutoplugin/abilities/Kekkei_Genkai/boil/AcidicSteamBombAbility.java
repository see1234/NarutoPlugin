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
public class AcidicSteamBombAbility extends BoilAbility {
    
    public AcidicSteamBombAbility() {
        super("Кислотная Бомба");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 50, 0, 0, 0, 0)) {
            Location loc = player.getEyeLocation();
            Vector direction = loc.getDirection().normalize();
            
            // Создание "бомбы"
            ArmorStand bomb = (ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
            bomb.setVisible(false);
            bomb.setGravity(true);
            bomb.setVelocity(direction.multiply(1.2));
            
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (bomb.isOnGround() || bomb.isDead()) {
                        // Взрыв при попадании
                        Location explodeLoc = bomb.getLocation();
                        bomb.remove();
                        
                        // Эффекты взрыва
                        explodeLoc.getWorld().spawnParticle(Particle.WATER_SPLASH, explodeLoc, 50, 1,1,1,0.5);
                        explodeLoc.getWorld().spawnParticle(Particle.CLOUD, explodeLoc, 30, 1,1,1,0.2);
                        explodeLoc.getWorld().playSound(explodeLoc, Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
                        
                        // Урон и эффекты
                        for (Entity entity : explodeLoc.getWorld().getNearbyEntities(explodeLoc, 4, 3, 4)) {
                            if (entity instanceof LivingEntity && entity != player) {
                                ((LivingEntity) entity).damage(10);
                                ((LivingEntity) entity).addPotionEffect(
                                    new PotionEffect(PotionEffectType.POISON, 20*5, 1));
                            }
                        }
                        
                        cancel();
                        return;
                    }
                    
                    // Эффекты полета
                    bomb.getWorld().spawnParticle(Particle.DRIPPING_OBSIDIAN_TEAR, 
                        bomb.getLocation(), 2, 0.1,0.1,0.1,0);
                }
            }.runTaskTimer(Main.getInstance(), 0, 1);
        }
    }
}