package minenaruto.narutoplugin.abilities.Kekkei_Genkai.medical;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

// Способность 5: Regeneration (Регенерация)
public class Regeneration extends MedicalAbility {
    public Regeneration() {
        super("Регенерация");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 2)); // Дает регенерацию
    }
}
