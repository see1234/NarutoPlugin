package minenaruto.narutoplugin.abilities.clan.ryu;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class DragonFlameTechnique extends RyuTechnique {
    public DragonFlameTechnique() {
        super("Пламя Дракона");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        Location loc = player.getEyeLocation();
        Vector direction = loc.getDirection().normalize();

        for (int i = 1; i <= 10; i++) {
            Location flameLoc = loc.clone().add(direction.clone().multiply(i));
            player.getWorld().spawnParticle(Particle.DRAGON_BREATH, flameLoc, 10);
            player.getWorld().spawnParticle(Particle.FLAME, flameLoc, 5);

            // Поджигаем мобов
            for (Entity entity : player.getWorld().getNearbyEntities(flameLoc, 1.5, 1.5, 1.5)) {
                if (entity instanceof LivingEntity && !entity.equals(player)) {
                    entity.setFireTicks(100);
                }
            }
        }
        player.getWorld().playSound(loc, Sound.ENTITY_ENDER_DRAGON_SHOOT, 1, 1.5f);
    }
}
