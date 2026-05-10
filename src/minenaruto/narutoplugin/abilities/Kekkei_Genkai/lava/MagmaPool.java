package minenaruto.narutoplugin.abilities.Kekkei_Genkai.lava;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

// Способность 2: Magma Pool (Озеро Магмы)
public class MagmaPool extends LavaAbility {
    public MagmaPool() {
        super("Озеро Магмы");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        Location loc = player.getLocation();
        for (int x = -2; x <= 2; x++) {
            for (int z = -2; z <= 2; z++) {
                Location blockLoc = loc.clone().add(x, -1, z);
                blockLoc.getBlock().setType(Material.MAGMA_BLOCK);
            }
        }
    }
}
