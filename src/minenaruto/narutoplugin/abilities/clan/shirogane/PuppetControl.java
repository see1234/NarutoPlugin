package minenaruto.narutoplugin.abilities.clan.shirogane;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

// Способность 1: Puppet Control (Контроль Кукол)
public class PuppetControl extends ShiroganeAbility {
    public PuppetControl() {
        super("Контроль Кукол");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.getWorld().spawnParticle(Particle.REDSTONE, player.getLocation(), 40);
        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 300, 1));
    }
}
