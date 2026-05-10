package minenaruto.narutoplugin.abilities.Kekkei_Genkai.storm;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

// Способность 4: Storm Surge (Штормовой Поток)
public class StormSurge extends StormAbility {
    public StormSurge() {
        super("Штормовой Поток");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        for (Entity entity : player.getNearbyEntities(5, 5, 5)) {
            if (entity instanceof LivingEntity) {
                ((LivingEntity) entity).setVelocity(new Vector(0, 1, 0));
            }
        }
    }
}
