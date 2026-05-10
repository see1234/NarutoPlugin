package minenaruto.narutoplugin.abilities.Kekkei_Genkai.scorch;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

// Способность 4: Scorched Earth (Обожженная Земля)
public class ScorchedEarth extends ScorchAbility {
    public ScorchedEarth() {
        super("Обожженная Земля");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        Location loc = player.getLocation();
        for (int x = -3; x <= 3; x++) {
            for (int z = -3; z <= 3; z++) {
                Block block = loc.clone().add(x, -1, z).getBlock();
                block.setType(Material.NETHERRACK);
            }
        }
    }
}
