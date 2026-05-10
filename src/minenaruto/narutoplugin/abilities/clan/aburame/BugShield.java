package minenaruto.narutoplugin.abilities.clan.aburame;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

// Способность 2: Bug Shield (Щит из Насекомых)
public class BugShield extends AburameAbility {
    public BugShield() {
        super("Щит из Насекомых");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 200, 2));
        player.getWorld().spawnParticle(Particle.CRIT_MAGIC, player.getLocation(), 50);
    }
}
