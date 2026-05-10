package minenaruto.narutoplugin.abilities.basics.lightning;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import minenaruto.narutoplugin.ParticleEffect;
import org.bukkit.*;
import org.bukkit.block.Block;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
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
public class LightningDragon extends AbilitiesMain {
    private Item item = new Item(Material.DIAMOND_HOE, 64, "§7[§6Naruto§7] §eМолния дракона", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 30, 0, 0, 0, 0)) {
            Location loc = player.getLocation().clone();
            List<ArmorStand> dragonSegments = spawnDragon(loc, 10);
            runDragonAbility(dragonSegments, player);
        }
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

    public void runDragonAbility(List<ArmorStand> segments, Player player) {
        BukkitRunnable task = new BukkitRunnable() {
            int count = 0;

            @Override
            public void run() {
                if (count++ != 100) {
                    for (ArmorStand segment : segments) {
                        segment.teleport(segment.getLocation().add(player.getLocation().getDirection().multiply(1.5)));

                        // Эффекты молнии
                        segment.getLocation().getWorld().spawnParticle(Particle.ELECTRIC_SPARK, segment.getLocation(), 10, 0.5, 0.5, 0.5, 0.1);

                        // Нанесение урона
                        for (Entity en : segment.getLocation().getWorld().getNearbyEntities(segment.getLocation(), 2, 2, 2)) {
                            if (!(en instanceof LivingEntity) || en == player)
                                continue;

                            addDamageEntity(player, en, 5);
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

    @Override
    public Item getItem() {
        return item;
    }
}