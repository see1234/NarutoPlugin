package minenaruto.narutoplugin.abilities.clan.uchiha;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.entity.Player;

public class Amaterasu extends UchihaAbility {
    public Amaterasu() {
        super("Аматерасу");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.getWorld().strikeLightningEffect(player.getTargetBlock(null, 20).getLocation());
    }
}
