package minenaruto.narutoplugin.abilities.clan.uzushio;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

// Способность 2: Whirlpool Prison (Тюрьма Водоворота)
public class WhirlpoolPrison extends UzushioAbility {
    public WhirlpoolPrison() {
        super("Тюрьма Водоворота");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.getWorld().spawnParticle(Particle.WATER_BUBBLE, player.getLocation(), 40);
        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 1));
    }
}
