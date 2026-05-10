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

public class Sharingan extends AbilitiesMain {
	private Item item = new Item(Material.DIAMOND_HOE, 87, "§7[§6Naruto§7] §4Шаринган Гендзюцу", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));
	public static HashMap<UUID, ArrayList<Bat>> bats = new HashMap<UUID, ArrayList<Bat>>();
	@Override
	public void RightClick(Player player, NarutoPlayer np) {
	 
		if(AbilityListener.checkChakraItem(player, getItem().getName(), 80, 50, 0, 10, 0)) {
			final LivingEntity entityTarget = rayTraceEntity(player, 16);
			if(entityTarget == null) {
			player.sendMessage("§7[§6Naruto§7] §f" + "Не попал!");	
			}
			else {
		     	if(entityTarget instanceof Player) {
			         ((Player) entityTarget).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20*10, 0));

		     	}
		     	else {
		     		entityTarget.setAI(false);
		     		Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
						
						@Override
						public void run() {
							entityTarget.setAI(true);
						}
					}, 40L);
		     		 
		     	}
				Particle.DustOptions dust = new Particle.DustOptions(
						Color.fromRGB((int) 1 * 255, (int) 0 * 255, (int) 0 * 255), 1);
				entityTarget.getLocation().getWorld().spawnParticle(Particle.REDSTONE, entityTarget.getLocation().getX(), entityTarget.getLocation().getY(), entityTarget.getLocation().getZ(), 10, 0, 2, 0, dust);

			}

	 
			 
             
		}
		
	}


	@Override
	public Item getItem() {

		return item;
	}
}
