package minenaruto.narutoplugin.abilities.clan.otsutsuki;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class TSBTechnique extends OtsutsukiTechnique {
    public TSBTechnique() {
        super("Чёрные Стержни");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        Location loc = player.getEyeLocation();
        Vector direction = loc.getDirection().normalize();

        for (int i = 0; i < 5; i++) {
            Location particleLoc = loc.clone().add(direction.clone().multiply(i));
            player.getWorld().spawnParticle(Particle.SMOKE_LARGE, particleLoc, 3);

            // Проверка на попадание
            for (Entity entity : player.getWorld().getNearbyEntities(particleLoc, 0.5, 0.5, 0.5)) {
                if (entity instanceof LivingEntity && !entity.equals(player)) {
                    ((LivingEntity) entity).addPotionEffect(new PotionEffect(
                            PotionEffectType.WEAKNESS, 200, 1));
                    entity.getWorld().playSound(entity.getLocation(),
                            Sound.BLOCK_ANVIL_PLACE, 0.5f, 1.5f);
                }
            }
        }
    }
}
