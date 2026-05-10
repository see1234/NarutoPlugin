package minenaruto.narutoplugin.abilities.clan.akimichi;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class AkimichiExpansion extends AkimichiAbility {
    public AkimichiExpansion() {
        super("Расширение Тела");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 1));
    }
}
