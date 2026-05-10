package minenaruto.narutoplugin.abilities.Kekkei_Genkai.swift;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Location;
import org.bukkit.entity.Player;

// Способность 2: Blink (Рывок)
public class Blink extends SwiftAbility {
    public Blink() {
        super("Рывок");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        Location loc = player.getTargetBlock(null, 10).getLocation();
        player.teleport(loc);
    }
}
