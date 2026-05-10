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
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;
public class PalmStrike extends AbilitiesMain {
    private Item item = new Item(Material.DIAMOND_HOE, 74, "§7[§6Naruto§7] §fУдар ладонью", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 15, 0, 0, 0, 0)) {
            Location loc = player.getLocation().clone();
            strikePalm(loc, player);
        }
    }

    public void strikePalm(Location loc, Player player) {
        BukkitRunnable task = new BukkitRunnable() {
            int count = 0;

            @Override
            public void run() {
                if (count++ != 50) {
                    // Эффекты удара
                    loc.getWorld().spawnParticle(Particle.CRIT, loc, 10, 0.5, 0.5, 0.5, 0.1);

                    // Нанесение урона
                    for (Entity en : loc.getWorld().getNearbyEntities(loc, 2, 2, 2)) {
                        if (!(en instanceof LivingEntity) || en == player)
                            continue;

                        addDamageEntity(player, en, 6);
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