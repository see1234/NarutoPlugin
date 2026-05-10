package minenaruto.narutoplugin.abilities.clan.aburame;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

// Способность 3: Parasitic Drain (Паразитическое Истощение)
public class ParasiticDrain extends AburameAbility {
    public ParasiticDrain() {
        super("Паразитическое Истощение");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        for (Entity entity : player.getNearbyEntities(5, 5, 5)) {
            if (entity instanceof LivingEntity) {
                ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 100, 1));
            }
        }
    }
}
