package minenaruto.narutoplugin.abilities.clan.yotsuki;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

// Способность 2: Speed Boost (Скоростной Рывок)
public class SpeedBoost extends YotsukiAbility {
    public SpeedBoost() {
        super("Скоростной Рывок");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 300, 3));
        player.getWorld().spawnParticle(Particle.CLOUD, player.getLocation(), 30);
    }
}
