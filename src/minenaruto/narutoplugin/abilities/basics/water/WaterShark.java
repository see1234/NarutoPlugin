package minenaruto.narutoplugin.abilities.basics.water;
import java.util.*;

import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.abilities.AbilityListener;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import minenaruto.narutoplugin.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class WaterShark extends AbilitiesMain {
    private minenaruto.narutoplugin.iditems.Item item = new minenaruto.narutoplugin.iditems.Item(293, 64, "§7[§6Naruto§7] §bАкула-Ракета", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));

    public static HashMap<ArmorStand, Player> distance = new HashMap<>();

    public static HashMap<ArmorStand, Integer> scheduler = new HashMap<>();

    public  void TrowShark(Player p, double Distance) {
        if (!distance.containsValue(p)
                 ) {
                        ArmorStand stand = (ArmorStand)p.getWorld().spawnEntity(p.getLocation(), EntityType.ARMOR_STAND);
        stand.setVisible(false);
        ((EntityEquipment)Objects.<EntityEquipment>requireNonNull(stand.getEquipment())).setItemInMainHand(getModels().get("shark").getItemStack());
                stand.setGravity(false);
        stand.setArms(true);
        int _scheduler = ((Integer)scheduler.getOrDefault(stand, Integer.valueOf(0))).intValue();
        distance.put(stand, p);
        _scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin) Main.getInstance(), () -> {
            if (p.getWorld() == stand.getWorld()) {
                if (distance.containsKey(stand) && distance.containsValue(p))
                    if (p.getLocation().distance(stand.getLocation()) < Distance) {
                        stand.teleport(stand.getLocation().add(stand.getLocation().getDirection().multiply(1)));
                        for(Entity en : stand.getNearbyEntities(1,1,1)) {
                            AbilityListener.damageEntity(en, p, 4);
                        }
                        if (stand.getEyeLocation().getBlock().getType() != Material.AIR && !stand.getEyeLocation().getBlock().getType().name().endsWith("PLANT") && stand.getEyeLocation().getBlock().getType() != Material.GRASS && stand.getEyeLocation().getBlock().getType().name().endsWith("_GRASS")) {
                            distance.remove(stand, p);
                            Bukkit.getScheduler().cancelTask(((Integer)scheduler.get(stand)).intValue());
                            stand.remove();
                        }
                    } else {
                        distance.remove(stand, p);
                        Bukkit.getScheduler().cancelTask(((Integer)scheduler.get(stand)).intValue());
                        stand.remove();
                    }
            } else {
                distance.remove(stand, p);
                stand.remove();
                Bukkit.getScheduler().cancelTask(((Integer)scheduler.get(stand)).intValue());
            }
        },0L, 0L);
        scheduler.put(stand, Integer.valueOf(_scheduler));
    }
}

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
        TrowShark(player, 20.0D);
    }
    @Override
    public Item getItem() {
        return item;
    }
}
