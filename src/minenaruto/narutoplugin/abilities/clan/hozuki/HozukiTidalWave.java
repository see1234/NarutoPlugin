package minenaruto.narutoplugin.abilities.clan.hozuki;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class HozukiTidalWave extends HozukiAbility {
    public HozukiTidalWave() {
        super("Приливная Волна");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.getWorld().spawnParticle(Particle.WATER_DROP, player.getLocation(), 100);
    }
}
