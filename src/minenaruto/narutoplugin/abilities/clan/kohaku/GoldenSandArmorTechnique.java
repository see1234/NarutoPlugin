package minenaruto.narutoplugin.abilities.clan.kohaku;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class GoldenSandArmorTechnique extends KohakuTechnique {
    public GoldenSandArmorTechnique() {
        super("Золотая Песчаная Броня");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 400, 2));
        player.getWorld().spawnParticle(Particle.FALLING_DUST, player.getLocation(), 50, 1, 1, 1, 0, Material.GOLD_BLOCK.createBlockData());
        player.getWorld().playSound(player.getLocation(), Sound.BLOCK_SAND_PLACE, 1, 1);
    }
}
