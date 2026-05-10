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

public class UltimateRasenshuriken extends AbilitiesMain {
    private Item item = new Item(Material.DIAMOND_HOE, 80, "§7[§6Naruto§7] §6Sage (Ultimate Rasen shuriken)", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 30, 0, 0, 0, 0)) {
            useUltimateRasenshuriken(player);
        }
    }

    public void useUltimateRasenshuriken(Player player) {
        Location location = player.getLocation().add(0, 1.5, 0);
        ArmorStand shuriken = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        shuriken.setVisible(false);
        shuriken.setGravity(false);

        BukkitRunnable task = new BukkitRunnable() {
            int count = 0;

            @Override
            public void run() {
                if (count++ < 40) {
                    shuriken.teleport(shuriken.getLocation().add(player.getLocation().getDirection().multiply(1.5)));
                    shuriken.getWorld().spawnParticle(Particle.CRIT, shuriken.getLocation(), 100, 1, 1, 1, 0.1);

                    for (Entity entity : shuriken.getLocation().getWorld().getNearbyEntities(shuriken.getLocation(), 5, 5, 5)) {
                        if (entity instanceof LivingEntity && entity != player) {
                            addDamageEntity(player,  entity, 15);
                            Vector direction = shuriken.getLocation().toVector().subtract(entity.getLocation().toVector());
                            if (direction.lengthSquared() > 0) {
                                entity.setVelocity(direction.normalize().multiply(3)); // Move toward at speed 2
                            } else {
                                entity.setVelocity(new Vector(0, 0, 0)); // Stop if at the same location
                            }
                        }
                    }
                } else {
                    shuriken.remove();
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