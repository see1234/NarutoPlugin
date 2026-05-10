package minenaruto.narutoplugin.abilities.clan.hatake;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class HatakeChidori extends HatakeAbility {
    public HatakeChidori() {
        super("Чидори");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.getWorld().spawnParticle(Particle.CRIT_MAGIC, player.getLocation(), 100, 1, 1, 1, 0.5);
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 2));
    }
}
