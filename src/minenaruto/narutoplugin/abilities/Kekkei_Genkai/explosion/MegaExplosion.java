package minenaruto.narutoplugin.abilities.Kekkei_Genkai.explosion;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

// Способность 5: Mega Explosion (Мега Взрыв)
public class MegaExplosion extends ExplosionAbility {
    public MegaExplosion() {
        super("Мега Взрыв");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        Location loc = player.getLocation();
        loc.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, loc, 5);
        loc.getWorld().createExplosion(loc, 5.0F, false, false);
    }
}
