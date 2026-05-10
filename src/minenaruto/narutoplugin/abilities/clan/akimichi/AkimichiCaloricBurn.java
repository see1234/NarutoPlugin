package minenaruto.narutoplugin.abilities.clan.akimichi;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class AkimichiCaloricBurn extends AkimichiAbility {
    public AkimichiCaloricBurn() {
        super("Калорийное Сжигание");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 300, 2));
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 300, 1));
    }
}
