package minenaruto.narutoplugin.abilities.byakugan;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.abilities.AbilityListener;
import minenaruto.narutoplugin.abilities.ClearInterface;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import minenaruto.narutoplugin.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;
public class RotationShield extends AbilitiesMain {
    private Item item = new Item(Material.DIAMOND_HOE, 74, "§7[§6Naruto§7] §fВращающийся щит", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 30, 0, 0, 0, 0)) {
            createRotationShield(player);
        }
    }

    public void createRotationShield(Player player) {
        BukkitRunnable task = new BukkitRunnable() {
            int count = 0;

            @Override
            public void run() {
                if (count++ != 100) {
                    // Эффекты вращающегося щита
                    for (double angle = 0; angle < 360; angle += 10) {
                        double radians = Math.toRadians(angle);
                        double x = Math.cos(radians) * 2;
                        double z = Math.sin(radians) * 2;
                        Location loc = player.getLocation().clone().add(x, 1, z);
                        loc.getWorld().spawnParticle(Particle.SPELL_WITCH, loc, 1, 0, 0, 0, 0.1);
                    }

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