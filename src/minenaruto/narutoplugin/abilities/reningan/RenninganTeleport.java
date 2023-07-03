package minenaruto.narutoplugin.abilities.reningan;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.abilities.AbilityListener;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import minenaruto.narutoplugin.main.Main;
import org.bukkit.util.BlockIterator;

public class RenninganTeleport extends AbilitiesMain {
    private Item item = new Item(Material.DIAMOND_HOE, 72, "§7[§6Naruto§7] §5Риннеган Саске (Сохранение локаций)", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));
    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 100, 0, 0, 0, 0)) {
            //	List<ArmorStand> arrayArmorStand = spawnArmorStand(playerlocation, 1);
            runTaskAbility(pl, player);

        }
    }



    @Override
    public Item getItem() {
        return item;
    }

    public void runTaskAbility(NarutoPlayer np, Player player) {
        ArrayList<Block> block = new ArrayList<>();
        float yaw = player.getEyeLocation().getYaw();
        float pitch = player.getEyeLocation().getPitch();
        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.getInstance(), () -> {
            player.getWorld().spawnParticle(Particle.PORTAL, player.getLocation(), 100, 1.04D, 1.1D, 0.04D, 0.01D);
            player.getEyeLocation().setYaw(yaw);
            player.getEyeLocation().setPitch(pitch);
        },20L);
        BlockIterator iteratorBlock = new BlockIterator((LivingEntity)player, 15);
        while (iteratorBlock.hasNext()) {
            Block b = iteratorBlock.next();
            Location loc = new Location(b.getWorld(), b.getLocation().getBlockX(), b.getLocation().getBlockY(), b.getLocation().getBlockZ(), yaw, pitch);
            if (b.getType() != Material.AIR) {
                if (b.isEmpty()) {
                    block.add(b);
                    player.teleport(loc);
                    block.clear();
                }
                continue;
            }
            player.getWorld().spawnParticle(Particle.PORTAL, player.getLocation(), 100, 1.04D, 1.1D, 1.04D, 0.01D);
            player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 20.0F, 20.0F);
            player.teleport(loc);
        }



    }





}