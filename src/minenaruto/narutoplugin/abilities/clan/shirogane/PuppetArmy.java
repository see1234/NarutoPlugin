package minenaruto.narutoplugin.abilities.clan.shirogane;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

// Способность 5: Puppet Army (Армия Кукол)
public class PuppetArmy extends ShiroganeAbility {
    public PuppetArmy() {
        super("Армия Кукол");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, player.getLocation(), 10);
        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 300, 2));
    }
}
