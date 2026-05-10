package minenaruto.narutoplugin.abilities.sage;

import java.util.List;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.abilities.AbilityListener;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import minenaruto.narutoplugin.main.Main;

public class Sensing extends AbilitiesMain {
    private Item item = new Item(Material.DIAMOND_HOE, 80, "§7[§6Naruto§7] §6Sage (Sensing)", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 30, 0, 0, 0, 0)) {
            useSensing(player);
        }
    }

    public void useSensing(Player player) {
        // Добавляем эффект GLOWING (подсветка врагов)
        player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 200, 1));

        // Создаем задачу для отображения частиц
        BukkitRunnable task = new BukkitRunnable() {
            @Override
            public void run() {
                for (Entity entity : player.getNearbyEntities(20, 20, 20)) {
                    if (entity instanceof LivingEntity && entity != player) {
                        entity.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, entity.getLocation(), 5, 0.5, 0.5, 0.5, 0.1);
                    }
                }
            }
        };

        // Запускаем задачу с интервалом 20 тиков (1 секунда)
        task.runTaskTimer(Main.getInstance(), 0L, 20L);

        // Останавливаем задачу через 10 секунд (200 тиков)
        Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
            task.cancel(); // Останавливаем задачу
            player.removePotionEffect(PotionEffectType.GLOWING); // Убираем эффект GLOWING
            player.sendMessage("§aСпособность Sensing завершена.");
        }, 200L); // 200 тиков = 10 секунд
    }

    @Override
    public Item getItem() {
        return item;
    }
}