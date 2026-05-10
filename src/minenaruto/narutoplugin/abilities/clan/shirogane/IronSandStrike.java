package minenaruto.narutoplugin.abilities.clan.shirogane;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

// Способность 4: Iron Sand Strike (Удар Железного Песка)
public class IronSandStrike extends ShiroganeAbility {
    public IronSandStrike() {
        super("Удар Железного Песка");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.getWorld().spawnParticle(Particle.FALLING_DUST, player.getLocation(), 20);
        for (Entity entity : player.getNearbyEntities(5, 5, 5)) {
            if (entity instanceof LivingEntity) {
                ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 1));
            }
        }
    }
}
