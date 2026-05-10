package minenaruto.narutoplugin.abilities.Kekkei_Genkai.lava;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

// Способность 1: Lava Burst (Лавовый Взрыв)
public class LavaBurst extends LavaAbility {
    public LavaBurst() {
        super("Лавовый Взрыв");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        Location loc = player.getLocation();
        loc.getWorld().spawnParticle(Particle.LAVA, loc, 50);
        loc.getWorld().createExplosion(loc, 2.5F, false, false);
    }
}
