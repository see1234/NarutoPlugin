package minenaruto.narutoplugin.abilities.clan.uchiha;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class FireballJutsu extends UchihaAbility {
    public FireballJutsu() {
        super("Огненный Шар");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.getWorld().spawnEntity(player.getLocation().add(player.getLocation().getDirection().multiply(2)), EntityType.SMALL_FIREBALL);
    }
}
