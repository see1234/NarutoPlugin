package minenaruto.narutoplugin.abilities.clan.hozuki;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;

public class HozukiWaterGun extends HozukiAbility {
    public HozukiWaterGun() {
        super("Водяное Оружие");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.launchProjectile(Snowball.class);
    }
}
