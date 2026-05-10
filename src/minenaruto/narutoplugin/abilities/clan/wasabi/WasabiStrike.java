package minenaruto.narutoplugin.abilities.clan.wasabi;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

// Способность 1: Wasabi Strike (Удар Васаби)
public class WasabiStrike extends WasabiAbility {
    public WasabiStrike() {
        super("Удар Васаби");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        for (Entity entity : player.getNearbyEntities(3, 3, 3)) {
            if (entity instanceof LivingEntity) {
                addDamageEntity(player, entity, 4);
                entity.getWorld().spawnParticle(Particle.CRIT_MAGIC, entity.getLocation(), 20);
            }
        }
    }
}
