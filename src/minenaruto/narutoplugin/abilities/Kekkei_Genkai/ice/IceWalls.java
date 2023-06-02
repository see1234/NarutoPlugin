package minenaruto.narutoplugin.abilities.Kekkei_Genkai.ice;

import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.abilities.AbilityListener;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import minenaruto.narutoplugin.main.Main;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;

public class IceWalls extends AbilitiesMain {
    private Item item = new Item(Material.DIAMOND_HOE, 55, "§7[§6Naruto§7] §bЩит из льда", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));
    public static WeakHashMap<Player, Integer> logScheduler = new WeakHashMap<>();
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
        ArrayList<Block> blocks = new ArrayList<>();
        int _scheduler = ((Integer)scheduler.getOrDefault(player, Integer.valueOf(0))).intValue();
        int height = 5;
        for (int y = 0; y < height; y++) {
            Block b = player.getWorld().getBlockAt(player.getLocation().add(1.0D, y, 0.0D));
            Block b2 = player.getWorld().getBlockAt(player.getLocation().add(0.0D, y, 1.0D));
            Block b3 = player.getWorld().getBlockAt(player.getLocation().add(-1.0D, y, 0.0D));
            Block b4 = player.getWorld().getBlockAt(player.getLocation().add(0.0D, y, -1.0D));
            if (b.getType() == Material.AIR)
                blocks.add(b);
            if (b2.getType() == Material.AIR)
                blocks.add(b2);
            if (b3.getType() == Material.AIR)
                blocks.add(b3);
            if (b4.getType() == Material.AIR)
                blocks.add(b4);
        }
        Iterator<Block> iterator = blocks.iterator();
        _scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), () -> {
            if (iterator.hasNext()) {
                Block b = iterator.next();
                if (b.getType() == Material.AIR)
                    b.setType(Material.ICE);
            } else {
                if (scheduler.containsKey(player))
                    Bukkit.getScheduler().cancelTask(((Integer)scheduler.get(player)).intValue());
                scheduler.remove(player);
            }
        },1L, 1L);
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> {
            for (Block b : blocks)
                b.setType(Material.AIR);
            blocks.clear();
        },120L);
        scheduler.put(player, Integer.valueOf(_scheduler));
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 80, 2));
    }
    @Override
    public Item getItem() {
        return item;
    }



}