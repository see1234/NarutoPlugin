package minenaruto.narutoplugin.abilities.clan.akimichi;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class AkimichiMeatTank extends AkimichiAbility {
    public AkimichiMeatTank() {
        super("Мясной Танк");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 400, 2));
        player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 400, 2));
    }
}
