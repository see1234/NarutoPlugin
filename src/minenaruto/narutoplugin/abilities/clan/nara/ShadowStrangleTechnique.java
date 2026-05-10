package minenaruto.narutoplugin.abilities.clan.nara;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ShadowStrangleTechnique extends NaraTechnique {
    public ShadowStrangleTechnique() {
        super("Shadow Strangle");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        for (Entity entity : player.getNearbyEntities(7, 3, 7)) {
            if (entity instanceof LivingEntity && !entity.equals(player)) {
                ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 100, 1));
                entity.getWorld().spawnParticle(Particle.SQUID_INK, entity.getLocation(), 30);
            }
        }
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_SCREAM, 0.7f, 1);
    }
}
