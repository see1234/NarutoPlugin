package minenaruto.narutoplugin.abilities.reningan;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.abilities.AbilityListener;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import minenaruto.narutoplugin.main.Main;
import org.bukkit.scheduler.BukkitRunnable;

public class RenninganFly extends AbilitiesMain {
    private Item item = new Item(293, 70, "§7[§6Naruto§7] §5Риннеган (Полет)", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));
    private WeakHashMap<Player, Boolean> flying = new WeakHashMap<Player,Boolean>();
    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 10, 0, 0, 0, 0)) {
            //	List<ArmorStand> arrayArmorStand = spawnArmorStand(playerlocation, 1);
            runTaskAbility(player, pl);

        }
    }

    @Override
    public void RightPlusShift(Player player, NarutoPlayer pl) {
        // TODO Auto-generated method stub
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 0, 0, 0, 0, 0)) {
            if(pl.IfHasJustuPointAndRemoveJustuPoint(5)) {
                //player.getInventory().addItem(Item.items.get(16).getItemStack());
            }
        }
    }

    @Override
    public Item getItem() {
        return item;
    }

    public void runTaskAbility(Player player, NarutoPlayer pl) {
        if(flying.containsKey(player)) {
            player.sendMessage("§7[§6Naruto§7] §f" + "Деактивирована способность полета");
            flying.remove(player);
            player.setAllowFlight(false);
            if(player.isFlying()) {
                player.setFlying(false);
            }
            return;
        }
        player.sendMessage("§7[§6Naruto§7] §f" + "Активирована способность полета");
        flying.put(player, true);
        BukkitRunnable task = new BukkitRunnable() {
            int count = 0;

            @Override
            public void run() {
                if(player == null) {


                    cancel();
                    return;
                }
                if(!flying.containsKey(player)) {
                    player.setAllowFlight(false);

                    cancel();
                    return;
                }
                if(pl.getInt("chakra") < 10) {

                    player.setAllowFlight(false);

                    cancel();
                    return;
                }
                else {
                    pl.setInt("chakra", pl.getInt("chakra") - 10);
                    player.setAllowFlight(true);
                }


            }
        };
        task.runTaskTimer(Main.getInstance(), 20L, 20L);

    }





}