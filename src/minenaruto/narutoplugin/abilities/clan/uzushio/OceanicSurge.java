package minenaruto.narutoplugin.abilities.clan.uzushio;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

// Способность 5: Oceanic Surge (Океанский Напор)
public class OceanicSurge extends UzushioAbility {
    public OceanicSurge() {
        super("Океанский Напор");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        for (Entity entity : player.getNearbyEntities(6, 6, 6)) {
            if (entity instanceof LivingEntity) {
                ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 100, 1));
            }
        }
        player.getWorld().spawnParticle(Particle.WATER_WAKE, player.getLocation(), 50);
    }
}
