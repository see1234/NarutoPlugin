package minenaruto.narutoplugin.abilities.Kekkei_Genkai.scorch;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;

// Способность 3: Blazing Sphere (Пылающая Сфера)
public class BlazingSphere extends ScorchAbility {
    public BlazingSphere() {
        super("Пылающая Сфера");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        Fireball fireball = player.launchProjectile(Fireball.class);
        fireball.setIsIncendiary(true);
        fireball.setYield(2);
    }
}
