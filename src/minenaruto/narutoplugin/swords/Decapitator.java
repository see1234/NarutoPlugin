package minenaruto.narutoplugin.swords;

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

public class Decapitator extends SwordMain implements Listener {
    private minenaruto.narutoplugin.iditems.Item item = new minenaruto.narutoplugin.iditems.Item(Material.DIAMOND_HOE, 108, "§7[§6Naruto§7] §bКубикирибочо", List.of("§7Урон: §c10;§7Способность восстанавливать здоровье после убийства игрока".split(";")));

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {

        if(checkMethodOnDamage(event.getDamager())) {

              event.setDamage(10);

        }
    }
    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {

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
