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

public class Posox_mydresa extends SwordMain implements Listener {
    private minenaruto.narutoplugin.iditems.Item item = new minenaruto.narutoplugin.iditems.Item(Material.DIAMOND_HOE, 121, "§7[§6Naruto§7] §6Посох мудреца", List.of("§7Урон: §c30;§7Способность давать полет врагу и отравление".split(";")));



    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {

        if(checkMethodOnDamage(event.getDamager())) {

            ((LivingEntity)event.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 120, 1));
            ((LivingEntity)event.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.POISON, 120, 2));

            event.setDamage(30);

        }
    }
    @Override
    public Item getItem() {
        return item;
    }
}
