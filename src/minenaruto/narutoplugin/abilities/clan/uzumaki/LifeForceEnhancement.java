package minenaruto.narutoplugin.abilities.clan.uzumaki;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

// Способность 4: Life Force Enhancement (Усиление Жизненной Энергии)
public class LifeForceEnhancement extends UzumakiAbility {
    public LifeForceEnhancement() {
        super("Усиление Жизненной Энергии");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 300, 2));
        player.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, player.getLocation(), 30);
    }
}
