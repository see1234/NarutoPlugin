package minenaruto.narutoplugin.abilities.sharingan;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
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

public class SharinganItachiAmateracy extends AbilitiesMain {
    private Item item = new Item(Material.DIAMOND_HOE, 77, "§7[§6Naruto§7] §4Шаринган Итачи (Аматерасу)", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));
	@Override
	public void RightClick(Player player, NarutoPlayer pl) {
		if (AbilityListener.checkChakraItem(player, getItem().getName(), 100, 0, 0, 0, 0)) {
		//	List<ArmorStand> arrayArmorStand = spawnArmorStand(playerlocation, 1);
				runTaskAbility(player);

		}
	}

	@Override
	public void RightPlusShift(Player player, NarutoPlayer pl) {
		// TODO Auto-generated method stub
		if (AbilityListener.checkChakraItem(player, getItem().getName(), 0, 0, 0, 0, 0)) {
			if(pl.IfHasJustuPointAndRemoveJustuPoint(5)) {
				//player.getInventory().addItem(Item.items.get(16).getItemStack());
			}
		}
	}

	@Override
	public Item getItem() {
		return item;
	}

	public void runTaskAbility(Player player) {
		final LivingEntity entityTarget = rayTraceEntity(player, 30);
		if(entityTarget == null) {
		player.sendMessage("§7[§6Naruto§7] §f" + "Вы промахнулись и потратили чакру");
		}
		else {
	     	 entityTarget.setFireTicks(10000);
		}
  


	}

 

 

}