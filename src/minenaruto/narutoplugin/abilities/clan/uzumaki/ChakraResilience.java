package minenaruto.narutoplugin.abilities.clan.uzumaki;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

// Способность 2: Chakra Resilience (Чакроустойчивость)
public class ChakraResilience extends UzumakiAbility {
    public ChakraResilience() {
        super("Чакроустойчивость");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 2));
        player.getWorld().spawnParticle(Particle.HEART, player.getLocation(), 50);
    }
}
