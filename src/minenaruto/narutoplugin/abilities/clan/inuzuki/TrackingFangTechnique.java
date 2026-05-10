package minenaruto.narutoplugin.abilities.clan.inuzuki;
import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import java.util.*;
public class TrackingFangTechnique extends InuzukaTechnique {
    public TrackingFangTechnique() { super("Следующий Клык"); }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        LivingEntity target = null;
        for (Entity entity : player.getNearbyEntities(10, 3, 10)) {
            if (entity instanceof LivingEntity && !entity.equals(player)) {
                target = (LivingEntity) entity;
                break;
            }
        }
        
        if (target != null) {
            player.setVelocity(target.getLocation().toVector()
                .subtract(player.getLocation().toVector())
                .normalize()
                .multiply(1.5));
            
            player.getWorld().playSound(player.getLocation(), Sound.ENTITY_WOLF_SHAKE, 1, 1);
            player.getWorld().spawnParticle(Particle.CRIT_MAGIC, target.getLocation(), 20);
        }
    }
}