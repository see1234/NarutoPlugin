package minenaruto.narutoplugin.abilities.clan.kohaku;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class SandCloneTechnique extends KohakuTechnique {
    public SandCloneTechnique() {
        super("Песчаный Клон");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        ArmorStand clone = (ArmorStand) player.getWorld().spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);
        clone.setInvisible(true);
        clone.setSmall(true);
        clone.setGravity(false);
        clone.getWorld().spawnParticle(Particle.FALLING_DUST, clone.getLocation(), 50, Material.SAND.createBlockData());

        Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
            clone.getWorld().spawnParticle(Particle.BLOCK_CRACK, clone.getLocation(), 30, Material.SAND.createBlockData());
            clone.remove();
        }, 100L);
    }
}
