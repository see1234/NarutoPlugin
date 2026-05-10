package minenaruto.narutoplugin.abilities.sharingan;

import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.abilities.AbilityListener;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import minenaruto.narutoplugin.main.Main;
import minenaruto.narutoplugin.models.ModelsMain;
import org.bukkit.*;
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

public class SharinganSusanoFull extends AbilitiesMain implements Listener {
    private Item item = new Item(Material.DIAMOND_HOE, 89, "§7[§6Naruto§7] §4Полное Сусаноо", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));
    public ArrayList<Player> enabledControl = new ArrayList<Player>();
    public WeakHashMap<Player, Integer> scheduler = new WeakHashMap<Player, Integer>();

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

    public SharinganSusanoFull() {
        super();
        Bukkit.getPluginManager().registerEvents((Listener) this, (Plugin) Main.getInstance());
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName())) {
            if (enabledController.containsKey(player) && !enabledController.containsValue(this.getClass().getSimpleName())) {
                player.sendMessage("§7[§6Naruto§7] §4Полное Сусаноо уже активировано");
            } else {
                if (AbilityListener.checkChakraItem(player, getItem().getName(), 100, 0, 0, 0, 0)) {
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
                ArmorStand armorstand = (ArmorStand) player.getWorld().spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);
                armorstand.setVisible(false);
                armorstand.setGravity(false);
                armorstand.setBasePlate(false);
                armorstand.setHelmet(ModelsMain.models.get("full_susano").getItemStack());
                armorstand.setCustomName("§4Полное Сусаноо");
                armorstand.setCustomNameVisible(true);
                player.setPassenger(armorstand);
                arm.put(player, armorstand);
                player.sendMessage("§7[§6Naruto§7] §4Полное Сусаноо активировано");

                int taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), () -> {
                    if (np.getInt("chakra") <= 5) {
                        np.setInt("chakra", 0);
                        removeControl(player);
                        if (arm.containsKey(player)) {
                            if (!arm.get(player).isDead()) {
                                arm.get(player).remove();
                            }
                        }
                        player.sendMessage("§7[§6Naruto§7] §4Полное Сусаноо деактивировано");
                    } else {
                        // Apply effects
                        player.removePotionEffect(PotionEffectType.JUMP);
                        player.removePotionEffect(PotionEffectType.SPEED);
                        player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
                        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 63, 3));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 63, 3));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 63, 5));

                        // Damage nearby entities
                        for (Entity entity : player.getNearbyEntities(5, 5, 5)) {
                            if (entity instanceof LivingEntity && entity != player) {
                                addDamageEntity(player, entity, 10);
                                Vector direction = player.getLocation().toVector().subtract(entity.getLocation().toVector());
                                if (direction.lengthSquared() > 0) {
                                    entity.setVelocity(direction.normalize().multiply(2));
                                }
                            }
                        }

                        np.setInt("chakra", np.getInt("chakra") - 5);
                    }
                }, 1L, 40L);

                scheduler.put(player, taskId);
            } else {
                player.sendTitle("§4У вас недостаточно чакры", "", 5, 5, 5);
            }
        } else {
            removeControl(player);
            player.sendMessage("§7[§6Naruto§7] §4Полное Сусаноо деактивировано");
            if (arm.containsKey(player)) {
                if (!arm.get(player).isDead()) {
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