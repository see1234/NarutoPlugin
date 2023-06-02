package minenaruto.narutoplugin.abilities.Kekkei_Genkai.log;

import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.abilities.AbilityListener;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import minenaruto.narutoplugin.main.Main;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

public class LogSharpNeedles extends AbilitiesMain {
    private Item item = new Item(Material.DIAMOND_HOE, 66, "§7[§6Naruto§7] §aОстрые иглы", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));
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
        ArrayList<Block> ultBlocks = new ArrayList<>();
        for (Entity en : player.getNearbyEntities(5.0D, 5.0D, 5.0D)) {
            if (en instanceof LivingEntity) {
                if (en.getLocation().getBlock().getType() == Material.AIR) {
                    Block b = en.getLocation().getBlock();
                    b.setType(Material.ACACIA_LOG);
                    ultBlocks.add(b);
                }
                if (en instanceof Player) {
                    if (hasPvpZone(en)) {
                        en.setVelocity(new Vector(0, 2, 0));
                        addDamageEntity(player, (Player)en, 8);
                    }
                    continue;
                }
                ((LivingEntity)en).damage(8.0D);
                en.setVelocity(new Vector(0, 2, 0));
            }
        }
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> {
            for (Block b : ultBlocks)
                b.setType(Material.AIR);
            ultBlocks.clear();
        },40L);
    }

    @Override
    public Item getItem() {
        return item;
    }



}