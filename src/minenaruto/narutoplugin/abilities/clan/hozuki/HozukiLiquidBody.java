package minenaruto.narutoplugin.abilities.clan.hozuki;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class HozukiLiquidBody extends HozukiAbility {
    public HozukiLiquidBody() {
        super("Жидкое Тело");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 2));
    }
}
