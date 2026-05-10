package minenaruto.narutoplugin.abilities.basics.wind;

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
public class WindVortexJutsu extends AbilitiesMain {
    private Item item = new Item(Material.DIAMOND_HOE, 72, "§7[§6Naruto§7] §aВихрь", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 30, 0, 0, 0, 0)) {
            new BukkitRunnable() {
                int count = 0;
                @Override
                public void run() {
                    if (count++ < 20) {
                        for (double angle = 0; angle < 360; angle += 20) {
                            double radians = Math.toRadians(angle);
                            double x = Math.cos(radians) * 3;
                            double z = Math.sin(radians) * 3;
                            Location loc = player.getLocation().add(x, 1, z);
                            player.getWorld().spawnParticle(Particle.CLOUD, loc, 5, 0.1, 0.1, 0.1, 0.1);

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
