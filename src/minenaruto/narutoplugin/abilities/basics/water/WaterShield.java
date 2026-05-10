package minenaruto.narutoplugin.abilities.basics.water;
import java.util.*;

import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.abilities.AbilityListener;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import minenaruto.narutoplugin.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class WaterShield extends AbilitiesMain {
    private Item item = new Item(Material.DIAMOND_HOE, 71, "§7[§6Naruto§7] §bВодяной щит", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 20, 0, 0, 0, 0)) {
            createShield(player);
        }
    }

    public void createShield(Player player) {
        BukkitRunnable task = new BukkitRunnable() {
            int count = 0;

            @Override
            public void run() {
                if (count++ != 100) {
                    // Эффекты щита
                    player.getWorld().spawnParticle(Particle.WATER_SPLASH, player.getLocation(), 30, 1, 1, 1, 0.1);

                    // Защита от снарядов
                    for (Entity en : player.getNearbyEntities(3, 3, 3)) {
                        if (en instanceof Projectile) {
                            en.remove();
                        }
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