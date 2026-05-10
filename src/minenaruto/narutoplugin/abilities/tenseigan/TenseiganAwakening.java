package minenaruto.narutoplugin.abilities.tenseigan;

import minenaruto.narutoplugin.ParticleEffect;
import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.abilities.AbilityListener;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import minenaruto.narutoplugin.main.Main;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.*;

public class TenseiganAwakening extends AbilitiesMain {
    private Item item = new Item(Material.DIAMOND_HOE, 90, "§7[§6Naruto§7] §bТенсейган освобождение",
            List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));



    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 100, 0, 0, 0, 0)) {
            tenseiganAwakening(player, pl);
        }
    }

    // 1. Сфера Тенсейгана
    private void tenseiganOrbs(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 20, 0, 0, 0, 0)) {
            Location loc = player.getLocation().add(0, 2, 0);

            for (int i = 0; i < 8; i++) {
                ArmorStand orb = (ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
                orb.setVisible(false);
                orb.setGravity(false);
                orb.setSmall(true);

                int finalI = i;

                new BukkitRunnable() {
                    double angle = finalI * (2 * Math.PI / 8);
                    double radius = 2.0;
                    int count = 0;
                    @Override
                    public void run() {
                        if(count++ == 1000) {
                            orb.remove();
                            cancel();
                            return;
                        }
                        if (!player.isOnline() || orb.isDead()) {
                            orb.remove();
                            cancel();
                            return;
                        }

                        // Вращение вокруг игрока
                        angle += 0.1;
                        double x = Math.cos(angle) * radius;
                        double z = Math.sin(angle) * radius;
                        orb.teleport(player.getLocation().add(x, 1.5, z));

                        // Эффекты частиц
                        player.getWorld().spawnParticle(Particle.END_ROD, orb.getLocation(), 3, 0.1, 0.1, 0.1, 0.05);

                        // Защита - блокирование снарядов
                        for (Entity entity : orb.getNearbyEntities(0.5, 0.5, 0.5)) {
                            if (entity instanceof Projectile) {
                                entity.remove();
                                player.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, entity.getLocation(), 10, 0, 0, 0, 0.2);
                            }
                        }
                    }
                }.runTaskTimer(Main.getInstance(), 1, 1);
            }

            // Длительность способности
            new BukkitRunnable() {
                @Override
                public void run() {
                    player.sendMessage("§bTenseigan Orbs §fрассеялись");
                }
            }.runTaskLater(Main.getInstance(), 20 * 30); // 30 секунд
        }
    }


    // 5. Пробуждение Тенсейгана (усиление)
    private void tenseiganAwakening(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 50, 0, 0, 0, 0)) {
            player.sendMessage("§b§lTENSEIGAN AWAKENING!");
            player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_DEATH, 1, 1);

            // Эффекты активации
            for (int i = 0; i < 20; i++) {
                player.getWorld().spawnParticle(Particle.FIREWORKS_SPARK,
                        player.getLocation().add(Math.random() * 3 - 1.5,
                                Math.random() * 3,
                                Math.random() * 3 - 1.5),
                        5, 0, 0, 0, 0.2);
            }

            // Баффы игроку
            player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20 * 30, 1));
            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20 * 30, 1));
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 30, 1));
            player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 20 * 30, 1));

            // Автоматическое создание сфер защиты
            tenseiganOrbs(player, pl);

            // Восстановление после окончания
            new BukkitRunnable() {
                @Override
                public void run() {
                    player.sendMessage("§bTenseigan §fвернулся в обычное состояние");
                    player.playSound(player.getLocation(), Sound.BLOCK_BEACON_DEACTIVATE, 1, 1);
                }
            }.runTaskLater(Main.getInstance(), 20 * 30);
        }
    }

    @Override
    public Item getItem() {
        return item;
    }
}