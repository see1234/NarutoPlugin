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

public class MassiveRassenganBarrage extends AbilitiesMain {
    private Item item = new Item(Material.DIAMOND_HOE, 80, "§7[§6Naruto§7] §6Sage (Massive Rassengan Barrage)", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 30, 0, 0, 0, 0)) {
            useMassiveRasenganBarrage(player);
        }
    }

    public void useMassiveRasenganBarrage(Player player) {
        for (int i = 0; i < 10; i++) {
            Location location = player.getLocation().add(0, 1.5, 0);
            ArmorStand rasengan = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
            rasengan.setVisible(false);
            rasengan.setGravity(false);

            Vector direction = new Vector(Math.random() - 0.5, Math.random() - 0.5, Math.random() - 0.5).normalize();
            BukkitRunnable task = new BukkitRunnable() {
                int count = 0;

                @Override
                public void run() {
                    if (count++ < 20) {
                        rasengan.teleport(rasengan.getLocation().add(direction));
                        rasengan.getWorld().spawnParticle(Particle.CLOUD, rasengan.getLocation(), 10, 0.5, 0.5, 0.5, 0.1);

                        for (Entity entity : rasengan.getLocation().getWorld().getNearbyEntities(rasengan.getLocation(), 2, 2, 2)) {
                            if (entity instanceof LivingEntity && entity != player) {
                                addDamageEntity(player,  entity, 5);
                                entity.setVelocity(direction.lengthSquared() > 0 ? direction.multiply(1.5) : new Vector(0, 0, 0));
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
    }

    @Override
    public Item getItem() {
        return item;
    }
}