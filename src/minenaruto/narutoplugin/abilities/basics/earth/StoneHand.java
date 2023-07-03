package minenaruto.narutoplugin.abilities.basics.earth;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

public class StoneHand extends AbilitiesMain {
   private Item item = new Item(Material.DIAMOND_HOE, 52, "§7[§6Naruto§7] §6Каменный кулак", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));
	@Override
	public void RightClick(Player player, NarutoPlayer pl) {
		if (AbilityListener.checkChakraItem(player, getItem().getName(), 20, 0, 0, 0, 0)) {
			runTaskAbility(player);
		}
	}



	public void runTaskAbility(Player player) {
		player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 120, 3, true));
	}

	@Override
	public Item getItem() {

		return item;
	}
}