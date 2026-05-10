package minenaruto.narutoplugin.abilities.clan.yotsuki;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

// Способность 1: Lightning Strike (Молниеносный Удар)
public class LightningStrike extends YotsukiAbility {
    public LightningStrike() {
        super("Молниеносный Удар");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        for (Entity entity : player.getNearbyEntities(4, 4, 4)) {
            if (entity instanceof LivingEntity) {
                addDamageEntity(player, entity, 6);
                entity.getWorld().strikeLightningEffect(entity.getLocation());
            }
        }
    }
}
