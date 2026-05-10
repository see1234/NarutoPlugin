package minenaruto.narutoplugin.abilities.clan.sarutobi;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class FireCloneTechnique extends SarutobiTechnique {
    public FireCloneTechnique() {
        super("Огненный Клон");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        ArmorStand clone = (ArmorStand) player.getWorld().spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);
        clone.setInvisible(true);
        clone.setSmall(true);
        clone.setCustomName("§6Огненный Клон");

        // Эффекты огня
        player.getWorld().spawnParticle(Particle.FLAME, clone.getLocation(), 30);
        player.getWorld().playSound(clone.getLocation(), Sound.BLOCK_FIRE_AMBIENT, 1, 1);

        // Исчезает через 10 секунд
        Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
            if (clone.isValid()) {
                clone.getWorld().spawnParticle(Particle.SMOKE_LARGE, clone.getLocation(), 20);
                clone.remove();
            }
        }, 200L);
    }
}
