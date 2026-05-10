package minenaruto.narutoplugin.abilities.basics.water;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.BlockPosition;
import com.comphenix.protocol.wrappers.WrappedBlockData;
import minenaruto.narutoplugin.ParticleEffect;
import org.bukkit.*;
import org.bukkit.block.Block;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;

import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.abilities.AbilityListener;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import minenaruto.narutoplugin.main.Main;

public class WaterWhip extends AbilitiesMain {
    private Item item = new Item(Material.DIAMOND_HOE, 71, "§7[§6Naruto§7] §bВодяной кнут", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 20, 0, 0, 0, 0)) {
            Location playerLocation = player.getLocation().clone();
            ArmorStand whip = spawnWhip(playerLocation, player);
            runTaskAbility(whip, player);
        }
    }

    public void runTaskAbility(ArmorStand whip, Player player) {
        BukkitRunnable task = new BukkitRunnable() {
            int count = 0;

            @Override
            public void run() {
                if (count++ != 100) {
                    whip.teleport(whip.getLocation().add(player.getLocation().getDirection().multiply(1.5)));

                    // Создаем эффект воды с помощью частиц
                    Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB(0, 0, 255), 1);
                    whip.getLocation().getWorld().spawnParticle(Particle.WATER_SPLASH, whip.getLocation(), 10, 0.5, 0.5, 0.5, 0.1);
                    whip.getLocation().getWorld().spawnParticle(Particle.REDSTONE, whip.getLocation(), 5, 0.5, 0.5, 0.5, dust);

                    // Наносим урон и отталкиваем врагов
                    for (Entity en : whip.getLocation().getWorld().getNearbyEntities(whip.getLocation(), 2.0, 2.0, 2.0)) {
                        if (!(en instanceof LivingEntity) || (en instanceof ArmorStand) || en == player)
                            continue;

                        if (en instanceof Player) {
                            if (!hasPvpZone(en))
                                continue;

                            addDamageEntity(player, en, 5);
                            en.setVelocity(en.getLocation().toVector().subtract(whip.getLocation().toVector()).normalize().multiply(1.5));
                            continue;
                        }

                        addDamageEntity(player, en, 7);
                        en.setVelocity(en.getLocation().toVector().subtract(whip.getLocation().toVector()).normalize().multiply(1.5));
                    }

                    // Оставляем след из воды с помощью пакетов
                    if (count % 5 == 0) {
                        sendWaterPacket(whip.getLocation(), player);
                    }

                } else {
                    whip.remove();
                    cancel();
                }
            }
        };
        task.runTaskTimer(Main.getInstance(), 1L, 1L);
    }

    private void sendWaterPacket(Location location, Player player) {
        PacketContainer packet = new PacketContainer(PacketType.Play.Server.BLOCK_CHANGE);
        packet.getBlockPositionModifier().write(0, new BlockPosition(location.getBlockX(), location.getBlockY(), location.getBlockZ()));
        packet.getBlockData().write(0, WrappedBlockData.createData(Material.WATER));

        try {
            ProtocolLibrary.getProtocolManager().sendServerPacket(player, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArmorStand spawnWhip(Location loc, Player player) {
        ArmorStand whip = (ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
        whip.setVisible(false);
        whip.setGravity(false);
        whip.setBasePlate(false);
        return whip;
    }

    @Override
    public Item getItem() {
        return item;
    }
}