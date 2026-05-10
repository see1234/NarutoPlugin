package minenaruto.narutoplugin.abilities.clan.nara;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class ShadowSewingTechnique extends NaraTechnique {
    public ShadowSewingTechnique() {
        super("Shadow Sewing");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        Location loc = player.getEyeLocation();
        World world = player.getWorld();

        for (int i = 0; i < 10; i++) {
            Location particleLoc = loc.clone().add(loc.getDirection().multiply(i * 0.5));
            world.spawnParticle(Particle.SQUID_INK, particleLoc, 3);

            // Проверка на попадание
            for (Entity entity : world.getNearbyEntities(particleLoc, 0.5, 0.5, 0.5)) {
                if (entity instanceof LivingEntity && !entity.equals(player)) {
                    ((LivingEntity) entity).damage(3);
                    entity.setVelocity(loc.getDirection().multiply(0.3));
                }
            }
        }
        world.playSound(loc, Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1.2f);
    }
}
