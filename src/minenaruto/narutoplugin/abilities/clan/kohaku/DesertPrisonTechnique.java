package minenaruto.narutoplugin.abilities.clan.kohaku;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class DesertPrisonTechnique extends KohakuTechnique {
    public DesertPrisonTechnique() {
        super("Тюрьма Пустыни");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        for (Entity entity : player.getNearbyEntities(5, 3, 5)) {
            if (entity instanceof LivingEntity && !entity.equals(player)) {
                ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 3));
                entity.getWorld().spawnParticle(Particle.BLOCK_CRACK, entity.getLocation(), 30, Material.SAND.createBlockData());
            }
        }
        player.getWorld().playSound(player.getLocation(), Sound.BLOCK_SAND_BREAK, 1, 0.8f);
    }
}
