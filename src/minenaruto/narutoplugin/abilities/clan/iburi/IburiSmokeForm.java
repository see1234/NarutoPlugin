package minenaruto.narutoplugin.abilities.clan.iburi;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class IburiSmokeForm extends IburiAbility {
    public IburiSmokeForm() {
        super("Дымовая Форма");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 400, 1));
    }
}
