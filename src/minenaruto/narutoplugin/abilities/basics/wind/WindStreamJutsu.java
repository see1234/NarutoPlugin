package minenaruto.narutoplugin.abilities.basics.wind;
import minenaruto.narutoplugin.ParticleEffect;
import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.abilities.AbilityListener;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import minenaruto.narutoplugin.main.Main;
import org.bukkit.World;
import org.bukkit.plugin.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.event.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import org.bukkit.event.block.*;
import org.bukkit.*;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.*;


import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.scheduler.*;
import org.bukkit.util.Vector;
import org.bukkit.entity.Entity;
public class WindStreamJutsu extends AbilitiesMain {
    private Item item = new Item(Material.DIAMOND_HOE, 72, "§7[§6Naruto§7] §aВоздушный поток", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 30, 0, 0, 0, 0)) {
            Location loc = player.getLocation().add(player.getLocation().getDirection().multiply(2));
            player.getWorld().spawnParticle(Particle.CLOUD, loc, 50, 1, 1, 1, 0.1);

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

