package minenaruto.narutoplugin.abilities.clan.sarutobi;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class GreatFireballTechnique extends SarutobiTechnique {
    public GreatFireballTechnique() {
        super("Великий Огненный Шар");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        Location loc = player.getEyeLocation();
        Vector dir = loc.getDirection().normalize();

        for (int i = 0; i < 3; i++) {
            Fireball fireball = player.getWorld().spawn(loc, Fireball.class);
            fireball.setVelocity(dir.multiply(2));
            fireball.setYield(3.0f);
        }

        player.getWorld().playSound(loc, Sound.ENTITY_GHAST_SHOOT, 1, 0.8f);
    }
}
