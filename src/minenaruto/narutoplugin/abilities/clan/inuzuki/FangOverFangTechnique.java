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

public class FangOverFangTechnique extends InuzukaTechnique {
    public FangOverFangTechnique() { super("Клык над Клыком"); }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        Wolf wolf = (Wolf) player.getWorld().spawnEntity(player.getLocation(), EntityType.WOLF);
        wolf.setOwner(player);
        wolf.setAngry(true);
        wolf.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 2));
        
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_WOLF_GROWL, 1, 1);
        player.getWorld().spawnParticle(Particle.CRIT, player.getLocation(), 30);
        
        Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
            wolf.getWorld().spawnParticle(Particle.EXPLOSION_NORMAL, wolf.getLocation(), 10);
            wolf.remove();
        }, 100L);
    }
}