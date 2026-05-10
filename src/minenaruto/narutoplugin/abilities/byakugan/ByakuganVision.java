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
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;
public class ByakuganVision extends AbilitiesMain {
    private Item item = new Item(Material.DIAMOND_HOE, 74, "§7[§6Naruto§7] §fВидение 360°", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 10, 0, 0, 0, 0)) {
            enable360Vision(player);
        }
    }

    public void enable360Vision(Player player) {
        BukkitRunnable task = new BukkitRunnable() {
            int count = 0;

            @Override
            public void run() {
                if (count++ != 100) {
                    // Эффекты видения
                    player.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, player.getLocation(), 30, 1, 1, 1, 0.1);

                    // Обнаружение врагов
                    for (Entity en : player.getNearbyEntities(30, 30, 30)) {
                        if (!(en instanceof LivingEntity) || en == player)
                            continue;

                        en.getWorld().spawnParticle(Particle.VILLAGER_ANGRY, en.getLocation(), 5, 0.5, 0.5, 0.5, 0.1);
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