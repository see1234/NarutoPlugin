package minenaruto.narutoplugin.abilities.Kekkei_Genkai.storm;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Location;
import org.bukkit.entity.Player;

// Способность 1: Lightning Strike (Молниеносный Удар)
public class LightningStrike extends StormAbility {
    public LightningStrike() {
        super("Молниеносный Удар");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        Location loc = player.getTargetBlockExact(30).getLocation();
        player.getWorld().strikeLightning(loc);
    }
}
