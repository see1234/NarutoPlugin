package minenaruto.narutoplugin.abilities.Kekkei_Genkai.sand;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

// Способность 1: Dust Blast (Взрыв Пыли)
public class DustBlast extends DustAbility {
    public DustBlast() {
        super("Взрыв Пыли");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        Location loc = player.getLocation();
        loc.getWorld().spawnParticle(Particle.CLOUD, loc, 50, 2, 2, 2, 0.1);
        loc.getWorld().playSound(loc, Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
    }
}
