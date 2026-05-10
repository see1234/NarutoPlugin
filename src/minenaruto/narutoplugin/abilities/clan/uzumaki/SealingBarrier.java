package minenaruto.narutoplugin.abilities.clan.uzumaki;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

// Способность 3: Sealing Barrier (Барьер Запечатывания)
public class SealingBarrier extends UzumakiAbility {
    public SealingBarrier() {
        super("Барьер Запечатывания");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 300, 1));
        player.getWorld().spawnParticle(Particle.SNOWBALL, player.getLocation(), 10);
    }
}
