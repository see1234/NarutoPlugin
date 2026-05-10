package minenaruto.narutoplugin.abilities.sharingan;
import java.io.File;
import java.util.*;

import minenaruto.narutoplugin.ParticleEffect;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.abilities.AbilityListener;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import minenaruto.narutoplugin.main.Main;
public class Izanami extends AbilitiesMain {
    private Item item = new Item(Material.DIAMOND_HOE, 88, "§7[§6Naruto§7] §4Идзанами", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 80, 0, 0, 0, 0)) {
            castIzanami(player);
        }
    }

    public void castIzanami(Player player) {
        for (Entity en : player.getNearbyEntities(10, 10, 10)) {
            if (en instanceof Player) {
                Player target = (Player) en;
                // Наложение эффекта слепоты и замедления
                target.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 300, 1));
                target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 300, 1));
            }
        }
    }

    @Override
    public Item getItem() {
        return item;
    }
}