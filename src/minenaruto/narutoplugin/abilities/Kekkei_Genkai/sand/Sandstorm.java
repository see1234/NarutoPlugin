package minenaruto.narutoplugin.abilities.Kekkei_Genkai.sand;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

// Способность 3: Sandstorm (Песчаная Буря)
public class Sandstorm extends DustAbility {
    public Sandstorm() {
        super("Песчаная Буря");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        Location loc = player.getLocation();
        loc.getWorld().spawnParticle(Particle.FALLING_DUST, loc, 100, 3, 3, 3, 0.2);
    }
}
