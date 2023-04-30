package minenaruto.narutoplugin.abilities.basics.wind;
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
import org.bukkit.craftbukkit.v1_12_R1.entity.*;
import net.minecraft.server.v1_12_R1.*;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.scheduler.*;
import org.bukkit.util.Vector;
import org.bukkit.entity.Entity;
public class WindPush extends AbilitiesMain {
    private minenaruto.narutoplugin.iditems.Item item = new minenaruto.narutoplugin.iditems.Item(293, 65, "§7[§6Naruto§7] §2Ветреный толчок", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));

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



            effect(player);




    }
    @SuppressWarnings("rawtypes")
    public static void CreateEffect(final Location loc) {
        ParticleEffect.WATER_SPLASH.display(loc, 10, 0.3,0.3, 0.3);
    }
    public  void effect(Player player ) {
        for (final Entity entity : player.getLocation().getWorld().getNearbyEntities(player.getLocation(),5, 5, 5)) {
            if(entity.equals(player)) {
                return;
            }
            final LivingEntity target = (LivingEntity)entity;

            if(!AbilityListener.hasPvpZone(target)) {
                return;
            }
            final Vector p = player.getLocation().toVector();
            final Vector e2 = target.getLocation().toVector();
            final Vector v = e2.subtract(p).normalize().multiply(3);
            ParticleEffect.CLOUD.display(target.getLocation(),10, 0.3,0.3,0.3);
            v.setY(v.getY() + 1.5);
            if (v.getY() > 2.0) {
                v.setY(2);
            }


            entity.playEffect(EntityEffect.HURT);
            entity.setVelocity(v);
        }

    }
    @Override
    public Item getItem() {
        return item;
    }





}
