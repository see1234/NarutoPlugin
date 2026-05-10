package minenaruto.narutoplugin.abilities.clan.hatake;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class HatakeSharpenInstinct extends HatakeAbility {
    public HatakeSharpenInstinct() {
        super("Обостренный Инстинкт");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 400, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 400, 2));
    }
}
