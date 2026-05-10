package minenaruto.narutoplugin.abilities.basics.fire;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
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
public class FireVortexJutsu extends AbilitiesMain {
    private Item item = new Item(Material.DIAMOND_HOE, 61, "§7[§6Naruto§7] §cОгненный вихрь", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 40, 0, 0, 0, 0)) {
            new BukkitRunnable() {
                int count = 0;
                @Override
                public void run() {
                    if (count++ < 30) {
                        for (double angle = 0; angle < 360; angle += 20) {
                            double radians = Math.toRadians(angle);
                            double x = Math.cos(radians) * 3;
                            double z = Math.sin(radians) * 3;
                            Location loc = player.getLocation().add(x, 1, z);
                            player.getWorld().spawnParticle(Particle.FLAME, loc, 5, 0.1, 0.1, 0.1, 0.1);

                            for (Entity entity : loc.getWorld().getNearbyEntities(loc, 1, 1, 1)) {
                                if (entity instanceof LivingEntity && entity != player) {
                                    addDamageEntity(player, entity, 5);
                                }
                            }
                        }
                    } else {
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
