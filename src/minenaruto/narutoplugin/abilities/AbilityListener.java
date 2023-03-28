package minenaruto.narutoplugin.abilities;

import java.util.ArrayList;
import java.util.HashMap;

import minenaruto.narutoplugin.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import com.sk89q.worldguard.LocalPlayer;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.association.RegionAssociable;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import minenaruto.narutoplugin.abilities.basics.ShadowClon;
import minenaruto.narutoplugin.abilities.basics.earth.StoneArmor;
import minenaruto.narutoplugin.abilities.basics.earth.StoneBullets;
import minenaruto.narutoplugin.abilities.basics.earth.StoneHand;
import minenaruto.narutoplugin.abilities.basics.fire.FireElement;
import minenaruto.narutoplugin.abilities.basics.fire.FireElement2;
import minenaruto.narutoplugin.abilities.basics.fire.FireElement3;
import minenaruto.narutoplugin.abilities.basics.fire.FireElement4;
import minenaruto.narutoplugin.abilities.basics.lightning.LightningArmor;
import minenaruto.narutoplugin.abilities.basics.lightning.LightningShield;
import minenaruto.narutoplugin.abilities.sharingan.Sharingan;
import minenaruto.narutoplugin.abilities.sharingan.SharinganItachi;
import minenaruto.narutoplugin.abilities.sharingan.SharinganItachiAmateracy;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;

public class AbilityListener implements Listener {
	public static HashMap<String, Long> playercooldown = new HashMap<String, Long>();

	public static boolean hasPvpZone(final Entity entity) {
		if (Bukkit.getServer().getPluginManager().getPlugin("WorldGuard") != null) {
			RegionManager regionManager = WorldGuardPlugin.inst().getRegionManager(entity.getWorld());
			ApplicableRegionSet set = regionManager.getApplicableRegions(entity.getLocation());

			for (ProtectedRegion region : set) {
				if (region != null) {
					if (!set.allows(DefaultFlag.PVP)) {

						return false;
					}
				}
			}

		}
		return true;
	}

	public boolean haveCooldownPlayer(String name) {
		if (playercooldown.containsKey(name)) {
			if (playercooldown.get(name) > System.currentTimeMillis()) {
				return true;
			}
		}
		playercooldown.put(name, System.currentTimeMillis() + 500);
		return false;
	}

	@EventHandler
	public void playerInteract(final PlayerInteractEvent e) {
		final Player player = e.getPlayer();

		if (player.getInventory().getItemInMainHand() != null && player.getInventory().getItemInMainHand().hasItemMeta()
				&& player.getInventory().getItemInMainHand().getItemMeta().hasDisplayName()
				&& player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().startsWith("§7[§6Naruto§7]")
				&& e.getHand() != null && !e.getHand().equals(EquipmentSlot.OFF_HAND)) {
			if (haveCooldownPlayer(e.getPlayer().getName())) return;
			if (hasPvpZone((Entity) player)) {

				NarutoPlayer pl = NarutoPlayer.getNarutoPlayer(player.getName());
				for (final AbilitiesMain ap : Main.getInstance().getAbilities()) {
					if (e.getAction().name().startsWith("RIGHT_CLICK")) {
						if (player.isSneaking()) {
							ap.RightPlusShift(player, pl);
						} else {
							ap.RightClick(player, pl);
						}
					}

				}

			}

		}
	}

	public static void damageEntity(final Entity entity, Player source, double damage) {
		if (entity instanceof LivingEntity) {
			LivingEntity lent = (LivingEntity) entity;
			damage = Math.max(0, damage);
			EntityDamageByEntityEvent finalEvent = new EntityDamageByEntityEvent(source, entity, DamageCause.MAGIC,
					damage);
			lent.damage(damage, source);
            entity.setVelocity(new Vector());
         
			entity.setLastDamageCause(finalEvent);
		}

	}

	public static boolean checkChakraItem(Player pl, String name, int chakra, int genjustu, int taijustu, int summoning,
			int senjustu) {

		if (pl.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase(name)) {
			int a = 0;
			if (a == 0) {
			return true;
			}
			NarutoPlayer player = NarutoPlayer.getNarutoPlayer(pl.getName());
			if (player.getInt("genjustu") < genjustu) {
				player.sendMessage("&cУ вас не хватает генджутсу " + genjustu);
				return false;
			}
			if (player.getInt("taijustu") < taijustu) {
				player.sendMessage("&cУ вас не хватает тайджутсу " + taijustu);
				return false;
			}
			if (player.getInt("summoning") < summoning) {
				player.sendMessage("&cУ вас не хватает призыва " + summoning);
				return false;
			}
			if (player.getInt("senjustu") < senjustu) {
				player.sendMessage("&cУ вас не хватает сенжутсу " + senjustu);
				return false;
			}
			if (player.getInt("chakra") >= chakra) {
				player.setInt("chakra", player.getInt("chakra") - chakra);
				return true;
			} else {
				player.sendMessage("&cДля использования способности не хватает чакры!");
			}

		}
		return false;
	}
}
