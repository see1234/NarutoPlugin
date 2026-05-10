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
public class LightningSword extends AbilitiesMain {
    private Item item = new Item(Material.DIAMOND_HOE, 64, "§7[§6Naruto§7] §eМолния-меч", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 20, 0, 0, 0, 0)) {
            Location loc = player.getLocation().clone();
            createSword(loc, player);
        }
    }

    public void createSword(Location loc, Player player) {
        BukkitRunnable task = new BukkitRunnable() {
            int count = 0;

            @Override
            public void run() {
                if (count++ != 50) {
                    loc.add(player.getLocation().getDirection().multiply(1.5));

                    // Эффекты меча
                    loc.getWorld().spawnParticle(Particle.ELECTRIC_SPARK, loc, 10, 0.5, 0.5, 0.5, 0.1);

                    // Нанесение урона
                    for (Entity en : loc.getWorld().getNearbyEntities(loc, 2, 2, 2)) {
                        if (!(en instanceof LivingEntity) || en == player)
                            continue;

                        addDamageEntity(player, en, 6);
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