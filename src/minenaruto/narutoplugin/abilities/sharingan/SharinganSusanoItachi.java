package minenaruto.narutoplugin.abilities.sharingan;

import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.abilities.AbilityListener;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import minenaruto.narutoplugin.main.Main;
import minenaruto.narutoplugin.models.ModelsMain;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.libs.it.unimi.dsi.fastutil.Hash;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;

import java.util.*;

public class SharinganSusanoItachi extends AbilitiesMain implements Listener {
    private Item item = new Item(293, 77, "§7[§6Naruto§7] §4Шаринган Итачи (Сусаноо)", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));
    public  ArrayList<Player> enabledControl = new ArrayList<Player>();

    public  WeakHashMap<Player, Integer> scheduler = new WeakHashMap<Player, Integer>();

    public  boolean isEnabled(Player p) {

        return enabledControl.contains(p);
    }

    public  void enableControl(Player p) {
        enabledController.put(p, this.getClass().getSimpleName());
        enabledControl.add(p);

    }
    public SharinganSusanoItachi() {
        Bukkit.getPluginManager().registerEvents((Listener)this, (Plugin)Main.getInstance());
    }
    public  void removeControl(Player p) {
        enabledControl.remove(p);
        Bukkit.getScheduler().cancelTask(((Integer)scheduler.get(p)).intValue());
        enabledController.remove(p);
        scheduler.remove(p);
    }
    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName())) {
        if(enabledController.containsKey(player)  && !enabledController.containsValue(this.getClass().getSimpleName())) {
            player.sendMessage("§7[§6Naruto§7] §4Сусаноо уже активировано");
        }
        else {

            if (AbilityListener.checkChakraItem(player, getItem().getName(), 50, 0, 0, 0, 0)) {

                runTaskAbility(pl, player);
            }
        }
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
    public void onMove(PlayerQuitEvent e) {
        Player p = e.getPlayer();

        if (isEnabled(p)) {
            if(arm.containsKey(p)) {
                if (!arm.get(p).isDead()) {
                    arm.get(p).remove();
                }
            }

        }

    }


    public void runTaskAbility(NarutoPlayer np, Player player) {

        if (!isEnabled(player)) {

            if (np.getInt("chakra") > 2) {
                enableControl(player);
                ArmorStand armorstand = (ArmorStand) player.getWorld().spawnEntity(player.getLocation(),
                        EntityType.ARMOR_STAND);
                armorstand.setVisible(false);
                armorstand.setGravity(false);
                armorstand.setBasePlate(false);
                armorstand.setHelmet(ModelsMain.models.get("susano_itachi").getItemStack());
                player.setPassenger(armorstand);
                arm.put(player, armorstand);
                player.sendMessage("§7[§6Naruto§7] §4Сусаноо Итачи активировано");
                int _scheduler = ((Integer)scheduler.getOrDefault(player, Integer.valueOf(0))).intValue();
                _scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), () -> {
                    if(np.getInt("chakra") <= 2) {
                        np.setInt("chakra", 0);
                        removeControl(player);
                        if (arm.containsKey(player)) {
                            if(!arm.get(player).isDead()) {
                                arm.get(player).remove();
                            }
                        }
                        player.sendMessage("§7[§6Naruto§7] §4Сусаноо Итачи деактивировано");
                    }
                    else {
                        player.removePotionEffect(PotionEffectType.JUMP);
                        player.removePotionEffect(PotionEffectType.SPEED);
                        player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
                        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 60, 10));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 60, 10));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 60, 10));
                        np.setInt("chakra", np.getInt("chakra") - 2);
                    }

                },1L, 60L);
                scheduler.put(player, Integer.valueOf(_scheduler));
            } else {
                player.sendTitle("§4У вас недостаточно чакры", "", 5, 5, 5);
            }
        } else {

            removeControl(player);
            player.sendMessage("§7[§6Naruto§7] §4Сусаноо Итачи деактивировано");
            if (arm.containsKey(player)) {
                if(!arm.get(player).isDead()) {
                    arm.get(player).remove();
                }
            }

        }
    }

    @Override
    public Item getItem() {
        return item;
    }



}