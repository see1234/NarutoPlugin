package minenaruto.narutoplugin.abilities.sharingan;

import java.io.File;
import java.util.*;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.abilities.AbilityListener;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import minenaruto.narutoplugin.main.Main;

public class SharinganObito extends AbilitiesMain implements Listener {
    public static HashSet<Player> inKamui = new HashSet<>();
    private Item item = new Item(293, 80, "§7[§6Naruto§7] §4Шаринган Обито (Камуи)", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));
    public SharinganObito() {
        Bukkit.getPluginManager().registerEvents((Listener)this, (Plugin)Main.getInstance());
    }
    public static HashMap<UUID, ArrayList<Bat>> bats = new HashMap<UUID, ArrayList<Bat>>();
    @Override
    public void RightClick(Player player, NarutoPlayer np) {

        if(AbilityListener.checkChakraItem(player, getItem().getName(), 80, 50, 0, 10, 0)) {
            boolean isInKamui = inKamui.contains(player);
            if (!isInKamui) {
                inKamui.add(player);
                player.sendTitle("§cВы активировали 'Камуи'", "§7Вы получили бессмертие на 10 сек", 15, 15, 15);
                Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.getInstance(), () -> {
                    inKamui.remove(player);
                    player.sendTitle("§cДействие 'Камуи' закончилось", "", 15, 15, 15);
                }, 200L);
            } else {
                player.sendTitle("§cВы уже активировали 'Камуи'", "", 15, 15, 15);
            }




        }

    }

    @Override
    public void RightPlusShift(Player player, NarutoPlayer np) {
        // TODO Auto-generated method stub

    }
    @Override
    public Item getItem() {

        return item;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void entityDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            if (inKamui.contains(e.getEntity())) {
                e.setCancelled(true);
        }
        }
    }
}
