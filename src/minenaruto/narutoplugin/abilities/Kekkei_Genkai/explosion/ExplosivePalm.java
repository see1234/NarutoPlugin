package minenaruto.narutoplugin.abilities.Kekkei_Genkai.explosion;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Location;
import org.bukkit.entity.Player;

// Способность 1: Explosive Palm (Взрывная Ладонь)
public class ExplosivePalm extends ExplosionAbility {
    public ExplosivePalm() {
        super("Взрывная Ладонь");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        Location loc = player.getLocation();
        loc.getWorld().createExplosion(loc, 2.0F, false, false);
    }
}
