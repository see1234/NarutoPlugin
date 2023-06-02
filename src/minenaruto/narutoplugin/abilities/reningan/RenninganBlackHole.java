package minenaruto.narutoplugin.abilities.reningan;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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

public class RenninganBlackHole extends AbilitiesMain {
    public static ArrayList<Entity> dropItem = new ArrayList<>();

    private Item item = new Item(Material.DIAMOND_HOE, 70, "§7[§6Naruto§7] §5Риннеган (Черная дыра)", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));
    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 100, 0, 0, 0, 0)) {
            //	List<ArmorStand> arrayArmorStand = spawnArmorStand(playerlocation, 1);
            runTaskAbility(player);

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

    public void runTaskAbility(Player player) {
        ArrayList<Entity> entities = new ArrayList<>();
        int distance = 10;
        Location whereCreateSphere = player.getLocation().add(0.0D, 2.0D, 0.0D);
        ItemStack item = new ItemStack(Material.COAL_BLOCK, 1);
        org.bukkit.entity.Item item1 = player.getWorld().dropItem(whereCreateSphere, item);
        item1.setGravity(false);
        dropItem.add(item1);
        int _scheduler = ((Integer)scheduler.getOrDefault(player, Integer.valueOf(0))).intValue();
        _scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)Main.getInstance(), () -> {
            for (Entity en : item1.getNearbyEntities(distance, distance, distance)) {
                if (en != player && en instanceof LivingEntity && !(en instanceof ArmorStand)) {
                    if (en instanceof Player) {
                        if (hasPvpZone(en)) {
                            addDamageEntity(player, (Player)en, 10);
                            en.teleport(item1.getLocation());
                            en.setGravity(false);
                            entities.add(en);
                        }
                        continue;
                    }
                    en.teleport(item1.getLocation());
                    en.setGravity(false);
                    entities.add(en);
                }
            }
        },1L, 1L);
        scheduler.put(player, Integer.valueOf(_scheduler));
        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.getInstance(), () -> {
            Bukkit.getScheduler().cancelTask(((Integer)scheduler.get(player)).intValue());
            item1.remove();
            for (Entity en : entities) {
                en.setGravity(true);
            }
            if (scheduler.containsKey(player)) {
                scheduler.remove(player);
            }
        },100L);



    }





}