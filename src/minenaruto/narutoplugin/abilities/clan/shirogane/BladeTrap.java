package minenaruto.narutoplugin.abilities.clan.shirogane;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

// Способность 3: Blade Trap (Ловушка с Лезвиями)
public class BladeTrap extends ShiroganeAbility {
    public BladeTrap() {
        super("Ловушка с Лезвиями");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.getWorld().spawnParticle(Particle.CRIT, player.getLocation(), 30);
        for (Entity entity : player.getNearbyEntities(3, 3, 3)) {
            if (entity instanceof LivingEntity) {
                addDamageEntity(player, entity, 4);
            }
        }
    }
}
