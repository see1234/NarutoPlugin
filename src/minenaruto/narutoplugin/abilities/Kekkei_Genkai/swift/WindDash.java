package minenaruto.narutoplugin.abilities.Kekkei_Genkai.swift;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

// Способность 5: Wind Dash (Ветровой Рывок)
public class WindDash extends SwiftAbility {
    public WindDash() {
        super("Ветровой Рывок");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        Vector direction = player.getLocation().getDirection().multiply(2);
        player.setVelocity(direction);
    }
}
