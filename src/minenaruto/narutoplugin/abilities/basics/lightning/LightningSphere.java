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
public class LightningSphere extends AbilitiesMain {
    private Item item = new Item(Material.DIAMOND_HOE, 64, "§7[§6Naruto§7] §eМолния-сфера", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 25, 0, 0, 0, 0)) {
            Location loc = player.getLocation().clone();
            createSphere(loc, player);
        }
    }

    public void createSphere(Location loc, Player player) {
        BukkitRunnable task = new BukkitRunnable() {
            int count = 0;

            @Override
            public void run() {
                if (count++ != 50) {
                    // Эффекты сферы
                    loc.getWorld().spawnParticle(Particle.ELECTRIC_SPARK, loc, 30, 1, 1, 1, 0.1);

                    // Нанесение урона
                    for (Entity en : loc.getWorld().getNearbyEntities(loc, 3, 3, 3)) {
                        if (!(en instanceof LivingEntity) || en == player)
                            continue;

                        addDamageEntity(player, en, 5);
                    }
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