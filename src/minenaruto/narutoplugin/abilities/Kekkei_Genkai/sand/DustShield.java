package minenaruto.narutoplugin.abilities.Kekkei_Genkai.sand;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

// Способность 2: Dust Shield (Пылевой Щит)
public class DustShield extends DustAbility {
    public DustShield() {
        super("Пылевой Щит");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 1));
    }
}
