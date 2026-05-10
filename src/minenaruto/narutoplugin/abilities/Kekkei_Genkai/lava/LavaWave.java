package minenaruto.narutoplugin.abilities.Kekkei_Genkai.lava;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

// Способность 3: Lava Wave (Лавовая Волна)
public class LavaWave extends LavaAbility {
    public LavaWave() {
        super("Лавовая Волна");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        Location loc = player.getLocation();
        player.getWorld().spawnParticle(Particle.FLAME, loc, 100, 2, 1, 2, 0.1);
    }
}
