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
public class Izanagi extends AbilitiesMain {
    private Item item = new Item(Material.DIAMOND_HOE, 88, "§7[§6Naruto§7] §4Идзанаги", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 70, 0, 0, 0, 0)) {
            castIzanagi(player);
        }
    }

    public void castIzanagi(Player player) {
        // Восстановление здоровья
        player.setHealth(player.getHealth() + 10);
    }

    @Override
    public Item getItem() {
        return item;
    }
}