package minenaruto.narutoplugin.abilities.basics.fire;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
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

public class FireSimpleFlame extends AbilitiesMain {
    private Item item = new Item(293, 54, "§7[§6Naruto§7] §cПростое пламя", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));
    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 100, 0, 0, 0, 0)) {
            runTaskAbility(player);
        }
    }

    @Override
    public void RightPlusShift(Player player, NarutoPlayer pl) {
        // TODO Auto-generated method stub
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 0, 0, 0, 0, 0)) {
            if(pl.IfHasJustuPointAndRemoveJustuPoint(5)) {
                player.getInventory().addItem(getItem().getItemStack());
            }
        }
    }

    public void runTaskAbility(Player player) {
        BukkitRunnable task = new BukkitRunnable() {
            int count = 0;

            @Override
            public void run() {
                if (count++ != 200) {
                    try {

                        Location loc = player.getLocation().clone().add(player.getLocation().clone().getDirection().multiply(3));
                        spawnSphere(loc);
                        for (Entity en : loc.getWorld().getNearbyEntities(loc, 1.0, 2.0, 1.0)) {
                            if (!(en instanceof LivingEntity) || (en instanceof ArmorStand)
                                    || !(loc.distance(en.getLocation()) < 1.5) || en == player)
                                continue;
                            if (en instanceof Player) {
                                if (!hasPvpZone(en))
                                    continue;
                                addDamageEntity(player, en, 4);
                                en.setFireTicks(150);
                                //arm.remove();
                                continue;
                            }

                            addDamageEntity(player, en, 5);
                            en.setFireTicks(150);


                        }
                        final Vector targetVector = loc.toVector();
                        final Vector tempvector = player.getLocation().toVector().clone();
                        tempvector.subtract(loc.toVector()).normalize();
                        final Vector diff = player.getLocation().toVector().subtract(tempvector);
                        final Location loc1 = diff.toLocation(player.getWorld());
                        loc1.getWorld().spawnParticle(Particle.FLAME, loc1, 0);
                    }
                    catch(Exception ex) {

                    }
                } else {
                    cancel();
                }
            }
        };
        task.runTaskTimerAsynchronously(Main.getInstance(), 1L, 1L);
    }
    public static void spawnSphere(Location loc) {
        for (double i = 0; i <= Math.PI; i += Math.PI / 4) {
            double radius = Math.sin(i);
            double y = Math.cos(i) + 1;
            for (double a = 0; a < Math.PI * 2; a+= Math.PI / 4) {
                double x = Math.cos(a) * radius;
                double z = Math.sin(a) * radius;
                loc.add(x, y, z);

                loc.getWorld().spawnParticle(Particle.FLAME, loc, 0);
                loc.subtract(x, y, z);

            }
        }
    }

    @Override
    public Item getItem() {

        return item;
    }


}