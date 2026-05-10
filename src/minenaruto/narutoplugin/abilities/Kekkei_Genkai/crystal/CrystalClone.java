package minenaruto.narutoplugin.abilities.Kekkei_Genkai.crystal;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.main.Main;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

// Способность 5: Crystal Clone (Клон из кристалла)
public class CrystalClone extends CrystalAbility {
    public CrystalClone() {
        super("Кристальный Клон");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        ArmorStand clone = (ArmorStand) player.getWorld().spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);
        clone.setCustomName(player.getName());
        clone.setCustomNameVisible(true);
        clone.setInvisible(false);
        clone.setInvulnerable(true);

        new BukkitRunnable() {
            @Override
            public void run() {
                clone.remove();
            }
        }.runTaskLater(Main.getInstance(), 200);
    }
}
