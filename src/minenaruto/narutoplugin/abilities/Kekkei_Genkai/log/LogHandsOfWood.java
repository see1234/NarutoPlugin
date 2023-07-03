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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

public class LogHandsOfWood extends AbilitiesMain {
    private Item item = new Item(Material.DIAMOND_HOE, 66, "§7[§6Naruto§7] §aРуки-дерево", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 50, 0, 0, 0, 0)) {
            runTaskAbility(player);
        }
    }


    public void runTaskAbility(Player player) {
        int _scheduler = ((Integer)scheduler.getOrDefault(player, Integer.valueOf(0))).intValue();
        ArrayList<Block> blockList = (ArrayList<Block>)blocks.getOrDefault(player, new ArrayList<Block>());
        blocks.put(player, blockList);
        BlockIterator iterator = new BlockIterator((LivingEntity)player, 10);
        iterator.next().setType(Material.AIR);

        _scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), () -> {
            if (iterator.hasNext()) {
                Block block = iterator.next();
                if (block.getType() == Material.AIR) {
                    block.setType(Material.ACACIA_LOG);
                    ((ArrayList<Block>)blocks.get(player)).add(block);
                    for (Entity entity : block.getLocation().getWorld().getNearbyEntities(block.getLocation(), 2.0D, 2.0D, 2.0D)) {
                        if (entity != player) {
                            if (entity instanceof Player) {
                                if (hasPvpZone(entity)) {
                                    addDamageEntity(player, (Player)entity,6);
                                    entity.setVelocity(block.getLocation().getDirection().add(new Vector(0, 1, 0)));
                                    ((LivingEntity)entity).addPotionEffect(new PotionEffect(PotionEffectType.POISON, 80, 1));

                                }
                                continue;
                            }
                            if (entity instanceof LivingEntity) {
                                entity.setVelocity(block.getLocation().getDirection().add(new Vector(0, 1, 0)));
                                ((LivingEntity)entity).addPotionEffect(new PotionEffect(PotionEffectType.POISON, 80, 1));
                                addDamageEntity(player,entity, 6);
                            }
                        }
                    }
                } else {
                    for (int size = ((ArrayList)blocks.get(player)).size(); size < 10 - ((ArrayList)blocks.get(player)).size(); size++) {
                        if (iterator.hasNext())
                            iterator.next();
                    }
                }
            } else if (!((ArrayList)blocks.get(player)).isEmpty()) {
                Block block = ((ArrayList<Block>)blocks.get(player)).remove(0);
                block.setType(Material.AIR);
            } else {
                Bukkit.getScheduler().cancelTask(((Integer)scheduler.get(player)).intValue());
                ((ArrayList)blocks.get(player)).clear();
                scheduler.remove(player);
            }
        },1L, 1L);
        scheduler.put(player, Integer.valueOf(_scheduler));
    }
    @Override
    public Item getItem() {
        return item;
    }



}