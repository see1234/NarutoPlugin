package minenaruto.narutoplugin.abilities.clan.wasabi;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

// Способность 3: Wasabi Reflex (Рефлексы Васаби)
public class WasabiReflex extends WasabiAbility {
    public WasabiReflex() {
        super("Рефлексы Васаби");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 1));
        player.getWorld().spawnParticle(Particle.SWEEP_ATTACK, player.getLocation(), 10);
    }
}
