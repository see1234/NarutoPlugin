package minenaruto.narutoplugin.abilities.basics.fire;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;

import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.abilities.AbilityListener;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import minenaruto.narutoplugin.main.Main;

public class FireMeteorJutsu extends AbilitiesMain {
    private Item item = new Item(Material.DIAMOND_HOE, 61, "§7[§6Naruto§7] §cОгненный метеор", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 100, 0, 0, 0, 0)) {
            Location target = player.getTargetBlock(null, 50).getLocation();
            FallingBlock meteor = player.getWorld().
                    spawnFallingBlock(target.add(0, 50, 0), Material.MAGMA_BLOCK.createBlockData());
            meteor.setDropItem(false);
            meteor.setHurtEntities(true);

            new BukkitRunnable() {
                @Override
                public void run() {
                    if (meteor.isOnGround()) {
                        player.getWorld().spawnParticle(Particle.FLAME, meteor.getLocation(), 100, 2, 2, 2, 0.1);
                        meteor.remove();
                        cancel();
                    }
                }
            }.runTaskTimer(Main.getInstance(), 0L, 1L);
        }
    }

    @Override
    public Item getItem() {
        return item;
    }
}
