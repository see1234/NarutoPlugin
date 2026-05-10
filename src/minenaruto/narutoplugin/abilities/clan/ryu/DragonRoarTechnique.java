package minenaruto.narutoplugin.abilities.clan.ryu;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class DragonRoarTechnique extends RyuTechnique {
    public DragonRoarTechnique() {
        super("Рев Дракона");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        for (Entity entity : player.getNearbyEntities(8, 4, 8)) {
            if (entity instanceof LivingEntity && !entity.equals(player)) {
                ((LivingEntity) entity).addPotionEffect(new PotionEffect(
                        PotionEffectType.WEAKNESS, 200, 1));
                entity.setVelocity(entity.getLocation().toVector()
                        .subtract(player.getLocation().toVector()).normalize().multiply(0.8));
            }
        }

        // Эффекты звуковой волны
        player.getWorld().spawnParticle(Particle.SONIC_BOOM, player.getLocation(), 10);
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 2, 0.7f);
    }
}
