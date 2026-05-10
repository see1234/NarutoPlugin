package minenaruto.narutoplugin.abilities.clan.otsutsuki;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class AmenotejikaraTechnique extends OtsutsukiTechnique {
    public AmenotejikaraTechnique() {
        super("Амэнотэдзикара");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        Entity target = null;
        for (Entity entity : player.getNearbyEntities(15, 5, 15)) {
            if (entity instanceof LivingEntity && !entity.equals(player)) {
                target = entity;
                break;
            }
        }

        if (target != null) {
            Location playerLoc = player.getLocation();
            Location targetLoc = target.getLocation();

            // Меняем местами
            player.teleport(targetLoc);
            target.teleport(playerLoc);

            // Эффекты телепортации
            player.getWorld().spawnParticle(Particle.REVERSE_PORTAL, playerLoc, 30);
            player.getWorld().spawnParticle(Particle.REVERSE_PORTAL, targetLoc, 30);
            player.getWorld().playSound(playerLoc, Sound.ENTITY_ENDERMAN_TELEPORT, 1, 0.5f);
        }
    }
}
