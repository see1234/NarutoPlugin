package minenaruto.narutoplugin.abilities.clan.ryu;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class DragonClawTechnique extends RyuTechnique {
    public DragonClawTechnique() {
        super("Когти Дракона");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        for (Entity entity : player.getNearbyEntities(3, 2, 3)) {
            if (entity instanceof LivingEntity && !entity.equals(player)) {
                ((LivingEntity) entity).damage(8);
                entity.setVelocity(entity.getLocation().toVector()
                        .subtract(player.getLocation().toVector()).normalize().multiply(1.2));

                // Эффекты когтей
                entity.getWorld().spawnParticle(Particle.CRIT_MAGIC, entity.getLocation(), 20);
            }
        }
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1, 0.5f);
    }
}
