package minenaruto.narutoplugin.abilities.Kekkei_Genkai.lava;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

// Способность 4: Burning Chains (Горящие Цепи)
public class BurningChains extends LavaAbility {
    public BurningChains() {
        super("Горящие Цепи");
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
