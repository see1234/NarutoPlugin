package minenaruto.narutoplugin.abilities.clan.uzushio;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

// Способность 3: Uzushio Regeneration (Регенерация Узушио)
public class UzushioRegeneration extends UzushioAbility {
    public UzushioRegeneration() {
        super("Регенерация Узушио");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 300, 1));
        player.getWorld().spawnParticle(Particle.TOTEM, player.getLocation(), 30);
    }
}
