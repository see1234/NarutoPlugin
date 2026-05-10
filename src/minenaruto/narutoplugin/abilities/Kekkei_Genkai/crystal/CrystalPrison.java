package minenaruto.narutoplugin.abilities.Kekkei_Genkai.crystal;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.main.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Set;

// Способность 1: Crystal Prison
public class CrystalPrison extends CrystalAbility {
    public CrystalPrison() {
        super("Кристальная Тюрьма");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        Location loc = player.getTargetBlockExact(5).getLocation();
        Set<Block> blocks = new HashSet<>();

        for (int x = -2; x <= 2; x++) {
            for (int y = -1; y <= 3; y++) {
                for (int z = -2; z <= 2; z++) {
                    Block block = loc.clone().add(x, y, z).getBlock();
                    if (block.getType().isAir()) {
                        block.setType(Material.PURPLE_STAINED_GLASS);
                        blocks.add(block);
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
