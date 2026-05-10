package minenaruto.narutoplugin.abilities.clan.aburame;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

// Способность 4: Bug Clone (Клон из Насекомых)
public class BugClone extends AburameAbility {
    public BugClone() {
        super("Клон из Насекомых");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.getWorld().spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);
    }
}
