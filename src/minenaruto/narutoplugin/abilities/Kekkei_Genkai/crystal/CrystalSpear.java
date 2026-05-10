package minenaruto.narutoplugin.abilities.Kekkei_Genkai.crystal;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Trident;

// Способность 3: Crystal Spear (Метательный Копьё)
public class CrystalSpear extends CrystalAbility {
    public CrystalSpear() {
        super("Кристальное Копьё");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        Trident spear = (Trident) player.getWorld().spawnEntity(player.getEyeLocation(), EntityType.TRIDENT);
        spear.setVelocity(player.getEyeLocation().getDirection().multiply(2));
        spear.setShooter(player);
        spear.setPersistent(false);
    }
}
