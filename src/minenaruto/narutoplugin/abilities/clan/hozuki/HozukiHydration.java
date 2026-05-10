package minenaruto.narutoplugin.abilities.clan.hozuki;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class HozukiHydration extends HozukiAbility {
    public HozukiHydration() {
        super("Гидратация");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 400, 1));
    }
}
