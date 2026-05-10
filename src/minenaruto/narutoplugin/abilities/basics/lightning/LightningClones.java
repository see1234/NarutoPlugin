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

public class LightningClones extends AbilitiesMain {
    private Item item = new Item(Material.DIAMOND_HOE, 64, "§7[§6Naruto§7] §eМолния-клоны", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 25, 0, 0, 0, 0)) {
            Location loc = player.getLocation().clone();
            spawnClones(loc, player);
        }
    }

    public void spawnClones(Location loc, Player player) {
        for (int i = 0; i < 3; i++) {
            ArmorStand clone = (ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
            clone.setVisible(false);
            clone.setGravity(false);
            runCloneAbility(clone, player);
        }
    }

    public void runCloneAbility(ArmorStand clone, Player player) {
        BukkitRunnable task = new BukkitRunnable() {
            int count = 0;

            @Override
            public void run() {
                if (count++ != 50) {
                    clone.teleport(clone.getLocation().add(player.getLocation().getDirection().multiply(1.5)));

                    // Эффекты молнии
                    clone.getLocation().getWorld().spawnParticle(Particle.ELECTRIC_SPARK, clone.getLocation(), 10, 0.5, 0.5, 0.5, 0.1);

                    // Нанесение урона
                    for (Entity en : clone.getLocation().getWorld().getNearbyEntities(clone.getLocation(), 2, 2, 2)) {
                        if (!(en instanceof LivingEntity) || en == player)
                            continue;

                        addDamageEntity(player, en, 4);
                    }
                } else {
                    clone.remove();
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