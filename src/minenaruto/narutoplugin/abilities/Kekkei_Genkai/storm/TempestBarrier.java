package minenaruto.narutoplugin.abilities.Kekkei_Genkai.storm;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.main.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

// Способность 5: Tempest Barrier (Буревой Барьер)
public class TempestBarrier extends StormAbility {
    public TempestBarrier() {
        super("Буревой Барьер");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        Location loc = player.getLocation();
        List<Block> blocks = new ArrayList<>();
        for (int x = -2; x <= 2; x++) {
            for (int y = 0; y <= 3; y++) {
                for (int z = -2; z <= 2; z++) {
                    Block block = loc.clone().add(x, y, z).getBlock();
                    if (block.getType() == Material.AIR) {
                        blocks.add(block);
                        block.setType(Material.GLASS);
                    }
                }
            }
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                for (Block block : blocks) {
                    block.setType(Material.AIR);
                }
            }
        }.runTaskLater(Main.getInstance(), 100);
    }
}
