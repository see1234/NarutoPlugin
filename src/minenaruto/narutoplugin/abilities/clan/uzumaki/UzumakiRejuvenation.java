package minenaruto.narutoplugin.abilities.clan.uzumaki;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

// Способность 5: Uzumaki Rejuvenation (Регенерация Узумак)
public class UzumakiRejuvenation extends UzumakiAbility {
    public UzumakiRejuvenation() {
        super("Регенерация Узумак");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 400, 1));
        player.getWorld().spawnParticle(Particle.TOTEM, player.getLocation(), 30);
    }
}
