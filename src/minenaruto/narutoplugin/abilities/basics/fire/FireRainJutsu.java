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
public class FireRainJutsu extends AbilitiesMain {
    private Item item = new Item(Material.DIAMOND_HOE, 61, "§7[§6Naruto§7] §cОгненный дождь", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 60, 0, 0, 0, 0)) {
            new BukkitRunnable() {
                int count = 0;
                @Override
                public void run() {
                    if (count++ < 20) {
                        Location loc = player.getLocation().add(Math.random() * 10 - 5, 10, Math.random() * 10 - 5);
                        Fireball fireball = player.getWorld().spawn(loc, Fireball.class);
                        fireball.setDirection(new Vector(0, -1, 0));
                        fireball.setYield(0); // Отключаем разрушение блоков
                    } else {
                        cancel();
                    }
                }
            }.runTaskTimer(Main.getInstance(), 0L, 5L);
        }
    }

    @Override
    public Item getItem() {
        return item;
    }
}
