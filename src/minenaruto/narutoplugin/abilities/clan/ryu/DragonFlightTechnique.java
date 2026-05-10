package minenaruto.narutoplugin.abilities.clan.ryu;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class DragonFlightTechnique extends RyuTechnique {
    public DragonFlightTechnique() {
        super("Полет Дракона");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 100, 1));
        player.setVelocity(player.getLocation().getDirection().multiply(1.5).setY(0.7));

        // Эффекты крыльев
        player.getWorld().spawnParticle(Particle.CLOUD, player.getLocation(), 30, 1, 1, 1, 0.1);
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_FLAP, 1, 0.8f);
    }
}
