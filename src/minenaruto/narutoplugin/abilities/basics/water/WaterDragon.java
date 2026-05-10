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
public class WaterDragon extends AbilitiesMain {
    private Item item = new Item(Material.DIAMOND_HOE, 71, "§7[§6Naruto§7] §bВодяной дракон", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 30, 0, 0, 0, 0)) {
            Location startLoc = player.getLocation().clone();
            List<ArmorStand> dragonSegments = spawnDragon(startLoc, 10);
            runDragonAbility(dragonSegments, player);
        }
    }

    public void runDragonAbility(List<ArmorStand> segments, Player player) {
        BukkitRunnable task = new BukkitRunnable() {
            int count = 0;

            @Override
            public void run() {
                if (count++ != 100) {
                    for (ArmorStand segment : segments) {
                        segment.teleport(segment.getLocation().add(player.getLocation().getDirection().multiply(1.5)));

                        // Эффекты воды
                        Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB(0, 0, 255), 1);
                        segment.getLocation().getWorld().spawnParticle(Particle.WATER_SPLASH, segment.getLocation(), 10, 0.5, 0.5, 0.5, 0.1);

                        // Нанесение урона
                        for (Entity en : segment.getLocation().getWorld().getNearbyEntities(segment.getLocation(), 2.0, 2.0, 2.0)) {
                            if (!(en instanceof LivingEntity) || (en instanceof ArmorStand) || en == player)
                                continue;

                            addDamageEntity(player, en, 5);
                            en.setVelocity(en.getLocation().toVector().subtract(segment.getLocation().toVector()).normalize().multiply(1.5));
                        }
                    }
                } else {
                    for (ArmorStand segment : segments) {
                        segment.remove();
                    }
                    cancel();
                }
            }
        };
        task.runTaskTimer(Main.getInstance(), 1L, 1L);
    }

    public List<ArmorStand> spawnDragon(Location loc, int size) {
        List<ArmorStand> segments = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            ArmorStand segment = (ArmorStand) loc.getWorld().spawnEntity(loc.clone().add(i, 0, 0), EntityType.ARMOR_STAND);
            segment.setVisible(false);
            segment.setGravity(false);
            segments.add(segment);
        }
        return segments;
    }

    @Override
    public Item getItem() {
        return item;
    }
}