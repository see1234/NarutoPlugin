package minenaruto.narutoplugin.abilities.clan.otsutsuki;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class DivineTreeTechnique extends OtsutsukiTechnique {
    public DivineTreeTechnique() {
        super("Божественное Дерево");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        Location center = player.getLocation();

        // Создаем "дерево" из частиц
        for (int y = 0; y < 6; y++) {
            Location loc = center.clone().add(0, y, 0);
            player.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, loc, 20, 0.5, 0, 0.5, 0);

            if (y > 2) {
                for (int r = 1; r <= 3; r++) {
                    for (int i = 0; i < 12; i++) {
                        double angle = Math.PI * 2 * i / 12;
                        Location branch = loc.clone().add(Math.cos(angle) * r, 0, Math.sin(angle) * r);
                        player.getWorld().spawnParticle(Particle.END_ROD, branch, 3);
                    }
                }
            }
        }

        player.getWorld().playSound(center, Sound.BLOCK_BEACON_AMBIENT, 1, 0.7f);
    }
}
