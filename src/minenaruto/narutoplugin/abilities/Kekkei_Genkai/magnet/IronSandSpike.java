package minenaruto.narutoplugin.abilities.Kekkei_Genkai.magnet;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

// Способность 4: Iron Sand Spike (Шип Железного Песка)
public class IronSandSpike extends MagnetAbility {
    public IronSandSpike() {
        super("Шип Железного Песка");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        Location loc = player.getTargetBlockExact(20).getLocation();
        loc.getWorld().spawnFallingBlock(loc, Material.IRON_BLOCK.createBlockData());
    }
}
