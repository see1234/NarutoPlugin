package minenaruto.narutoplugin.abilities.Kekkei_Genkai.magnet;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

// Способность 2: Iron Sand Armor (Броня Железного Песка)
public class IronSandArmor extends MagnetAbility {
    public IronSandArmor() {
        super("Броня Железного Песка");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 1));
    }
}
