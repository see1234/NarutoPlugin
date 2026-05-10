package minenaruto.narutoplugin.abilities.sage;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.abilities.AbilityListener;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import minenaruto.narutoplugin.main.Main;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BlockIterator;
public class Healing extends AbilitiesMain {
    private Item item = new Item(Material.DIAMOND_HOE, 80, "§7[§6Naruto§7] §6Sage (Healing)", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 30, 0, 0, 0, 0)) {
            useHealing(player);
        }
    }

    public void useHealing(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 2)); // Регенерация
        player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 100, 2)); // Насыщение
        player.getWorld().spawnParticle(Particle.HEART, player.getLocation(), 10, 1, 1, 1, 0.1);
    }

    @Override
    public Item getItem() {
        return item;
    }
}