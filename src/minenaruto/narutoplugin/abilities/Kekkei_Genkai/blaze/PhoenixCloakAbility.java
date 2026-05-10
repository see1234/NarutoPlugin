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
public class PhoenixCloakAbility extends BlazeAbility {
    
    public PhoenixCloakAbility() {
        super("Плащ Феникса");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 50, 0, 0, 0, 0)) {
            // Баффы игроку
            player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 20 * 30, 0));
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 30, 1));

            // Визуальные эффекты
            new BukkitRunnable() {
                int count = 0;
                @Override
                public void run() {
                    if (count++ >= 20 * 30) { // 30 секунд
                        cancel();
                        return;
                    }
                    if (!player.isOnline()) {
                        cancel();
                        return;
                    }
                    
                    player.getWorld().spawnParticle(Particle.FLAME, 
                        player.getLocation().add(0, 1, 0), 10, 0.5, 0.5, 0.5, 0.01);
                }
            }.runTaskTimer(Main.getInstance(), 0, 5);
            
            player.sendMessage("§6Blaze Release: Плащ Феникса активирован!");
        }
    }
}