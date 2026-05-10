package minenaruto.narutoplugin.abilities.Kekkei_Genkai.magnet;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

// Способность 5: Magnetic Field (Магнитное Поле)
public class MagneticField extends MagnetAbility {
    public MagneticField() {
        super("Магнитное Поле");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 2));
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ZOMBIE_ATTACK_IRON_DOOR, 1, 1);
    }
}
