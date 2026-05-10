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

public class TenseiganChakraAbsorption extends AbilitiesMain {
    private Item item = new Item(Material.DIAMOND_HOE, 90, "§7[§6Naruto§7] §bТенсейган поглощение чакры",
            List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));



    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 100, 0, 0, 0, 0)) {
            chakraAbsorption(player, pl);
        }
    }




    // 3. Поглощение чакры
    private void chakraAbsorption(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 10, 0, 0, 0, 0)) {
            int absorbed = 0;

            for (Entity entity : player.getNearbyEntities(5, 5, 5)) {
                if (entity instanceof LivingEntity && entity != player) {
                    absorbed += 5;

                    // Эффект поглощения
                    player.getWorld().spawnParticle(Particle.VILLAGER_HAPPY,
                            ((LivingEntity) entity).getEyeLocation(), 10, 0.5, 0.5, 0.5, 0.1);


                    // Небольшой урон врагу
                    addDamageEntity(player, entity, 2);
                }
            }

            if (absorbed > 0) {
                // Восстановление чакры
                int maxChakra = (pl.getInt("ninjustu") * 10) + 100;
                int adjustedChakra = Math.min(maxChakra, pl.getInt("chakra") + absorbed);

                if (adjustedChakra <= maxChakra) {
                    pl.setInt("chakra", adjustedChakra);
                }
                player.sendMessage("§bTenseigan §fпоглотил §a" + absorbed + " §fчакры");
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
            } else {
                player.sendMessage("§bTenseigan §fне нашел чакру для поглощения");
            }
        }
    }



    @Override
    public Item getItem() {
        return item;
    }
}