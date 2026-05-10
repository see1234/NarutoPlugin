package minenaruto.narutoplugin.abilities.clan.fuma;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;

public class FumaShurikenThrow extends FumaAbility {
    public FumaShurikenThrow() {
        super("Метание Фума Сюрикена");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        Snowball shuriken = player.launchProjectile(Snowball.class);
        shuriken.setVelocity(player.getLocation().getDirection().multiply(2));
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_SNOWBALL_THROW, 1, 1);
    }
}
