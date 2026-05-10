package minenaruto.narutoplugin.abilities.clan.iburi;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.entity.Player;

public class IburiSmokeDash extends IburiAbility {
    public IburiSmokeDash() {
        super("Дымовой Рывок");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.setVelocity(player.getLocation().getDirection().multiply(2));
    }
}
