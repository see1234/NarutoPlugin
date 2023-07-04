package minenaruto.narutoplugin.abilities.basics.lightning;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import minenaruto.narutoplugin.ParticleEffect;
import org.bukkit.*;
import org.bukkit.block.Block;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;

import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.abilities.AbilityListener;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import minenaruto.narutoplugin.main.Main;

public class LightningBullets extends AbilitiesMain {
    private Item item = new Item(Material.DIAMOND_HOE, 57, "§7[§6Naruto§7] §bОсколки молнии", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));
    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 20, 0, 0, 0, 0)) {
            Location playerlocation = player.getLocation().clone();
            List<ArmorStand> arrayArmorStand = spawnArmorStand(playerlocation, 9);
            for (ArmorStand arm : arrayArmorStand) {
                runTaskAbility(arm, player);
            }
        }
    }


    public void runTaskAbility(ArmorStand arm, Player player) {
        BukkitRunnable task = new BukkitRunnable() {
            int count = 0;

            @Override
            public void run() {
                if (count++ != 200) {

                    try {

                        arm.teleport(arm.getLocation().add(player.getLocation().getDirection().multiply(2.5)));

                        Particle.DustOptions dust = new Particle.DustOptions(
                                Color.fromRGB((int) 0 * 255, (int) 0 * 255, (int) 1 * 255), 1);
                        arm.getLocation().getWorld().spawnParticle(Particle.REDSTONE, arm.getLocation().getX(), arm.getLocation().getY(), arm.getLocation().getZ(), 3, 0, 0, 0, dust);
                        Bukkit.getScheduler().runTask(Main.getInstance(), new Runnable() {
                            @Override
                            public void run() {

                                for (Entity en : arm.getLocation().getWorld().getNearbyEntities(arm.getLocation(), 1.0, 1.0, 1.0)) {

                                    if (!(en instanceof LivingEntity) || (en instanceof ArmorStand)
                                            || en == player)
                                        continue;

                                    if (en instanceof Player) {
                                        if (!hasPvpZone(en))
                                            continue;

                                        addDamageEntity(player, en, 6);

                                        en.setFireTicks(100);

                                        arm.remove();
                                        cancel();
                                        continue;
                                    }

                                    addDamageEntity(player, en, 8);
                                    en.setFireTicks(100);
                                    arm.remove();
                                    cancel();
                                }
                            }
                        });
                    }
                    catch(Exception ex) {

                    }
                    if (arm.getLocation().getBlock().getType() != Material.AIR) {
                        Bukkit.getScheduler().runTask(Main.getInstance(), new Runnable() {
                            @Override
                            public void run() {


                                for (Entity en : arm.getWorld().getNearbyEntities(arm.getLocation(), 1.0, 1.0, 1.0)) {

                                    if (!(en instanceof LivingEntity) || (en instanceof ArmorStand)
                                            || en == player)
                                        continue;

                                    if (en instanceof Player) {
                                        if (!hasPvpZone(en))
                                            continue;

                                        addDamageEntity(player, en, 6);

                                        en.setFireTicks(100);


                                        continue;
                                    }

                                    addDamageEntity(player, en, 8);
                                    en.setFireTicks(100);

                                }

                            }
                        });
                        arm.remove();
                        cancel();

                    }
                } else {
                    arm.remove();
                    cancel();
                }
            }
        };
        task.runTaskTimerAsynchronously(Main.getInstance(), 1L, 1L);
    }

    public List<ArmorStand> spawnArmorStand(Location loc, int size) {
        ArrayList<ArmorStand> arrayArmorStand = new ArrayList<ArmorStand>();
        for (int i = 0; i < size; i++) {
            Random rand = new Random();
            int boolx = rand.nextBoolean() ? rand.nextInt(i + 1) : -rand.nextInt(i + 1);
            int boolz = rand.nextBoolean() ? rand.nextInt(i + 1) : -rand.nextInt(i + 1);
            Vector vec = new Vector(boolx, (rand.nextInt(i + 1) / 2), boolz);
            ArmorStand armorstand = (ArmorStand) loc.getWorld().spawnEntity(loc.clone().add(vec),
                    EntityType.ARMOR_STAND);
            armorstand.setVisible(false);
            armorstand.setGravity(false);
            armorstand.setBasePlate(false);

            arrayArmorStand.add(armorstand);
        }
        return arrayArmorStand;
    }
    @Override
    public Item getItem() {

        return item;
    }
}