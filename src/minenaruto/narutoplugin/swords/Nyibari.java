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

public class Nyibari extends SwordMain implements Listener {
    private minenaruto.narutoplugin.iditems.Item item = new minenaruto.narutoplugin.iditems.Item(Material.DIAMOND_HOE, 118, "§7[§6Naruto§7] §4Нуибари", List.of("§7Урон: §c14;§7Способность отравление врага".split(";")));



    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {

        if(checkMethodOnDamage(event.getDamager())) {

            ((LivingEntity)event.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.POISON, 60, 1));
            event.setDamage(14);

        }
    }
    @Override
    public Item getItem() {
        return item;
    }
}
