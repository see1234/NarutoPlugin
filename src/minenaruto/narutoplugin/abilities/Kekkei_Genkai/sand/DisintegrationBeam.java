package minenaruto.narutoplugin.abilities.Kekkei_Genkai.sand;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

// Способность 5: Disintegration Beam (Луч Распада)
public class DisintegrationBeam extends DustAbility {
    public DisintegrationBeam() {
        super("Луч Распада");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        Location loc = player.getEyeLocation();
        loc.getWorld().spawnParticle(Particle.FLAME, loc, 50, 0.5, 0.5, 0.5, 0.02);
    }
}
