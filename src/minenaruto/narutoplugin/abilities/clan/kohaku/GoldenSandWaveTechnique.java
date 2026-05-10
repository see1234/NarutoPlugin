package minenaruto.narutoplugin.abilities.clan.kohaku;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class GoldenSandWaveTechnique extends KohakuTechnique {
    public GoldenSandWaveTechnique() {
        super("Золотая Песчаная Волна");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        Location loc = player.getLocation();
        for (int i = 0; i < 5; i++) {
            loc.getWorld().spawnParticle(Particle.BLOCK_CRACK,
                    loc.clone().add(Math.random() * 4 - 2, 0, Math.random() * 4 - 2),
                    30, Material.GOLD_BLOCK.createBlockData());
        }
        loc.getWorld().playSound(loc, Sound.BLOCK_SAND_HIT, 1, 1);
    }
}
