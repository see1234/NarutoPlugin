package minenaruto.narutoplugin.abilities.Kekkei_Genkai.magnet;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

// Способность 1: Magnetic Pull (Магнитное Притяжение)
public class MagneticPull extends MagnetAbility {
    public MagneticPull() {
        super("Магнитное Притяжение");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        for (Entity entity : player.getNearbyEntities(7, 7, 7)) {
            if (entity instanceof LivingEntity) {
                Vector direction = player.getLocation().toVector().subtract(entity.getLocation().toVector()).normalize();
                entity.setVelocity(direction.multiply(1.2));
            }
        }
    }
}
