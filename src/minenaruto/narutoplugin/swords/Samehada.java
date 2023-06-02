package minenaruto.narutoplugin.swords;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
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

public class Samehada extends SwordMain implements Listener {
    private minenaruto.narutoplugin.iditems.Item item = new minenaruto.narutoplugin.iditems.Item(Material.DIAMOND_HOE, 122, "§7[§6Naruto§7] §bСамехада", List.of("§7Урон: §c10;§7Способность высасывать чакру у врага".split(";")));



    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {

        if(checkMethodOnDamage(event.getDamager())) {
           if(event.getEntity() instanceof Player) {
               NarutoPlayer narutoPlayer = NarutoPlayer.getNarutoPlayer(event.getEntity().getName());

               if (narutoPlayer.getInt("chakra") <= 15) {
                   narutoPlayer.setInt("chakra", 0);
               } else {
                   narutoPlayer.setInt("chakra", narutoPlayer.getInt("chakra") - 15);
               }
               NarutoPlayer pl = NarutoPlayer.getNarutoPlayer(event.getDamager().getName());

               if (pl.getInt("chakra") <= ((pl.getInt("ninjustu") * 10) + 100)) {
                   pl.setInt("chakra", pl.getInt("chakra") + 15);
               } else {
                  pl.setInt("chakra", ((pl.getInt("ninjustu") * 10) + 100));
               }
           }
            event.setDamage(10);

        }
    }
    @Override
    public Item getItem() {
        return item;
    }
}
