package minenaruto.narutoplugin.abilities.clan.akimichi;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.entity.Player;

public class AkimichiSuperPunch extends AkimichiAbility {
    public AkimichiSuperPunch() {
        super("Супер Удар");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.getWorld().createExplosion(player.getLocation(), 1.5F, false, false);
    }
}
