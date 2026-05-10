package minenaruto.narutoplugin.abilities.clan.uzushio;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

// Способность 1: Water Whirl (Водяной Вихрь)
public class WaterWhirl extends UzushioAbility {
    public WaterWhirl() {
        super("Водяной Вихрь");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.getWorld().spawnParticle(Particle.WATER_SPLASH, player.getLocation(), 50);
        for (Entity entity : player.getNearbyEntities(4, 4, 4)) {
            if (entity instanceof LivingEntity) {
                ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 1));
            }
        }
    }
}
