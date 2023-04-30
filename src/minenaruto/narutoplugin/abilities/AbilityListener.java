package minenaruto.narutoplugin.abilities;

import java.util.HashMap;

import minenaruto.narutoplugin.abilities.basics.earth.StoneBroke;
import minenaruto.narutoplugin.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.util.Vector;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

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
	public void playerInterapct(final PlayerInteractAtEntityEvent e) {
		final Player player = (Player) e.getPlayer();
		if (player.getInventory().getItemInMainHand() != null && player.getInventory().getItemInMainHand().hasItemMeta()
				&& player.getInventory().getItemInMainHand().getItemMeta().hasDisplayName()
				&& player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().startsWith("§7[§6Naruto§7]")
				&& player.getMainHand() != null && !player.getMainHand().equals(EquipmentSlot.OFF_HAND)) {

			if (haveCooldownPlayer(player.getName())) return;

			if (hasPvpZone((Entity) player)) {

				NarutoPlayer pl = NarutoPlayer.getNarutoPlayer(player.getName());
				if(AbilitiesMain.scheduler.containsKey(player)) {
					player.sendMessage("§7[§6Naruto§7] §cВы контроллируете прошлую способку, подождите её действия");
					return;
				}
				for (final AbilitiesMain ap : Main.getInstance().getAbilities()) {

						if (player.isSneaking()) {
							ap.RightPlusShift(player, pl);
						} else {
							ap.RightClick(player, pl);
						}


				}

			}

		}
	}
	@EventHandler
	public void playerInteroact(final PlayerArmorStandManipulateEvent e) {
		final Player player = e.getPlayer();
		if (player.getInventory().getItemInMainHand() != null && player.getInventory().getItemInMainHand().hasItemMeta()
				&& player.getInventory().getItemInMainHand().getItemMeta().hasDisplayName()
				&& player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().startsWith("§7[§6Naruto§7]")
				&& e.getPlayer().getMainHand() != null && !e.getPlayer().getMainHand().equals(EquipmentSlot.OFF_HAND)) {

			if (haveCooldownPlayer(e.getPlayer().getName())) return;

			if (hasPvpZone((Entity) player)) {

				NarutoPlayer pl = NarutoPlayer.getNarutoPlayer(player.getName());

				if(AbilitiesMain.scheduler.containsKey(player)) {
					player.sendMessage("§7[§6Naruto§7] §cВы контроллируете прошлую способку, подождите её действия");
					return;
				}

				for (final AbilitiesMain ap : Main.getInstance().getAbilities()) {
					if (AbilityListener.checkChakraItem(player, ap.getItem().getName())) {
						if (player.isSneaking()) {

							ap.RightPlusShift(player, pl);
						} else {

							ap.RightClick(player, pl);
						}
						break;
					}


				}

			}

		}
	}
	@EventHandler
	public void playerInteract(final PlayerInteractEvent e) {
		final Player player = e.getPlayer();

		if (player.getInventory().getItemInMainHand() != null && player.getInventory().getItemInMainHand().hasItemMeta()
				&& player.getInventory().getItemInMainHand().getItemMeta().hasDisplayName()
				&& player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().startsWith("§7[§6Naruto§7]")
				&& e.getPlayer().getMainHand() != null && !e.getPlayer().getMainHand().equals(EquipmentSlot.OFF_HAND)) {

			if (haveCooldownPlayer(e.getPlayer().getName())) return;

			if (hasPvpZone((Entity) player)) {

				NarutoPlayer pl = NarutoPlayer.getNarutoPlayer(player.getName());
				if(AbilitiesMain.scheduler.containsKey(player)) {
					player.sendMessage("§7[§6Naruto§7] §cВы контроллируете прошлую способку, подождите её действия");
					return;
				}
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

			lent.setVelocity(new Vector());

			lent.setLastDamageCause(finalEvent);
		}

	}
	@EventHandler
	public void onChangeBlockFalling(EntityChangeBlockEvent e) {
		for (FallingBlock f :  StoneBroke.fBlocks) {
			if (f != null) {
				f.remove();
				e.setCancelled(true);
			}
		}
	}
	public static boolean checkChakraItem(Player pl, String name) {

		if (pl.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase(name)) {
			int a = 0;
			if (a == 0) {
				return true;
			}


		}
		return false;
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
