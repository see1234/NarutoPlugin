package minenaruto.narutoplugin.abilities.clan.ryu;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class DragonScaleArmorTechnique extends RyuTechnique {
    public DragonScaleArmorTechnique() {
        super("Чешуя Дракона");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 300, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 300, 0));

        // Эффекты чешуи
        player.getWorld().spawnParticle(Particle.REDSTONE, player.getLocation(), 50,
                new Particle.DustOptions(Color.RED, 2));
        player.getWorld().playSound(player.getLocation(), Sound.ITEM_ARMOR_EQUIP_NETHERITE, 1, 1);
    }
}
