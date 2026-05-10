package minenaruto.narutoplugin.abilities.reningan;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
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
public class TengaiShinsei extends AbilitiesMain {
    private Item item = new Item(Material.DIAMOND_HOE, 77, "§7[§6Naruto§7] §5Небесный путь", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 40, 0, 0, 0, 0)) {
            Location loc = player.getLocation().clone();
            createTengaiShinsei(loc, player);
        }
    }

    public void createTengaiShinsei(Location loc, Player player) {
        BukkitRunnable task = new BukkitRunnable() {
            int count = 0;

            @Override
            public void run() {
                if (count++ != 50) {
                    // Эффекты небесного пути
                    loc.getWorld().spawnParticle(Particle.PORTAL, loc, 30, 1, 1, 1, 0.1);

                    // Нанесение урона
                    for (Entity en : loc.getWorld().getNearbyEntities(loc, 5, 5, 5)) {
                        if (!(en instanceof LivingEntity) || en == player)
                            continue;

                        addDamageEntity(player, en, 10);
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