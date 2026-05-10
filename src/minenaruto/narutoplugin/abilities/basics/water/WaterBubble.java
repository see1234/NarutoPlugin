package minenaruto.narutoplugin.abilities.basics.water;
import minenaruto.narutoplugin.ParticleEffect;
import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.abilities.AbilityListener;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import minenaruto.narutoplugin.main.Main;
import org.bukkit.World;
import org.bukkit.plugin.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.event.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import org.bukkit.event.block.*;
import org.bukkit.*;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.*;


import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.scheduler.*;
import org.bukkit.util.Vector;
import org.bukkit.entity.Entity;
public class WaterBubble extends AbilitiesMain {
    private Item item = new Item(Material.DIAMOND_HOE, 71, "§7[§6Naruto§7] §bВодяной пузырь", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 20, 0, 0, 0, 0)) {
            Location loc = player.getLocation().clone();
            createBubble(loc, player);
        }
    }

    public void createBubble(Location loc, Player player) {
        BukkitRunnable task = new BukkitRunnable() {
            int count = 0;

            @Override
            public void run() {
                if (count++ != 50) {
                    for (Entity en : loc.getWorld().getNearbyEntities(loc, 3, 3, 3)) {
                        if (!(en instanceof LivingEntity) || en == player)
                            continue;

                        en.setVelocity(new Vector(0, 0.2, 0)); // Поднимает врагов вверх
                        addDamageEntity(player, en, 2);
                    }
                    loc.getWorld().spawnParticle(Particle.WATER_SPLASH, loc, 20, 1, 1, 1, 0.1);
                } else {
                    cancel();
                }
            }
        };
        task.runTaskTimer(Main.getInstance(), 1L, 1L);
    }

    @Override
    public Item getItem() {
        return item;
    }
}