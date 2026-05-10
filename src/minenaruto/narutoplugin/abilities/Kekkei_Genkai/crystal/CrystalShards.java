package minenaruto.narutoplugin.abilities.Kekkei_Genkai.crystal;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.main.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

// Способность 2: Crystal Shards (Выстрел осколками)
public class CrystalShards extends CrystalAbility {
    public CrystalShards() {
        super("Кристальные Осколки");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        Location loc = player.getEyeLocation();
        Vector direction = loc.getDirection();

        for (int i = 0; i < 5; i++) {
            ArmorStand shard = (ArmorStand) player.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
            shard.setInvisible(true);
            shard.setSmall(true);
            shard.getEquipment().setHelmet(new ItemStack(Material.PURPLE_STAINED_GLASS));
            shard.setVelocity(direction.multiply(1.5));

            new BukkitRunnable() {
                @Override
                public void run() {
                    shard.remove();
                }
            }.runTaskLater(Main.getInstance(), 40);
        }
    }
}
