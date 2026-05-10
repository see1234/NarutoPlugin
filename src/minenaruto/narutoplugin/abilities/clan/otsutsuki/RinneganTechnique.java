package minenaruto.narutoplugin.abilities.clan.otsutsuki;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class RinneganTechnique extends OtsutsukiTechnique {
    public RinneganTechnique() {
        super("Риннеган");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 600, 0));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 200, 1));

        // Эффект глаз Риннегана
        player.getWorld().spawnParticle(Particle.PORTAL, player.getEyeLocation(), 30, 0.2, 0.2, 0.2, 0.1);
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_FLAP, 0.7f, 1.5f);
    }
}
