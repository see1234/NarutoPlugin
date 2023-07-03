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
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;

import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.abilities.AbilityListener;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import minenaruto.narutoplugin.main.Main;

public class StoneBullets extends AbilitiesMain {
    private Item item = new Item(Material.DIAMOND_HOE, 52, "§7[§6Naruto§7] §6Каменные пули", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));
	@Override
	public void RightClick(Player player, NarutoPlayer pl) {
		if (AbilityListener.checkChakraItem(player, getItem().getName(), 20, 0, 0, 0, 0)) {
			Location playerlocation = player.getLocation().clone();
			List<ArmorStand> arrayArmorStand = spawnArmorStand(playerlocation, 9);
			for (ArmorStand arm : arrayArmorStand) {
				runTaskAbility(arm, player);
			}
		}
	}



	public void runTaskAbility(ArmorStand arm, Player player) {
		BukkitRunnable task = new BukkitRunnable() {
			int count = 0;

			@Override
			public void run() {
				if (count++ != 200) {
					if (arm.getLocation().getBlock().getType() != Material.AIR) {
						arm.remove();
						cancel();
					}
					try {
					arm.teleport(arm.getLocation().add(player.getLocation().getDirection().multiply(2)));
					for (Entity en : arm.getLocation().getWorld().getNearbyEntities(arm.getLocation(), 2.0, 2.0, 2.0)) {
						if (!(en instanceof LivingEntity) || (en instanceof ArmorStand)
								|| !(arm.getLocation().distance(en.getLocation()) < 2.0) || en == player)
							continue;
						if (en instanceof Player) {
							if (!hasPvpZone(en))
								continue;
							addDamageEntity(player, en, 8);
							arm.remove();
							continue;
						}

						addDamageEntity(player, en, 10);
						arm.remove();
					}
					}
					catch(Exception ex) {
						
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
			Random rand = new Random();
			int boolx = rand.nextBoolean() ? rand.nextInt(i + 1) : -rand.nextInt(i + 1);
			int boolz = rand.nextBoolean() ? rand.nextInt(i + 1) : -rand.nextInt(i + 1);
			Vector vec = new Vector(boolx, (rand.nextInt(i + 1) / 2), boolz);
			ArmorStand armorstand = (ArmorStand) loc.getWorld().spawnEntity(loc.clone().add(vec),
					EntityType.ARMOR_STAND);
			armorstand.setVisible(false);
			armorstand.setGravity(false);
			armorstand.setBasePlate(false);
			armorstand.setHelmet(getModels().get("stone").getItemStack());
			arrayArmorStand.add(armorstand);
		}
		return arrayArmorStand;
	}
	@Override
	public Item getItem() {

		return item;
	}
}