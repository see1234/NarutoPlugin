package minenaruto.narutoplugin.abilities.clan.nara;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class ShadowGraspTechnique extends NaraTechnique {
    public ShadowGraspTechnique() {
        super("Shadow Grasp");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.getWorld().getNearbyEntities(player.getLocation(), 5, 3, 5).forEach(entity -> {
            if (entity instanceof LivingEntity && !entity.equals(player)) {
                entity.setVelocity(new Vector(0, 0.1, 0)); // Подбрасывание
                ((LivingEntity) entity).addPotionEffect(new PotionEffect(
                        PotionEffectType.LEVITATION, 60, 0, false, false));
                entity.getWorld().spawnParticle(Particle.SMOKE_LARGE, entity.getLocation(), 20);
            }
        });
        player.getWorld().playSound(player.getLocation(), Sound.BLOCK_CONDUIT_AMBIENT, 1, 0.8f);
    }
}
