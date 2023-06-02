package minenaruto.narutoplugin.swords;

import minenaruto.narutoplugin.iditems.Item;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class Shubiki_mech extends SwordMain implements Listener {
    private minenaruto.narutoplugin.iditems.Item item = new minenaruto.narutoplugin.iditems.Item(Material.DIAMOND_HOE, 119, "§7[§6Naruto§7] §bШибуки меч", List.of("§7Урон: §c12;§7Способность подрыва врага".split(";")));



    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {

        if(checkMethodOnDamage(event.getDamager())) {

         event.getEntity().getWorld().createExplosion(event.getEntity().getLocation(), 2.0F, false, false);
            event.setDamage(12);

        }
    }
    @Override
    public Item getItem() {
        return item;
    }
}
