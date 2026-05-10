package minenaruto.narutoplugin.abilities.clan.hagoromo;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.entity.Player;

public class HagoromoChibakuTensei extends HagoromoAbility {
    public HagoromoChibakuTensei() {
        super("Чибаку Тенсей");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.getWorld().createExplosion(player.getLocation(), 3.0F, false, false);
    }
}
