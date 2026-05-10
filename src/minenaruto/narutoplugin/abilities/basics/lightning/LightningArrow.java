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
public class LightningArrow extends AbilitiesMain {
    private Item item = new Item(Material.DIAMOND_HOE, 64, "§7[§6Naruto§7] §eМолния-стрела", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 15, 0, 0, 0, 0)) {
            Location loc = player.getLocation().clone();
            spawnArrow(loc, player);
        }
    }

    public void spawnArrow(Location loc, Player player) {
        ArmorStand arrow = (ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
        arrow.setVisible(false);
        arrow.setGravity(false);
        runArrowAbility(arrow, player);
    }

    public void runArrowAbility(ArmorStand arrow, Player player) {
        BukkitRunnable task = new BukkitRunnable() {
            int count = 0;

            @Override
            public void run() {
                if (count++ != 50) {
                    arrow.teleport(arrow.getLocation().add(player.getLocation().getDirection().multiply(1.5)));

                    // Эффекты молнии
                    arrow.getLocation().getWorld().spawnParticle(Particle.ELECTRIC_SPARK, arrow.getLocation(), 10, 0.5, 0.5, 0.5, 0.1);

                    // Нанесение урона
                    for (Entity en : arrow.getLocation().getWorld().getNearbyEntities(arrow.getLocation(), 1, 1, 1)) {
                        if (!(en instanceof LivingEntity) || en == player)
                            continue;

                        addDamageEntity(player, en, 5);
                    }
                } else {
                    arrow.remove();
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