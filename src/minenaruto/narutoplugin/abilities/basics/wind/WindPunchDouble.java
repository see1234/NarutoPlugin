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


import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.scheduler.*;
import org.bukkit.util.Vector;
import org.bukkit.entity.Entity;
public class WindPunchDouble extends AbilitiesMain {
    private minenaruto.narutoplugin.iditems.Item item = new minenaruto.narutoplugin.iditems.Item(Material.DIAMOND_HOE, 65, "§7[§6Naruto§7] §2Ветреный удар со взрывом", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));

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



        abilityStart(player);




    }
    @SuppressWarnings("rawtypes")
    public static void CreateEffect(final Location loc) {
        ParticleEffect.WATER_SPLASH.display(loc, 10, 0.3,0.3, 0.3);
    }
    public  void abilityStart(Player player ) {



        Location loc = player.getEyeLocation();


        new BukkitRunnable() {
            int count = 100;
            @Override
            public void run() {
                if(count-- == 0) {
                    cancel();
                    return;
                }
                if(loc.getBlock().getType() != Material.AIR) {
                    cancel();
                    return;
                }
                loc.add(loc.getDirection().multiply(2));
                Particle.DustOptions dust = new Particle.DustOptions(
                        Color.fromRGB((int) 1 * 255, (int) 1 * 255, (int) 1 * 255), 1);
                loc.getWorld().spawnParticle(Particle.REDSTONE, loc.getX(), loc.getY(), loc.getZ(), 8, 0.1, 0.1, 0.1, dust);

                for (Entity en : loc.getWorld().getNearbyEntities(loc,1,1,1)) {
                    if(en instanceof LivingEntity) {
                        if (AbilityListener.hasPvpZone(en)) {
                            AbilityListener.damageEntity(en, player, 5);
                        }
                        loc.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, loc.getX(), loc.getY(), loc.getZ(),3);
                        break;
                    }




                }


            }
        }.runTaskTimer(Main.getInstance(), 2, 2);





    }
    @Override
    public Item getItem() {
        return item;
    }





}
