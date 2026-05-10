package minenaruto.narutoplugin.abilities.clan.nara;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class ShadowCloneTechnique extends NaraTechnique {
    public ShadowCloneTechnique() {
        super("Shadow Clone");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        ArmorStand clone = (ArmorStand) player.getWorld().spawnEntity(
                player.getLocation().add(1, 0, 0), EntityType.ARMOR_STAND);

        clone.setInvisible(true);
        clone.setSmall(true);
        clone.setCustomNameVisible(true);
        clone.setCustomName(ChatColor.DARK_GRAY + "Shadow Clone");

        // Эффекты при создании
        player.getWorld().spawnParticle(Particle.SMOKE_LARGE, clone.getLocation(), 30);
        player.getWorld().playSound(clone.getLocation(), Sound.ENTITY_ILLUSIONER_MIRROR_MOVE, 1, 1);

        // Автоматическое исчезновение через 15 секунд
        Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
            if (clone.isValid()) {
                clone.getWorld().spawnParticle(Particle.SMOKE_LARGE, clone.getLocation(), 20);
                clone.remove();
            }
        }, 300L);
    }
}
