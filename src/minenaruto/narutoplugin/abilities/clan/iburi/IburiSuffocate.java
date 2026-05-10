package minenaruto.narutoplugin.abilities.clan.iburi;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class IburiSuffocate extends IburiAbility {
    public IburiSuffocate() {
        super("Задушение Дымом");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        for (Entity entity : player.getNearbyEntities(5, 5, 5)) {
            if (entity instanceof LivingEntity) {
                ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 200, 1));
            }
        }
    }
}
