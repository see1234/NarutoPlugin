package minenaruto.narutoplugin.abilities.basics;

import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.abilities.AbilityListener;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import minenaruto.narutoplugin.main.Main;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;

import java.util.*;

public class ReplacementTechnique extends AbilitiesMain {
    private Item item = new Item(Material.DIAMOND_HOE, 6, "§7[§6Naruto§7] §aТехника замены", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 50, 0, 0, 0, 0)) {
            runTaskAbility(player);
        }
    }


    public void runTaskAbility(Player player) {
        int size = 2;
        ArrayList<Block> blocks = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Block b = player.getWorld().getBlockAt(player.getLocation().getBlockX(), player.getLocation().getBlockY() + i, player.getLocation().getBlockZ());
            if (b.getType() == Material.AIR) {
                b.setType(Material.ACACIA_LOG);
                blocks.add(b);
            }
            b.getWorld().spawnParticle(Particle.CLOUD, b.getLocation(), 20, 0.3D, 0.3D, 0.3D, 0.3D);
        }
        int random = 3 + (int)(Math.random() * 6.0D);
        Random randomPos = new Random();
        if (randomPos.nextFloat() > 0.5F) {
            player.teleport(player.getLocation().add(random, 1.0D, -random));
        } else {
            player.teleport(player.getLocation().add(-random, 1.0D, random));
        }
        player.getWorld().spawnParticle(Particle.CLOUD, player.getLocation(), 20, 0.3D, 0.3D, 0.3D, 0.3D);
        Iterator<Block> blockIterator = blocks.iterator();
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> {
            while (blockIterator.hasNext()) {
                Block b = blockIterator.next();
                b.setType(Material.AIR);
            }
        },60L);
    }

    @Override
    public Item getItem() {
        return item;
    }



}