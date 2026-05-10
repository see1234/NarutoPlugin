package minenaruto.narutoplugin.abilities.Kekkei_Genkai.medical;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

// Способность 3: Poison Cure (Лечение Яда)
public class PoisonCure extends MedicalAbility {
    public PoisonCure() {
        super("Лечение Яда");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.removePotionEffect(PotionEffectType.POISON); // Убирает эффект яда
    }
}
