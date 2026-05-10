package minenaruto.narutoplugin.abilities.clan.inuzuki;
import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import minenaruto.narutoplugin.main.Main;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import java.util.*;
public class TwinFangTechnique extends InuzukaTechnique {
    public TwinFangTechnique() { super("Двойной Клык"); }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        Wolf wolf1 = (Wolf) player.getWorld().spawnEntity(player.getLocation(), EntityType.WOLF);
        Wolf wolf2 = (Wolf) player.getWorld().spawnEntity(player.getLocation(), EntityType.WOLF);
        
        wolf1.setOwner(player);
        wolf2.setOwner(player);
        wolf1.setAngry(true);
        wolf2.setAngry(true);
        
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_WOLF_AMBIENT, 1, 1);
        player.getWorld().spawnParticle(Particle.HEART, player.getLocation(), 10);
        
        Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
            wolf1.remove();
            wolf2.remove();
        }, 200L);
    }
}