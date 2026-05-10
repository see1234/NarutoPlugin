package minenaruto.narutoplugin.abilities.clan.aburame;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

// Способность 5: Insect Camouflage (Маскировка Насекомых)
public class InsectCamouflage extends AburameAbility {
    public InsectCamouflage() {
        super("Маскировка Насекомых");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 200, 1));
    }
}
