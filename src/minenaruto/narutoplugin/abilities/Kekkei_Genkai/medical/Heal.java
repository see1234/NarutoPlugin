package minenaruto.narutoplugin.abilities.Kekkei_Genkai.medical;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.entity.Player;

// Способность 1: Heal (Исцеление)
public class Heal extends MedicalAbility {
    public Heal() {
        super("Исцеление");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.setHealth(player.getHealth() + 5); // Восстановление здоровья
    }
}
