package minenaruto.narutoplugin.abilities.clan.akimichi;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class AkimichiHumanBoulder extends AkimichiAbility {
    public AkimichiHumanBoulder() {
        super("Шароподобная Атака");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.setVelocity(player.getLocation().getDirection().multiply(2));
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_IRON_GOLEM_ATTACK, 1, 1);
    }
}
