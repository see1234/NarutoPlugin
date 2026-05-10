package minenaruto.narutoplugin.abilities.Kekkei_Genkai.scorch;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.entity.Player;

// Способность 5: Inferno Burst (Адский Взрыв)
public class InfernoBurst extends ScorchAbility {
    public InfernoBurst() {
        super("Адский Взрыв");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.getWorld().createExplosion(player.getLocation(), 3.0F, true, false);
    }
}
