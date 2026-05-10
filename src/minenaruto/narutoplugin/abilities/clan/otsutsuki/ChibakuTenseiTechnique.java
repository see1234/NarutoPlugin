package minenaruto.narutoplugin.abilities.clan.otsutsuki;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class ChibakuTenseiTechnique extends OtsutsukiTechnique {
    public ChibakuTenseiTechnique() {
        super("Чибаку Тэнсэй");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        Location center = player.getLocation().add(0, 5, 0);
        player.getWorld().spawnParticle(Particle.DRAGON_BREATH, center, 100, 2, 2, 2, 0.3);

        // Притягивание мобов к центру
        for (Entity entity : player.getWorld().getNearbyEntities(center, 8, 8, 8)) {
            if (entity instanceof LivingEntity && !entity.equals(player)) {
                Vector direction = center.toVector().subtract(entity.getLocation().toVector()).normalize();
                entity.setVelocity(direction.multiply(0.7));
            }
        }
        player.getWorld().playSound(center, Sound.ENTITY_ENDER_DRAGON_GROWL, 1, 0.8f);
    }
}
