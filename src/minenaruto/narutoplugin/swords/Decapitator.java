package minenaruto.narutoplugin.swords;

import minenaruto.narutoplugin.iditems.Item;
import minenaruto.narutoplugin.main.Main;
import org.bukkit.Bukkit;
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

public class Decapitator extends SwordMain implements Listener {
    private minenaruto.narutoplugin.iditems.Item item = new minenaruto.narutoplugin.iditems.Item(293, 108, "§7[§6Naruto§7] §bОбезглавливающий меч", List.of("§7Урон: §c10;§7Способность восстанавливать здоровье после убийства игрока".split(";")));

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {

        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            if(player.getInventory().getItemInMainHand() == null) {
                return;
            }
            if(player.getInventory().getItemInMainHand().getType() == null) {
                return;
            }
            if(player.getInventory().getItemInMainHand().getType().getId() != 293) {
                return;
            }
            if(!player.getInventory().getItemInMainHand().hasItemMeta()) {
                return;
            }
            if(!player.getInventory().getItemInMainHand().getItemMeta().hasDisplayName()) {
                return;
            }
            if(player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase(getItem().getItemStack().getItemMeta().getDisplayName())) {

              event.setDamage(10);
            }
        }
    }
    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {
        LivingEntity livingEntity = e.getEntity();
        if (e.getEntity().getKiller() instanceof Player) {
            Player p = e.getEntity().getKiller();
            if (onCheckItem(p.getInventory().getItemInMainHand()) &&
                    p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(getItem().getItemStack().getItemMeta().getDisplayName()))
                            p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 1));
        }
    }
    @Override
    public Item getItem() {
        return item;
    }
}
