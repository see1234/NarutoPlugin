package minenaruto.narutoplugin.abilities.basics.wind;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import minenaruto.narutoplugin.ParticleEffect;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
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

public class WindExplosionJutsu extends AbilitiesMain {
    private Item item = new Item(Material.DIAMOND_HOE, 72, "§7[§6Naruto§7] §aВоздушный взрыв", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 40, 0, 0, 0, 0)) {
            Location loc = player.getLocation().add(player.getLocation().getDirection().multiply(2));
            player.getWorld().spawnParticle(Particle.CLOUD, loc, 100, 2, 2, 2, 0.1);

            for (Entity entity : loc.getWorld().getNearbyEntities(loc, 3, 3, 3)) {
                if (entity instanceof LivingEntity && entity != player) {
                    entity.setVelocity(player.getLocation().getDirection().multiply(2));
                }
            }
        }
    }

    @Override
    public Item getItem() {
        return item;
    }
}
