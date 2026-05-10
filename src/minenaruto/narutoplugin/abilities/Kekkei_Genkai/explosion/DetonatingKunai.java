package minenaruto.narutoplugin.abilities.Kekkei_Genkai.explosion;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

// Способность 2: Detonating Kunai (Взрывной Кунай)
public class DetonatingKunai extends ExplosionAbility {
    public DetonatingKunai() {
        super("Взрывной Кунай");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        Location loc = player.getTargetBlockExact(5).getLocation();
        loc.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, loc, 10);
        loc.getWorld().createExplosion(loc, 3.0F, false, false);
    }
}
