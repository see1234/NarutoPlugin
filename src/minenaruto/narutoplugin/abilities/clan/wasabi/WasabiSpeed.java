package minenaruto.narutoplugin.abilities.clan.wasabi;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

// Способность 2: Wasabi Speed (Скорость Васаби)
public class WasabiSpeed extends WasabiAbility {
    public WasabiSpeed() {
        super("Скорость Васаби");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 2));
        player.getWorld().spawnParticle(Particle.CLOUD, player.getLocation(), 30);
    }
}
