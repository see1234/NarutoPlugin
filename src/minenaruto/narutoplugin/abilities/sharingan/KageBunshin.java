package minenaruto.narutoplugin.abilities.sharingan;
import java.io.File;
import java.util.*;

import minenaruto.narutoplugin.ParticleEffect;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.abilities.AbilityListener;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import minenaruto.narutoplugin.main.Main;
import org.bukkit.scheduler.BukkitRunnable;

public class KageBunshin extends AbilitiesMain {
    private Item item = new Item(Material.DIAMOND_HOE, 87, "§7[§6Naruto§7] §4Кагэ Буны", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 30, 0, 0, 0, 0)) {
            createKageBunshin(player);
        }
    }

    public void createKageBunshin(Player player) {
        for (int i = 0; i < 3; i++) {
            ArmorStand clone = (ArmorStand) player.getWorld().spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);
            clone.setVisible(false);
            clone.setGravity(false);
            runCloneAbility(clone, player);
        }
    }

    public void runCloneAbility(ArmorStand clone, Player player) {
        BukkitRunnable task = new BukkitRunnable() {
            int count = 0;

            @Override
            public void run() {
                if (count++ != 50) {
                    clone.teleport(clone.getLocation().add(player.getLocation().getDirection().multiply(1.5)));

                    // Эффекты клона
                    clone.getLocation().getWorld().spawnParticle(Particle.CRIT, clone.getLocation(), 10, 0.5, 0.5, 0.5, 0.1);

                    // Нанесение урона
                    for (Entity en : clone.getLocation().getWorld().getNearbyEntities(clone.getLocation(), 2, 2, 2)) {
                        if (!(en instanceof LivingEntity) || en == player)
                            continue;

                        addDamageEntity(player, en, 4);
                    }
                } else {
                    clone.remove();
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