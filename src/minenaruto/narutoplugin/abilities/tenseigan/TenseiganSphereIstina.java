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

public class TenseiganSphereIstina extends AbilitiesMain {
    private Item item = new Item(Material.DIAMOND_HOE, 90, "§7[§6Naruto§7] §bТенсейган сфера",
            List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));



    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 100, 0, 0, 0, 0)) {
            truthSeekerOrbs(player, pl);
        }
    }


    // 4. Сферы истинного поиска (упрощенная версия)
    private void truthSeekerOrbs(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 30, 0, 0, 0, 0)) {
            Location loc = player.getEyeLocation();
            Vector direction = loc.getDirection().normalize();

            for (int i = 0; i < 3; i++) {
                ArmorStand orb = (ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
                orb.setVisible(false);
                orb.setGravity(false);

                new BukkitRunnable() {
                    int ticks = 0;

                    @Override
                    public void run() {
                        if (ticks++ > 100 || orb.isDead()) {
                            orb.remove();
                            cancel();
                            return;
                        }

                        // Движение вперед
                        orb.teleport(orb.getLocation().add(direction.clone().multiply(0.8)));

                        // Эффекты
                        player.getWorld().spawnParticle(Particle.SMOKE_LARGE, orb.getLocation(), 2, 0.2, 0.2, 0.2, 0.05);
                        player.getWorld().spawnParticle(Particle.DRAGON_BREATH, orb.getLocation(), 1, 0, 0, 0, 0.1);

                        // Урон и эффекты при попадании
                        for (Entity entity : orb.getNearbyEntities(1.5, 1.5, 1.5)) {
                            if (entity instanceof LivingEntity && entity != player) {
                                addDamageEntity(player, entity, 8);
                                ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 20 * 3, 1));
                                orb.remove();
                                cancel();
                                break;
                            }
                        }
                    }
                }.runTaskTimer(Main.getInstance(), i * 5, 1);
            }
        }
    }



    @Override
    public Item getItem() {
        return item;
    }
}