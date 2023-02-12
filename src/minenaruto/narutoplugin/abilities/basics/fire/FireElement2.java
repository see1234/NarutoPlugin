package minenaruto.narutoplugin.abilities.basics.fire;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
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
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;

import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.abilities.AbilityListener;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import minenaruto.narutoplugin.main.Main;

public class FireElement2 extends AbilitiesMain {

	@Override
	public void RightClick(Player player, NarutoPlayer pl) {
		if (AbilityListener.checkChakraItem(player, Item.items.get(3).getName(), 100, 0, 0, 0, 0)) {
			Location playerlocation = player.getLocation().clone();
			List<ArmorStand> arrayArmorStand = spawnArmorStand(playerlocation, 1);
			for (ArmorStand arm : arrayArmorStand) {
				runTaskAbility(arm, player);
			}
		}
	}

	@Override
	public void RightPlusShift(Player player, NarutoPlayer pl) {
		// TODO Auto-generated method stub
		if (AbilityListener.checkChakraItem(player, Item.items.get(3).getName(), 0, 0, 0, 0, 0)) {
			if(pl.IfHasJustuPointAndRemoveJustuPoint(5)) {
				player.getInventory().addItem(Item.items.get(4).getItemStack());
			}
		}
	}

	public void runTaskAbility(ArmorStand arm, Player player) {
		BukkitRunnable task = new BukkitRunnable() {
			int count = 0;

			@Override
			public void run() {
				if (count++ != 200) {
				 
					try {
						 
					arm.teleport(arm.getLocation().add(player.getLocation().getDirection().multiply(2.5)));
					arm.getWorld().spawnParticle(Particle.FLAME, arm.getLocation(), 25);

			
					Bukkit.getScheduler().runTask(Main.getInstance(), new Runnable() {
						@Override
						public void run() {
					for (Entity en : arm.getLocation().getWorld().getNearbyEntities(arm.getLocation(), 4.0, 4.0, 4.0)) {
						
						if (!(en instanceof LivingEntity) || (en instanceof ArmorStand)
								 || en == player)
							continue;
						if (en instanceof Player) {
							if (!hasPvpZone(en))
								continue;
							addDamageEntity(player, en, 25);
							
							en.setFireTicks(100);
							

							continue;
						}

						addDamageEntity(player, en, 30);
						en.setFireTicks(100);
					}
						}
					});
					}
					catch(Exception ex) {
						
					}
					if (arm.getLocation().getBlock().getType() != Material.AIR) {
						Bukkit.getScheduler().runTask(Main.getInstance(), new Runnable() {
							@Override
							public void run() {
								arm.getWorld().createExplosion(arm.getLocation().getX(), arm.getLocation().getY(), arm.getLocation().getZ(), 1.0f, true, false);
								if (arm.getLocation().clone().add(new Vector(0, 1, 0)).getBlock()
										.getType() == Material.AIR) {
									arm.getLocation().clone().add(new Vector(0, 1, 0)).getBlock()
											.setType(Material.FIRE);
								}
								
							}
						});
						arm.remove();
						cancel();
					}
				} else {
					arm.remove();
					cancel();
				}
			}
		};
		task.runTaskTimerAsynchronously(Main.getInstance(), 1L, 1L);
	}

	public List<ArmorStand> spawnArmorStand(Location loc, int size) {
		ArrayList<ArmorStand> arrayArmorStand = new ArrayList<ArmorStand>();
		for (int i = 0; i < size; i++) {
 
			//int boolx = rand.nextBoolean() ? rand.nextInt(i + 1) : -rand.nextInt(i + 1);
			//int boolz = rand.nextBoolean() ? rand.nextInt(i + 1) : -rand.nextInt(i + 1);
			//Vector vec = new Vector(boolx, (rand.nextInt(i + 1) / 2), boolz);
			ArmorStand armorstand = (ArmorStand) loc.getWorld().spawnEntity(loc.clone(),
					EntityType.ARMOR_STAND);
			armorstand.setGravity(false);
			armorstand.setBasePlate(false);
			armorstand.setVisible(false);
			armorstand.setItemInHand(Item.items.get(2).getItemStack());
			arrayArmorStand.add(armorstand);
		}
		return arrayArmorStand;
	}

 

}