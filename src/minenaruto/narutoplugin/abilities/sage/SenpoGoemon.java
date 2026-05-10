package minenaruto.narutoplugin.abilities.sage;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.abilities.AbilityListener;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import minenaruto.narutoplugin.main.Main;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BlockIterator;
public class SenpoGoemon extends AbilitiesMain {
    private Item item = new Item(Material.DIAMOND_HOE, 80, "§7[§6Naruto§7] §6Sage (Senpo Goemon)", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 30, 0, 0, 0, 0)) {
            useSenpoGoemon(player);
        }
    }

    public void useSenpoGoemon(Player player) {
        Location location = player.getLocation();
        BukkitRunnable task = new BukkitRunnable() {
            int count = 0;

            @Override
            public void run() {
                if (count++ < 10) {
                    location.getWorld().spawnParticle(Particle.WATER_SPLASH, location, 100, 2, 2, 2, 0.1);
                    location.getWorld().spawnParticle(Particle.FLAME, location, 50, 2, 2, 2, 0.1);

                    for (Entity entity : location.getWorld().getNearbyEntities(location, 5, 5, 5)) {
                        if (entity instanceof LivingEntity && entity != player) {
                            addDamageEntity(player,  entity, 8);
                            entity.setFireTicks(100);
                        }
                    }
                    location.add(player.getLocation().getDirection().multiply(1.5));
                } else {
                    cancel();
                }
            }
        };
        task.runTaskTimer(Main.getInstance(), 0L, 5L);
    }

    @Override
    public Item getItem() {
        return item;
    }
}