package minenaruto.narutoplugin.abilities.Kekkei_Genkai.explosion;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

// Способность 4: Blast Wave (Взрывная Волна)
public class BlastWave extends ExplosionAbility {
    public BlastWave() {
        super("Взрывная Волна");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        Location loc = player.getLocation();
        loc.getWorld().playSound(loc, Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
        for (Entity entity : loc.getWorld().getNearbyEntities(loc, 5, 5, 5)) {
            if (entity instanceof LivingEntity && entity != player) {
                ((LivingEntity) entity).setVelocity(entity.getVelocity().add(new Vector(0, 1, 0)));
            }
        }
    }
}
