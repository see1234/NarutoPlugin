package minenaruto.narutoplugin.abilities.clan.hagoromo;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.entity.Player;

public class HagoromoHeavenlyHand extends HagoromoAbility {
    public HagoromoHeavenlyHand() {
        super("Небесная Длань");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.teleport(player.getLocation().add(player.getLocation().getDirection().multiply(10)));
    }
}
