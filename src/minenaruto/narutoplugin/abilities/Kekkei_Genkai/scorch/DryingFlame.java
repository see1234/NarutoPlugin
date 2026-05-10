package minenaruto.narutoplugin.abilities.Kekkei_Genkai.scorch;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

// Способность 2: Drying Flame (Высушивающее Пламя)
public class DryingFlame extends ScorchAbility {
    public DryingFlame() {
        super("Высушивающее Пламя");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.getWorld().playSound(player.getLocation(), Sound.ITEM_FLINTANDSTEEL_USE, 1, 1);
        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, 1));
    }
}
