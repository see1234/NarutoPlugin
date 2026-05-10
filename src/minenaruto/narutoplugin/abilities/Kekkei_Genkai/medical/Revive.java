package minenaruto.narutoplugin.abilities.Kekkei_Genkai.medical;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.entity.Player;

// Способность 2: Revive (Воскрешение)
public class Revive extends MedicalAbility {
    public Revive() {
        super("Воскрешение");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (player.getHealth() <= 0) { // Воскрешение при смерти игрока
            player.setHealth(10); // Восстановление здоровья
            player.sendMessage("§aВы были воскрешены!");
        }
    }
}
