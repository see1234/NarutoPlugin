package minenaruto.narutoplugin.abilities.clan.shirogane;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

// Способность 2: Poison Gas (Ядовитый Газ)
public class PoisonGas extends ShiroganeAbility {
    public PoisonGas() {
        super("Ядовитый Газ");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.getWorld().spawnParticle(Particle.SPELL_MOB, player.getLocation(), 50);
        for (Entity entity : player.getNearbyEntities(4, 4, 4)) {
            if (entity instanceof LivingEntity) {
                ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.POISON, 200, 1));
            }
        }
    }
}
