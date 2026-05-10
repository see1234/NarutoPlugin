package minenaruto.narutoplugin.abilities.clan.hagoromo;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class HagoromoSixPaths extends HagoromoAbility {
    public HagoromoSixPaths() {
        super("Режим Шести Путей");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 400, 2));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 400, 3));
    }
}
