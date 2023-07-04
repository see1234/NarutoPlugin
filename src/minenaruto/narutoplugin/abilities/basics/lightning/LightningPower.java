package minenaruto.narutoplugin.abilities.basics.lightning;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;

import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.abilities.AbilityListener;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import minenaruto.narutoplugin.main.Main;

public class LightningPower extends AbilitiesMain {
    private Item item = new Item(Material.DIAMOND_HOE, 57, "§7[§6Naruto§7] §6Молния с небес", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 100, 0, 0, 0, 0)) {

            //	List<ArmorStand> arrayArmorStand = spawnArmorStand(playerlocation, 1);
            runTaskAbility(player);

        }
    }



    @Override
    public Item getItem() {
        return item;
    }

    public void runTaskAbility(Player player) {
        final LivingEntity entityTarget = rayTraceEntity(player, 16);
        if (entityTarget == null) {
            player.sendMessage("§7[§6Naruto§7] §f" + "Вы не попали");
        } else {
               entityTarget.getWorld().strikeLightning(entityTarget.getLocation());
                addDamageEntity(player,entityTarget, 3);

        }

    }


}