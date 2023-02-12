package minenaruto.narutoplugin.abilities.sharingan;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
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

public class Sharingan extends AbilitiesMain {
	public static HashMap<UUID, ArrayList<Bat>> bats = new HashMap<UUID, ArrayList<Bat>>();
	@Override
	public void RightClick(Player player, NarutoPlayer np) {
	 
		if(AbilityListener.checkChakraItem(player, Item.items.get(11).getName(), 80, 50, 0, 10, 0)) {
			final LivingEntity entityTarget = rayTraceEntity(player, 16);
			if(entityTarget == null) {
			player.sendMessage("§7[§6Naruto§7] §f" + "Не попал!");	
			}
			else {
		     	if(entityTarget instanceof Player) {
			         ((Player) entityTarget).sendTitle("쀁", "", 20, 60, 20);
		     	}
		     	else {
		     		entityTarget.setAI(true);
		     		Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
						
						@Override
						public void run() {
							entityTarget.setAI(false);
						}
					}, 20L);
		     		 
		     	}
			}

	 
			 
             
		}
		
	}

	@Override
	public void RightPlusShift(Player player, NarutoPlayer np) {
		// TODO Auto-generated method stub
		
	}
 
}
