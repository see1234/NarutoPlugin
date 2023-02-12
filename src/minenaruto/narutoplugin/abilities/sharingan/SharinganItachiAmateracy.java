package minenaruto.narutoplugin.abilities.sharingan;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.abilities.AbilityListener;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import minenaruto.narutoplugin.main.Main;

public class SharinganItachiAmateracy extends AbilitiesMain {

	@Override
	public void RightClick(Player player, NarutoPlayer pl) {
		if (AbilityListener.checkChakraItem(player, Item.items.get(15).getName(), 100, 0, 0, 0, 0)) {
		//	List<ArmorStand> arrayArmorStand = spawnArmorStand(playerlocation, 1);
				runTaskAbility(player);

		}
	}

	@Override
	public void RightPlusShift(Player player, NarutoPlayer pl) {
		// TODO Auto-generated method stub
		if (AbilityListener.checkChakraItem(player, Item.items.get(15).getName(), 0, 0, 0, 0, 0)) {
			if(pl.IfHasJustuPointAndRemoveJustuPoint(5)) {
				player.getInventory().addItem(Item.items.get(16).getItemStack());
			}
		}
	}

	public void runTaskAbility(Player player) {
		final LivingEntity entityTarget = rayTraceEntity(player, 30);
		if(entityTarget == null) {
		player.sendMessage("ß7[ß6Narutoß7] ßf" + "¬˚ ÌÂ ÔÓÔ‡ÎË ‚ ÌÂ„Ó");	
		}
		else {
	     	 entityTarget.setFireTicks(10000);
		}
  


	}

 

 

}