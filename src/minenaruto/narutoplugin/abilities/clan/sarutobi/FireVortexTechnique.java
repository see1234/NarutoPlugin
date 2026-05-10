package minenaruto.narutoplugin.abilities.clan.sarutobi;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class FireVortexTechnique extends SarutobiTechnique {
    public FireVortexTechnique() {
        super("Огненный Вихрь");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        Location center = player.getLocation();

        for (int i = 0; i < 20; i++) {
            double angle = Math.PI * 2 * i / 20;
            double x = Math.cos(angle) * 3;
            double z = Math.sin(angle) * 3;

            Location flameLoc = center.clone().add(x, 0, z);
            player.getWorld().spawnParticle(Particle.FLAME, flameLoc, 5);

            // Поджигает мобов
            for (Entity entity : player.getWorld().getNearbyEntities(flameLoc, 1, 1, 1)) {
                if (entity instanceof LivingEntity && !entity.equals(player)) {
                    entity.setFireTicks(80);
                }
            }
        }

        player.getWorld().playSound(center, Sound.ENTITY_BLAZE_BURN, 1, 1);
    }
}
