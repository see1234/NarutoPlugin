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
import org.bukkit.util.Vector;

public class WaterDragonBullet extends AbilitiesMain {
    private Item item = new Item(Material.DIAMOND_HOE, 80, "§7[§6Naruto§7] §6Sage (Water Dragon Bullet)", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 30, 0, 0, 0, 0)) {
            useWaterDragonBullet(player);
        }
    }

    public void useWaterDragonBullet(Player player) {
        Location location = player.getLocation().add(0, 1.5, 0);
        ArmorStand dragon = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        dragon.setVisible(false);
        dragon.setGravity(false);

        BukkitRunnable task = new BukkitRunnable() {
            int count = 0;

            @Override
            public void run() {
                if (count++ < 30) {
                    dragon.teleport(dragon.getLocation().add(player.getLocation().getDirection().multiply(1.5)));
                    dragon.getWorld().spawnParticle(Particle.WATER_SPLASH, dragon.getLocation(), 50, 1, 1, 1, 0.1);

                    for (Entity entity : dragon.getLocation().getWorld().getNearbyEntities(dragon.getLocation(), 3, 3, 3)) {
                        if (entity instanceof LivingEntity && entity != player) {
                            addDamageEntity(player,  entity, 7);
                            Vector direction = dragon.getLocation().toVector().subtract(entity.getLocation().toVector());
                            if (direction.lengthSquared() > 0) {
                                entity.setVelocity(direction.normalize().multiply(1.5)); // Move toward at speed 2
                            } else {
                                entity.setVelocity(new Vector(0, 0, 0)); // Stop if at the same location
                            }
                        }
                    }
                } else {
                    dragon.remove();
                    cancel();
                }
            }
        };
        task.runTaskTimer(Main.getInstance(), 0L, 1L);
    }

    @Override
    public Item getItem() {
        return item;
    }
}