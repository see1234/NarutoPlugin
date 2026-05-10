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

public class BurningAshAbility extends BlazeAbility {

    public BurningAshAbility() {
        super("Горячая Зола");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 30, 0, 0, 0, 0)) {
            Location loc = player.getLocation();

            // Эффекты активации
            player.getWorld().spawnParticle(Particle.ASH, loc, 100, 3, 1, 3, 0.2);
            player.playSound(loc, Sound.BLOCK_FIRE_EXTINGUISH, 1, 0.5f);

            new BukkitRunnable() {
                int ticks = 0;

                @Override
                public void run() {
                    if (ticks++ >= 20 * 10) { // 10 секунд
                        cancel();
                        return;
                    }

                    // Постепенное распространение золы
                    for (Entity entity : loc.getWorld().getNearbyEntities(loc, 5, 3, 5)) {
                        if (entity instanceof LivingEntity && entity != player) {
                            ((LivingEntity) entity).addPotionEffect(
                                    new PotionEffect(PotionEffectType.BLINDNESS, 40, 0));
                            entity.getWorld().spawnParticle(Particle.SMOKE_LARGE,
                                    entity.getLocation(), 3, 0.3, 0.3, 0.3, 0.01);
                        }
                    }
                }
            }.runTaskTimer(Main.getInstance(), 0, 20);
        }
    }
}