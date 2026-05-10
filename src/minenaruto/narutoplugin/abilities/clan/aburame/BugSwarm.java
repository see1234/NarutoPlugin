package minenaruto.narutoplugin.abilities.clan.aburame;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

// Способность 1: Bug Swarm (Рой Насекомых)
public class BugSwarm extends AburameAbility {
    public BugSwarm() {
        super("Рой Насекомых");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        for (Entity entity : player.getNearbyEntities(7, 7, 7)) {
            if (entity instanceof LivingEntity) {
                ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, 1));
                entity.getWorld().spawnParticle(Particle.CRIT, entity.getLocation(), 30);
            }
        }
    }
}
