package minenaruto.narutoplugin.abilities.clan.uchiha;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

// Способности клана Uchiha
public class Sharingan extends UchihaAbility {
    public Sharingan() {
        super("Шаринган");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 600, 0));
        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 600, 1));
    }
}
