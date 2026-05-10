package minenaruto.narutoplugin.abilities.clan.uzumaki;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

// Способность 1: Adamantine Sealing Chains (Адамантовые Цепи Печати)
public class AdamantineChains extends UzumakiAbility {
    public AdamantineChains() {
        super("Адамантовые Цепи");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        for (Entity entity : player.getNearbyEntities(5, 5, 5)) {
            if (entity instanceof LivingEntity) {
                ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 2));
                entity.getWorld().spawnParticle(Particle.END_ROD, entity.getLocation(), 30);
            }
        }
    }
}
