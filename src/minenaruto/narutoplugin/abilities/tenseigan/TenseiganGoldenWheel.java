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

public class TenseiganGoldenWheel extends AbilitiesMain {
    private Item item = new Item(Material.DIAMOND_HOE, 90, "§7[§6Naruto§7] §bТенсейган золотое колесо перерождения",
            List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));



    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 100, 0, 0, 0, 0)) {
            goldenWheelReincarnation(player, pl);
        }
    }



    // 2. Золотое колесо перерождения
    private void goldenWheelReincarnation(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 40, 0, 0, 0, 0)) {
            Location center = player.getLocation();

            // Эффект активации
            player.getWorld().playSound(center, Sound.ENTITY_ENDER_DRAGON_GROWL, 1, 1);
            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20 * 5, 2));

            new BukkitRunnable() {
                double radius = 0;

                @Override
                public void run() {
                    if (radius > 8) {
                        cancel();
                        return;
                    }

                    // Создание кругового взрыва
                    for (double angle = 0; angle < 2 * Math.PI; angle += Math.PI / 16) {
                        double x = Math.cos(angle) * radius;
                        double z = Math.sin(angle) * radius;
                        Location loc = center.clone().add(x, 0, z);

                        // Эффекты
                        player.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, loc, 2, 0, 0, 0, 0.1);

                        // Урон мобам
                        for (Entity entity : loc.getWorld().getNearbyEntities(loc, 1, 1, 1)) {
                            if (entity instanceof LivingEntity && entity != player) {
                                addDamageEntity(player, entity, 6);
                                ((LivingEntity) entity).setVelocity(new Vector(0, 0.5, 0));
                            }
                        }
                    }

                    radius += 0.5;
                }
            }.runTaskTimer(Main.getInstance(), 0, 2);
        }
    }



    @Override
    public Item getItem() {
        return item;
    }
}