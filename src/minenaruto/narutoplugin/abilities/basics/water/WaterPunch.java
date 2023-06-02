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
public class WaterPunch extends AbilitiesMain {
    private minenaruto.narutoplugin.iditems.Item item = new minenaruto.narutoplugin.iditems.Item(Material.DIAMOND_HOE, 64, "§7[§6Naruto§7] §bВодная пуля", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 50, 0, 0, 0, 0)) {
            runTaskAbility(player);
        }
    }

    @Override
    public void RightPlusShift(Player player, NarutoPlayer pl) {
        // TODO Auto-generated method stub
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 0, 0, 0, 0, 0)) {
            if(pl.IfHasJustuPointAndRemoveJustuPoint(5)) {
                //player.getInventory().addItem(Item.items.get(6).getItemStack());
            }
        }
    }

    public void runTaskAbility(Player player) {
        final LivingEntity entityTarget = rayTraceEntity(player, 30);
        if(entityTarget == null) {
            player.sendMessage("§7[§6Naruto§7] §f" + "Вы промахнулись и потратили чакру");
        }
        else {
            effect(player,entityTarget.getLocation(), entityTarget);

        }


    }
    @SuppressWarnings("rawtypes")
    public static void CreateEffect(final Location loc) {
        ParticleEffect.WATER_SPLASH.display(loc, 10, 0.3,0.3, 0.3);
    }
    public  void effect(Player player, final Location loc, final LivingEntity entityTarget) {
        final BukkitRunnable runable = new BukkitRunnable() {
            Vector current = loc.toVector();
            World world = entityTarget.getWorld();
            int timer = 100;

            public void run() {
                if (this.timer-- >= 0) {
                    final Vector targetVector = entityTarget.getLocation().toVector();
                    final Vector tempvector = this.current.clone();
                    tempvector.subtract(entityTarget.getLocation().toVector()).normalize();
                    final Vector diff = this.current.subtract(tempvector);
                    final Location loc1 = diff.toLocation(this.world);
                    CreateEffect(loc1);
                    if (this.current.distanceSquared(targetVector) < 4.0) {

                        addDamageEntity(player, entityTarget, 3);
                        this.cancel();
                    }
                }
                else {
                    this.cancel();
                }
            }
        };
        runable.runTaskTimer((Plugin)Main.instance, 1L, 1L);
    }
    @Override
    public Item getItem() {
        return item;
    }





}
