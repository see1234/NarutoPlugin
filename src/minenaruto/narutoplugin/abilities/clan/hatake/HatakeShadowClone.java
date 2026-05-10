package minenaruto.narutoplugin.abilities.clan.hatake;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class HatakeShadowClone extends HatakeAbility {
    public HatakeShadowClone() {
        super("Теневой Клон");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.getWorld().spawnEntity(player.getLocation(), EntityType.PLAYER);
    }
}
