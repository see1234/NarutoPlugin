package minenaruto.narutoplugin.swords;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import minenaruto.narutoplugin.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class HidanScythe extends SwordMain implements Listener {
    private minenaruto.narutoplugin.iditems.Item item = new minenaruto.narutoplugin.iditems.Item(Material.DIAMOND_HOE, 113, "§7[§6Naruto§7] §4Коса Хидана", List.of("§7Урон: §c12;§7Способность вампиризма".split(";")));


    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {

         if(checkMethodOnDamage(event.getDamager())) {
             Player player = (Player) event.getDamager();
             if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase(getItem().getItemStack().getItemMeta().getDisplayName())) {
                 NarutoPlayer narutoPlayer = NarutoPlayer.getNarutoPlayer(player.getName());
                 narutoPlayer.setDouble("health", narutoPlayer.getDouble("health") + 2);
                 double healthmax = narutoPlayer.getDouble("medical") + 20;
                 double health = narutoPlayer.getDouble("health") > healthmax ? healthmax : narutoPlayer.getDouble("health");
                 ((Player) player).setHealth((health / healthmax) * 20);

                 event.setDamage(12);
             }
         }
    }

    @Override
    public Item getItem() {
        return item;
    }
}
