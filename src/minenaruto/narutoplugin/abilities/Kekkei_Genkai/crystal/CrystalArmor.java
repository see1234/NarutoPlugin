package minenaruto.narutoplugin.abilities.Kekkei_Genkai.crystal;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

// Способность 4: Crystal Armor (Защитная броня)
public class CrystalArmor extends CrystalAbility {
    public CrystalArmor() {
        super("Кристальная Броня");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 1));
        player.getWorld().spawnParticle(Particle.CRIT_MAGIC, player.getLocation(), 50, 1, 1, 1, 0.2);
    }
}
