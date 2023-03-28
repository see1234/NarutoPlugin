package minenaruto.narutoplugin.abilities.basics.lightning;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.abilities.AbilityListener;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import minenaruto.narutoplugin.main.Main;

public class LightningShield extends AbilitiesMain {
	private Item item = new Item(293, 57, "§7[§6Naruto§7] §6Штормовой щит", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));
	@Override
	public void RightClick(Player player, NarutoPlayer pl) {
		if (AbilityListener.checkChakraItem(player, getItem().getName(), 50, 0, 0, 0, 0)) {
			runTaskAbility(player);
		}
	}

	@Override
	public void RightPlusShift(Player player, NarutoPlayer pl) {
		// TODO Auto-generated method stub
		if (AbilityListener.checkChakraItem(player, getItem().getName(), 0, 0, 0, 0, 0)) {
			if(pl.IfHasJustuPointAndRemoveJustuPoint(5)) {
				//player.getInventory().addItem(Item.items.get(6).getItemStack());
			}
		}
	}

	public void runTaskAbility(Player player) {
		BukkitRunnable task = new BukkitRunnable() {

			@Override
			public void run() {
	            for (double k = 0.0; k < 3.0; k += 0.5) {
	                for (Entity en : player.getNearbyEntities(k, k, k)) {
	                    if (!(en instanceof LivingEntity)) continue;
	                    if (en instanceof Player) {
	                        if (!hasPvpZone(en)) continue;
	                        addDamageEntity(player, en, 14);
	                        ((LivingEntity)en).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 5));
	                        continue;
	                    }
	                    addDamageEntity(player, en, 20);
	                    ((LivingEntity)en).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 5));
	                }
	                World world = player.getWorld();
	                double radius = k;
	                Location location = player.getLocation();
	                float yaw = player.getEyeLocation().getYaw();
	                int angle = 360;
	                int angleStep = 5;
	                int arcCount = 3;
	                double playerHeight = 2.1;
	                double floorOffset = 0.1;
	                double arcHeight = (playerHeight - floorOffset) / (double)arcCount;
	                for (int arc = 0; arc < arcCount; ++arc) {
	                    for (int i = 0; i <= angle; i += angleStep) {
	                        double radians = Math.toRadians((float)i + yaw - (float)angle / 2.0f);
	                        double x = -Math.sin(radians) * radius;
	                        double y = Math.cos(radians) * radius;
	                        world.spawnParticle(Particle.CRIT_MAGIC, new Vector(x, floorOffset + arcHeight * (double)arc, y).toLocation(world).add(location), 1, 0.0, 0.0, 0.0, 0.0);
	                    }
	                }
	            }
	            cancel();
			}
		};
		task.runTaskTimerAsynchronously(Main.getInstance(), 1L, 1L);
	}
	@Override
	public Item getItem() {
		return item;
	}

 

}