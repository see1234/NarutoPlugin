package minenaruto.narutoplugin.abilities.clan.inuzuki;
import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import java.util.*;
public class HowlingWolfTechnique extends InuzukaTechnique {
    public HowlingWolfTechnique() { super("Воющий Волк"); }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        for (Entity entity : player.getNearbyEntities(8, 3, 8)) {
            if (entity instanceof LivingEntity && !entity.equals(player)) {
                ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 2));
                ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 200, 1));
            }
        }
        
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_WOLF_HOWL, 1.5f, 0.8f);
        player.getWorld().spawnParticle(Particle.NOTE, player.getLocation(), 50);
    }
}