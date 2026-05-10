package minenaruto.narutoplugin.abilities.Kekkei_Genkai.magnet;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

// Способность 3: Magnetic Repel (Магнитное Отталкивание)
public class MagneticRepel extends MagnetAbility {
    public MagneticRepel() {
        super("Магнитное Отталкивание");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        for (Entity entity : player.getNearbyEntities(5, 5, 5)) {
            if (entity instanceof LivingEntity) {
                Vector direction = entity.getLocation().toVector().subtract(player.getLocation().toVector()).normalize();
                entity.setVelocity(direction.multiply(1.5));
            }
        }
    }
}
