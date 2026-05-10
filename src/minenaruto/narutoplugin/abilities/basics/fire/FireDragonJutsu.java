package minenaruto.narutoplugin.abilities.basics.fire;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
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
public class FireDragonJutsu extends AbilitiesMain {
    private Item item = new Item(Material.DIAMOND_HOE, 61, "§7[§6Naruto§7] §cОгненный дракон", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 50, 0, 0, 0, 0)) {
            Location loc = player.getLocation().add(0, 1, 0);
            ArmorStand dragon = (ArmorStand) player.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
            dragon.setVisible(false);
            dragon.setGravity(false);
            new BukkitRunnable() {
                int count = 0;
                @Override
                public void run() {
                    if (count++ < 30) {
                        dragon.teleport(dragon.getLocation().add(player.getLocation().getDirection().multiply(1.5)));
                        player.getWorld().spawnParticle(Particle.FLAME, dragon.getLocation(), 50, 1, 1, 1, 0.1);

                        for (Entity entity : dragon.getLocation().getWorld().getNearbyEntities(dragon.getLocation(), 2, 2, 2)) {
                            if (entity instanceof LivingEntity && entity != player) {
                                addDamageEntity(player, entity, 8);
                            }
                        }
                    } else {
                        dragon.remove();
                        cancel();
                    }
                }
            }.runTaskTimer(Main.getInstance(), 0L, 1L);
        }
    }

    @Override
    public Item getItem() {
        return item;
    }
}
