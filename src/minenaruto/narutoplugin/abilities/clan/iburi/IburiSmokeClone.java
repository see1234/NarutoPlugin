package minenaruto.narutoplugin.abilities.clan.iburi;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class IburiSmokeClone extends IburiAbility {
    public IburiSmokeClone() {
        super("Дымовой Клон");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.getWorld().spawnEntity(player.getLocation(), EntityType.ZOMBIE);
    }
}
