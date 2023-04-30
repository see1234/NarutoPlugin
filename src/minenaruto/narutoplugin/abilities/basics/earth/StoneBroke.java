package minenaruto.narutoplugin.abilities.basics.earth;

import java.util.*;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftEntity;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityChangeBlockEvent;
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

public class StoneBroke extends AbilitiesMain {
    private Item item = new Item(293, 52, "§7[§6Naruto§7] §6Разрыв земли", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));
    public static ArrayList<FallingBlock> fBlocks = new ArrayList<>();
    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 20, 0, 0, 0, 0)) {
            Location playerlocation = player.getLocation().clone();

            runTaskAbility(player);

        }
    }


    @Override
    public void RightPlusShift(Player player, NarutoPlayer pl) {
        // TODO Auto-generated method stub
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 0, 0, 0, 0, 0)) {
            if (pl.IfHasJustuPointAndRemoveJustuPoint(5)) {
                //	player.getInventory().addItem(Item.items.get(10).getItemStack());
            }
        }
    }

    public void runTaskAbility(Player player) {
        if (player.isOnGround() && (player.getLocation().getBlock().getType() != Material.WATER || player.getLocation().getBlock().getType() != Material.LAVA)) {
            int _scheduler = ((Integer) scheduler.getOrDefault(player, Integer.valueOf(0))).intValue();
            ArrayList<Block> blocks = new ArrayList<>();
            HashMap<Block, Material> blockType = new HashMap<>();
            HashMap<Player, Integer> scheduler = new HashMap<>();
            Location loc = player.getLocation();
            int radius = 3;
            for (Entity en : player.getNearbyEntities(radius, radius, radius)) {
                if (en instanceof LivingEntity) {
                    if (en instanceof Player) {
                        if (hasPvpZone(en)) {
                            addDamageEntity(player, (Player)en, 5);


                        }
                    } else {
                        ((LivingEntity)en).damage(10.0D);
                    }
                    en.setVelocity(en.getLocation().getDirection().add(new Vector(0.0D, 1.5D, 0.0D)));
                }
            }
            int x = loc.getBlockX() - radius;
            for (; x <= loc.getBlockX() + radius; x++) {
                for (int y = loc.getBlockY() - 2; y <= loc.getBlockY(); y++) {
                    for (int z = loc.getBlockZ() - radius; z <= loc.getBlockZ() + radius; z++) {
                        Location l = new Location(loc.getWorld(), x, y, z);
                        if ((l.getBlock().getType().equals(Material.DIRT) || l.getBlock().getType().equals(Material.GRASS) || l.getBlock().getType().equals(Material.SAND) || l
                                .getBlock().getType().equals(Material.STONE) || l.getBlock().getType().equals(Material.COBBLESTONE)) &&
                                l.distance(loc) <= radius) {
                            blocks.add(l.getBlock());
                            blockType.put(l.getBlock(), l.getBlock().getType());
                        }
                    }
                }
            }
            for (Block b : blocks) {
                FallingBlock fb = player.getWorld().spawnFallingBlock(b.getLocation().add(0.0D, 2.0D, 0.0D), b.getType(), (byte)0);
                fb.setVelocity(new Vector(-1.0F + (float)(Math.random() * 3.0D), 2.0F, -0.3F + (float)(Math.random() * 1.6D)));
                fBlocks.add(fb);
                b.setType(Material.BARRIER);
            }
            Iterator<Block> blockIterator = blocks.iterator();
            _scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)Main.getInstance(), () -> {
                if (blockIterator.hasNext()) {
                    Block b = blockIterator.next();
                    b.setType((Material)blockType.get(b));
                } else {
                    Bukkit.getScheduler().cancelTask(((Integer)scheduler.get(player)).intValue());
                }
            },100L, 1L);
            scheduler.put(player, Integer.valueOf(_scheduler));
        }
     else {
        player.sendTitle("", ChatColor.RED + "Встаньте на землю", 20, 20, 20);
    }
   }






    @Override
    public Item getItem() {

        return item;
    }
}