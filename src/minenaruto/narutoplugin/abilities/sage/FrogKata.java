package minenaruto.narutoplugin.abilities.sage;

import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.abilities.AbilityListener;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import minenaruto.narutoplugin.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

public class FrogKata extends AbilitiesMain implements Listener {
    private Item item = new Item(Material.DIAMOND_HOE, 80, "§7[§6Naruto§7] §6Sage (Frog Kata)", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));
    public ArrayList<Player> enabledControl = new ArrayList<Player>();
    public WeakHashMap<Player, Integer> scheduler = new WeakHashMap<Player, Integer>();

    public FrogKata() {
        super();
        Bukkit.getPluginManager().registerEvents((Listener) this, (Plugin) Main.getInstance());
    }

    public boolean isEnabled(Player p) {
        return enabledControl.contains(p);
    }

    public void enableControl(Player p) {
        enabledController.put(p, this.getClass().getSimpleName());
        enabledControl.add(p);
    }

    public void removeControl(Player p) {
        enabledControl.remove(p);
        Bukkit.getScheduler().cancelTask(scheduler.get(p));
        enabledController.remove(p);
        scheduler.remove(p);
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName())) {
            if (enabledController.containsKey(player) && !enabledController.containsValue(this.getClass().getSimpleName())) {
                player.sendMessage("§7[§6Naruto§7] §6Frog Kata уже активировано");
            } else {
                if (AbilityListener.checkChakraItem(player, getItem().getName(), 30, 0, 0, 0, 0)) {
                    runTaskAbility(pl, player);
                }
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if (isEnabled(p)) {
            removeControl(p);
        }
    }

    public void runTaskAbility(NarutoPlayer np, Player player) {
        if (!isEnabled(player)) {
            if (np.getInt("chakra") > 5) {
                enableControl(player);
                player.sendMessage("§7[§6Naruto§7] §6Frog Kata активировано");

                // Apply initial effects


                // Start periodic task
                int taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), () -> {
                    if (np.getInt("chakra") <= 5) {
                        np.setInt("chakra", 0);
                        removeControl(player);
                        player.sendMessage("§7[§6Naruto§7] §6Frog Kata деактивировано");
                    } else {
                        // Damage and knockback nearby entities
                        for (Entity entity : player.getNearbyEntities(3, 3, 3)) {
                            if (entity instanceof LivingEntity && entity != player) {
                                addDamageEntity(player, entity, 5);
                                Vector direction = player.getLocation().toVector().subtract(entity.getLocation().toVector());
                                if (direction.lengthSquared() > 0) {
                                    entity.setVelocity(direction.normalize().multiply(1.5)); // Knockback
                                } else {
                                    entity.setVelocity(new Vector(0, 0, 0)); // Stop if at the same location
                                }
                            }
                        }
                        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, 2)); // Усиление силы
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 1)); // Увеличение скорости
                        np.setInt("chakra", np.getInt("chakra") - 5); // Reduce chakra
                    }
                }, 0L, 10L);

                scheduler.put(player, taskId);
            } else {
                player.sendTitle("§4У вас недостаточно чакры", "", 5, 5, 5);
            }
        } else {
            removeControl(player);
            player.sendMessage("§7[§6Naruto§7] §6Frog Kata деактивировано");
        }
    }

    @Override
    public Item getItem() {
        return item;
    }
}