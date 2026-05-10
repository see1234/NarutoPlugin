package minenaruto.narutoplugin.abilities.clan.hatake;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.entity.Player;

public class HatakeLightningBlade extends HatakeAbility {
    public HatakeLightningBlade() {
        super("Лезвие Молнии");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.getWorld().strikeLightning(player.getTargetBlock(null, 10).getLocation());
    }
}
