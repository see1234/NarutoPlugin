package minenaruto.narutoplugin.abilities.clan.sarutobi;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class FireWallTechnique extends SarutobiTechnique {
    public FireWallTechnique() {
        super("Огненная Стена");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        Location start = player.getLocation();
        org.bukkit.util.Vector dir = start.getDirection().normalize();

        for (int i = 0; i < 10; i++) {
            Location wallLoc = start.clone().add(dir.clone().multiply(i));
            player.getWorld().spawnParticle(Particle.FLAME, wallLoc, 20);

            // Создает огонь на блоки
            if (wallLoc.getBlock().getType().isAir()) {
                wallLoc.getBlock().setType(Material.FIRE);
            }
        }

        player.getWorld().playSound(start, Sound.BLOCK_FIRE_EXTINGUISH, 1, 1);
    }
}
