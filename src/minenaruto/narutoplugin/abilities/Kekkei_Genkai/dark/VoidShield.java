package minenaruto.narutoplugin.abilities.Kekkei_Genkai.dark;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

// Способность 5: Void Shield (Щит Пустоты)
public class VoidShield extends DarkAbility {
    public VoidShield() {
        super("Щит Пустоты");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 200, 2));
        player.getWorld().spawnParticle(Particle.SPELL_WITCH, player.getLocation(), 50, 1, 1, 1, 0.2);
    }
}
