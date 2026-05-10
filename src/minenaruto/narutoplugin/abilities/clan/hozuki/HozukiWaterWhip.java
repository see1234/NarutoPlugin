package minenaruto.narutoplugin.abilities.clan.hozuki;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class HozukiWaterWhip extends HozukiAbility {
    public HozukiWaterWhip() {
        super("Водяной Кнут");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.getWorld().spawnParticle(Particle.WATER_SPLASH, player.getLocation(), 50);
    }
}
