package minenaruto.narutoplugin.abilities.clan.nara;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;

public class ShadowImitationTechnique extends NaraTechnique {
    private final Map<Player, LivingEntity> shadowConnections = new HashMap<>();

    public ShadowImitationTechnique() {
        super("Shadow Imitation");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        // Поиск ближайшей цели
        LivingEntity target = null;
        for (Entity entity : player.getNearbyEntities(10, 3, 10)) {
            if (entity instanceof LivingEntity && !entity.equals(player)) {
                target = (LivingEntity) entity;
                break;
            }
        }

        if (target != null) {
            shadowConnections.put(player, target);
            target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 255, false, false));

            // Эффекты теней
            player.getWorld().spawnParticle(Particle.SQUID_INK, player.getLocation(), 50);
            player.getWorld().spawnParticle(Particle.SQUID_INK, target.getLocation(), 50);
            player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 0.8f);

            // Разрыв связи через 10 секунд
            Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
                if (shadowConnections.remove(player) != null) {
                    player.sendMessage(ChatColor.GRAY + "Shadow connection released");
                }
            }, 200L);
        }
    }
}
