package minenaruto.narutoplugin.abilities.basics.wind;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import minenaruto.narutoplugin.ParticleEffect;
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
public class WindCloneJutsu extends AbilitiesMain {
    private Item item = new Item(Material.DIAMOND_HOE, 72, "§7[§6Naruto§7] §aВоздушный клон", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 25, 0, 0, 0, 0)) {
            ArmorStand clone = (ArmorStand) player.getWorld().spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);
            clone.setVisible(false);
            clone.setGravity(false);

            new BukkitRunnable() {
                int count = 0;
                @Override
                public void run() {
                    if (count++ < 20) {
                        for (Entity entity : clone.getLocation().getWorld().getNearbyEntities(clone.getLocation(), 2, 2, 2)) {
                            if (entity instanceof LivingEntity && entity != player) {
                                addDamageEntity(player, entity, 5);
                            }
                        }
                        player.getWorld().spawnParticle(Particle.CLOUD, clone.getLocation(), 10, 0.5, 0.5, 0.5, 0.1);
                    } else {
                        clone.remove();
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
