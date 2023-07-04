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

public class Weer extends SwordMain implements Listener {
    private minenaruto.narutoplugin.iditems.Item item = new minenaruto.narutoplugin.iditems.Item(Material.DIAMOND_HOE, 124, "§7[§6Naruto§7] §bВеер", List.of("§7Урон: §c11;§7Способность откидывать врага".split(";")));



    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {

        if(checkMethodOnDamage(event.getDamager())) {

            event.getEntity().setVelocity(((Player)event.getDamager()).getEyeLocation().getDirection().multiply(2));
            event.setDamage(11);

        }
    }
    @Override
    public Item getItem() {
        return item;
    }
}
