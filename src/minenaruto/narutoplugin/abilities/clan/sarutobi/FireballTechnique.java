package minenaruto.narutoplugin.abilities.clan.sarutobi;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Sound;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;

public class FireballTechnique extends SarutobiTechnique {
    public FireballTechnique() {
        super("Огненный Шар");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        Fireball fireball = player.launchProjectile(Fireball.class);
        fireball.setVelocity(player.getLocation().getDirection().multiply(1.5));
        fireball.setIsIncendiary(true);
        fireball.setYield(2.0f);

        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_BLAZE_SHOOT, 1, 1);
    }
}
