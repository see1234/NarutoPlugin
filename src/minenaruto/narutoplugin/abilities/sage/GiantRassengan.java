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

public class GiantRassengan extends AbilitiesMain {
    private Item item = new Item(Material.DIAMOND_HOE, 80, "§7[§6Naruto§7] §6Sage (Гиганский рассенган)", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 30, 0, 0, 0, 0)) {
            useGiantRasengan(player);
        }
    }

    public void useGiantRasengan(Player player) {
        Location location = player.getLocation().add(0, 1.5, 0);
        ArmorStand rasengan = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        rasengan.setVisible(false);
        rasengan.setGravity(false);
        rasengan.setSmall(false);

        BukkitRunnable task = new BukkitRunnable() {
            int count = 0;

            @Override
            public void run() {
                if (count++ < 20) {
                    rasengan.teleport(rasengan.getLocation().add(player.getLocation().getDirection().multiply(1.5)));
                    rasengan.getWorld().spawnParticle(Particle.CLOUD, rasengan.getLocation(), 50, 1, 1, 1, 0.1);

                    for (Entity entity : rasengan.getLocation().getWorld().getNearbyEntities(rasengan.getLocation(), 3, 3, 3)) {
                        if (entity instanceof LivingEntity && entity != player) {
                            addDamageEntity(player,  entity, 10);
                            Vector direction = rasengan.getLocation().toVector().subtract(entity.getLocation().toVector());
                            if (direction.lengthSquared() > 0) {
                                entity.setVelocity(direction.normalize().multiply(2)); // Move toward at speed 2
                            } else {
                                entity.setVelocity(new Vector(0, 0, 0)); // Stop if at the same location
                            }
                        }
                    }
                } else {
                    rasengan.remove();
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