package minenaruto.narutoplugin.abilities.basics.fire;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
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

public class FireShieldOfFire extends AbilitiesMain {
    private Item item = new Item(Material.DIAMOND_HOE, 54, "§7[§6Naruto§7] §cЩит огня", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));
    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 150, 0, 0, 0, 0)) {


            runTaskAbility(player, player.getLocation().clone());
        }
    }



    public void runTaskAbility(Player player, Location loc) {
        BukkitRunnable task = new BukkitRunnable() {
            int count = 0;
            int h = 1;
            @Override
            public void run() {
                if (count++ != 200) {
                    if (h > 3) {
                        h=-3;
                    }
                    else {
                        h+=1;
                    }
                    spawnParticle(loc.clone().add(new Vector(0, h, 0)),4);
                    for (Entity en : loc.getWorld().getNearbyEntities(loc, 5.0, 2.0, 5.0)) {
                        if (!(en instanceof LivingEntity) || (en instanceof ArmorStand)
                                || !(loc.distance(en.getLocation()) < 5.0) || en == player)
                            continue;
                        if (en instanceof Player) {
                            if (!hasPvpZone(en))
                                continue;
                            addDamageEntity(player, en, 6);
                            en.setFireTicks(150);
                            //arm.remove();
                            continue;
                        }

                        addDamageEntity(player, en, 14);
                        en.setFireTicks(150);


                    }



                } else {

                    cancel();
                }
            }
        };
        task.runTaskTimer(Main.getInstance(), 1L, 1L);
    }

    public static void spawnParticle(Location location, double radius) {
        int points = 50;
        for (int i = 0; i < points; i++) {
            double angle = 2 * Math.PI * i / points;
            Location point = location.clone().add(radius * Math.sin(angle), 0.0d, radius * Math.cos(angle));
            location.getWorld().spawnParticle(Particle.FLAME, point, 0);
        }
    }

    public List<ArmorStand> spawnArmorStand(Location loc, int size) {
        ArrayList<ArmorStand> arrayArmorStand = new ArrayList<ArmorStand>();
        for (int i = 0; i < size; i++) {

            // int boolx = rand.nextBoolean() ? rand.nextInt(i + 1) : -rand.nextInt(i + 1);
            // int boolz = rand.nextBoolean() ? rand.nextInt(i + 1) : -rand.nextInt(i + 1);
            // Vector vec = new Vector(boolx, (rand.nextInt(i + 1) / 2), boolz);
            ArmorStand armorstand = (ArmorStand) loc.getWorld().spawnEntity(loc.clone(), EntityType.ARMOR_STAND);
            armorstand.setGravity(false);
            armorstand.setBasePlate(false);
            armorstand.setVisible(false);
            // armorstand.setItemInHand(Item.items.get(2).getItemStack());
            arrayArmorStand.add(armorstand);
        }
        return arrayArmorStand;
    }
    @Override
    public Item getItem() {

        return item;
    }

}