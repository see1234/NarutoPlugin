package minenaruto.narutoplugin.abilities.Kekkei_Genkai.lava;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

// Способность 5: Lava Armor (Лавовая Броня)
public class LavaArmor extends LavaAbility {
    public LavaArmor() {
        super("Лавовая Броня");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 200, 1));
    }
}
