package minenaruto.narutoplugin.abilities.clan.hagoromo;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class HagoromoTruthSeeker extends HagoromoAbility {
    public HagoromoTruthSeeker() {
        super("Шары Истины");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.getWorld().spawnParticle(Particle.SPELL_WITCH, player.getLocation(), 50, 1, 1, 1, 0.1);
        player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 300, 2));
    }
}
