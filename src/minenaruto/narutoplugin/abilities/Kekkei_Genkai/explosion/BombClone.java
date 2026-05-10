package minenaruto.narutoplugin.abilities.Kekkei_Genkai.explosion;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

// Способность 3: Bomb Clone (Клон-Бомба)
public class BombClone extends ExplosionAbility {
    public BombClone() {
        super("Клон-Бомба");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        Location loc = player.getLocation();
        player.getWorld().spawnParticle(Particle.EXPLOSION_NORMAL, loc, 50, 2, 2, 2, 0.2);
    }
}
