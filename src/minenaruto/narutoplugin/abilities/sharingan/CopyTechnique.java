package minenaruto.narutoplugin.abilities.sharingan;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import minenaruto.narutoplugin.utils.Task;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
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
public class CopyTechnique extends AbilitiesMain {
    private Item item = new Item(Material.DIAMOND_HOE, 89, "§7[§6Naruto§7] §4Копирование техник", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 20, 0, 0, 0, 0)) {
            copyTechnique(player);
        }
    }

    public void copyTechnique(Player player) {
        for (Entity en : player.getNearbyEntities(10, 10, 10)) {
            if (en instanceof Player && !en.equals(player)) {
                Player target = (Player) en;
                // Копирование последней использованной способности
                if(AbilityListener.abilityLast.containsKey(target)) {
                    AbilityListener.copyAbility(player, AbilityListener.abilityLast.get(target));
                }
                break;
            }
        }
    }

    @Override
    public Item getItem() {
        return item;
    }
}