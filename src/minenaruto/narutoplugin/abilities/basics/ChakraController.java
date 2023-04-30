package minenaruto.narutoplugin.abilities.basics;

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
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;

import java.util.*;

public class ChakraController extends AbilitiesMain implements Listener {
    private Item item = new Item(293, 2, "§7[§6Naruto§7] §9Контроль чакры", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));
    public static ArrayList<Player> enabledControlChakras = new ArrayList<>();

    public static WeakHashMap<Player, Integer> scheduler = new WeakHashMap<>();

    public static boolean isEnabled(Player p) {
        return enabledControlChakras.contains(p);
    }

    public static void enableControlChakras(Player p) {
        enabledControlChakras.add(p);
    }
    public ChakraController() {
        Bukkit.getPluginManager().registerEvents((Listener)this, (Plugin)Main.getInstance());
    }
    public static void removeControlChakras(Player p) {
        enabledControlChakras.remove(p);
        Bukkit.getScheduler().cancelTask(((Integer)scheduler.get(p)).intValue());
        scheduler.remove(p);
    }
    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 50, 0, 0, 0, 0)) {
            runTaskAbility(pl, player);
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
    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();

        if (isEnabled(p) &&
                p.getGameMode() != GameMode.CREATIVE && p.getLocation().subtract(0.0D, 1.0D, 0.0D).getBlock().getType() != Material.AIR && !p.isFlying())
            p.setAllowFlight(true);
    }

    @EventHandler
    public void onFlightAttempt(PlayerToggleFlightEvent event) {
        Player p = event.getPlayer();
        if (isEnabled(p)) {
            if (p.getGameMode() == GameMode.CREATIVE)
                return;
            event.setCancelled(true);
            p.setAllowFlight(false);
            p.setFlying(false);
            p.setVelocity(p.getLocation().getDirection().multiply(1).setY(1));
        }
    }
    public void runTaskAbility(NarutoPlayer np, Player player) {
        int rate = 2;
        if (!isEnabled(player)) {

            if (np.getInt("chakra") > 2) {
                enableControlChakras(player);
                player.sendMessage("§7[§6Naruto§7] §aКонтроль чакры активирован");
                int _scheduler = ((Integer)scheduler.getOrDefault(player, Integer.valueOf(0))).intValue();
                _scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), () -> {
                    if(np.getInt("chakra") <= 2) {
                        np.setInt("chakra",0);
                        removeControlChakras(player);
                        player.setAllowFlight(false);
                        player.setFlying(false);
                        player.sendMessage("§7[§6Naruto§7] §aКонтроль чакры деактивирован");
                    }
                    else {
                        np.setInt("chakra", np.getInt("chakra") - 2);
                    }

                },1L, 60L);
                scheduler.put(player, Integer.valueOf(_scheduler));
            } else {
                player.sendTitle("§4У вас недостаточно чакры", "", 5, 5, 5);
            }
        } else {
            removeControlChakras(player);
            player.setAllowFlight(false);
            player.setFlying(false);
            player.sendMessage("§7[§6Naruto§7] §aКонтроль чакры деактивирован");
        }
    }

    @Override
    public Item getItem() {
        return item;
    }



}