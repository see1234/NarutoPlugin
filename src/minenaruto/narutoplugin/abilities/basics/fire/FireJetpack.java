package minenaruto.narutoplugin.abilities.basics.fire;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import minenaruto.narutoplugin.ParticleEffect;
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

public class FireJetpack extends AbilitiesMain {
    private Item item = new Item(293, 54, "§7[§6Naruto§7] §cОгненный полет", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));
    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 150, 0, 0, 0, 0)) {


            runTaskAbility(player);
        }
    }

    @Override
    public void RightPlusShift(Player player, NarutoPlayer pl) {
        // TODO Auto-generated method stub
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 0, 0, 0, 0, 0)) {
            if (pl.IfHasJustuPointAndRemoveJustuPoint(5)) {
                //	player.getInventory().addItem(Item.items.get(5).getItemStack());
            }
        }
    }

    public void runTaskAbility(Player player) {
        BukkitRunnable task = new BukkitRunnable() {
            int count = 0;

            @Override
            public void run() {
                if (count++ != 200) {
                    player.setVelocity(player.getEyeLocation().getDirection().multiply(0.3));

                    ParticleEffect.FLAME.display(player.getLocation(), 10, 0.3, 0.3, 0.3);



                } else {

                    cancel();
                }
            }
        };
        task.runTaskTimer(Main.getInstance(), 1L, 1L);
    }


    public static void spawnParticle(Location location) {

        location.getWorld().spawnParticle(Particle.FLAME, location, 0);

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