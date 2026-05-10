package minenaruto.narutoplugin.abilities.Kekkei_Genkai.scorch;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

// Способность 1: Heat Wave (Тепловая волна)
public class HeatWave extends ScorchAbility {
    public HeatWave() {
        super("Тепловая Волна");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        for (Entity entity : player.getNearbyEntities(5, 5, 5)) {
            if (entity instanceof LivingEntity) {
                ((LivingEntity) entity).setFireTicks(100);
            }
        }
    }
}
