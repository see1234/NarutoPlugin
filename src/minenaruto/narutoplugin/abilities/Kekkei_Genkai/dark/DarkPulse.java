package minenaruto.narutoplugin.abilities.Kekkei_Genkai.dark;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

// Способность 2: Dark Pulse (Взрыв Тьмы)
public class DarkPulse extends DarkAbility {
    public DarkPulse() {
        super("Взрыв Тьмы");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        Location loc = player.getLocation();
        loc.getWorld().spawnParticle(Particle.SMOKE_LARGE, loc, 100, 2, 2, 2, 0.1);
        loc.getWorld().playSound(loc, Sound.ENTITY_WITHER_SHOOT, 1, 0.5f);
        for (Entity entity : loc.getWorld().getNearbyEntities(loc, 4, 4, 4)) {
            if (entity instanceof LivingEntity && entity != player) {
                ((LivingEntity) entity).damage(6);
            }
        }
    }
}
