package minenaruto.narutoplugin.abilities.Kekkei_Genkai.storm;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.World;
import org.bukkit.entity.Player;

// Способность 3: Rain Storm (Ливень)
public class RainStorm extends StormAbility {
    public RainStorm() {
        super("Ливень");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        World world = player.getWorld();
        world.setStorm(true);
        world.setWeatherDuration(200);
    }
}
