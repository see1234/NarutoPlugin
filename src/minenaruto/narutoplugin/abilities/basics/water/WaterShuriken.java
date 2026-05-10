package minenaruto.narutoplugin.abilities.basics.water;
import minenaruto.narutoplugin.ParticleEffect;
import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.abilities.AbilityListener;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import minenaruto.narutoplugin.main.Main;
import org.bukkit.World;
import org.bukkit.plugin.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.event.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import org.bukkit.event.block.*;
import org.bukkit.*;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.*;


import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.scheduler.*;
import org.bukkit.util.Vector;
import org.bukkit.entity.Entity;
public class WaterShuriken extends AbilitiesMain {
    private Item item = new Item(Material.DIAMOND_HOE, 71, "§7[§6Naruto§7] §bВодяные кинжалы", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 15, 0, 0, 0, 0)) {
            Location loc = player.getLocation().clone();
            spawnShurikens(loc, player);
        }
    }

    public void spawnShurikens(Location loc, Player player) {
        for (int i = 0; i < 5; i++) {
            ArmorStand shuriken = (ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
            shuriken.setVisible(false);
            shuriken.setGravity(false);
            runShurikenAbility(shuriken, player);
        }
    }

    public void runShurikenAbility(ArmorStand shuriken, Player player) {
        BukkitRunnable task = new BukkitRunnable() {
            int count = 0;

            @Override
            public void run() {
                if (count++ != 50) {
                    shuriken.teleport(shuriken.getLocation().add(player.getLocation().getDirection().multiply(1.5)));

                    // Эффекты воды
                    shuriken.getLocation().getWorld().spawnParticle(Particle.WATER_SPLASH, shuriken.getLocation(), 5, 0.5, 0.5, 0.5, 0.1);

                    // Нанесение урона
                    for (Entity en : shuriken.getLocation().getWorld().getNearbyEntities(shuriken.getLocation(), 1, 1, 1)) {
                        if (!(en instanceof LivingEntity) || en == player)
                            continue;

                        addDamageEntity(player, en, 4);
                    }
                } else {
                    shuriken.remove();
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