package minenaruto.narutoplugin.abilities.Kekkei_Genkai.dark;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

// Способность 4: Abyssal Chains (Цепи Бездны)
public class AbyssalChains extends DarkAbility {
    public AbyssalChains() {
        super("Цепи Бездны");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        Location loc = player.getTargetBlockExact(5).getLocation();
        for (Entity entity : loc.getWorld().getNearbyEntities(loc, 3, 3, 3)) {
            if (entity instanceof LivingEntity && entity != player) {
                ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 100, 1));
            }
        }
    }
}
