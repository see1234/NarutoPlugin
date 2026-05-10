package minenaruto.narutoplugin.abilities.clan.iburi;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class IburiSmokeScreen extends IburiAbility {
    public IburiSmokeScreen() {
        super("Дымовая Завеса");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.getWorld().spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, player.getLocation(), 100);
    }
}
