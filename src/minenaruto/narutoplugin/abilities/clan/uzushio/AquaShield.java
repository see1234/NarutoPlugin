package minenaruto.narutoplugin.abilities.clan.uzushio;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

// Способность 4: Aqua Shield (Аква Щит)
public class AquaShield extends UzushioAbility {
    public AquaShield() {
        super("Аква Щит");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 250, 2));
        player.getWorld().spawnParticle(Particle.WATER_DROP, player.getLocation(), 20);
    }
}
