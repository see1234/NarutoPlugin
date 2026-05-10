package minenaruto.narutoplugin.skillmenu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;

import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;

public class SkillListener implements Listener {

	@EventHandler
	public void EventDamager(EntityDamageEvent event) {
		if (event.isCancelled()) {
			return;
		}
		if (event.getEntity() instanceof Player) {
			if (Bukkit.getOnlinePlayers().contains(event.getEntity())) {
				NarutoPlayer player = NarutoPlayer.getNarutoPlayer(event.getEntity().getName());
				player.addHealth(-(event.getDamage()));
				event.setDamage(0);

			}
		}
	}
    @EventHandler
	public void playerdeath(PlayerDeathEvent event) {
		if (Bukkit.getOnlinePlayers().contains(event.getEntity())) {
			NarutoPlayer player = NarutoPlayer.getNarutoPlayer(event.getEntity().getName());
			player.setHealth(player.getDouble("medical") + 20);
		}
	}
	@EventHandler
	public void ExceptClickMenu(PlayerInteractEvent event) {
		if(event.getPlayer().getInventory() == null) {
			return;
		}
		if(event.getPlayer().getInventory().getItemInMainHand() == null) {
			return;
		}
		if(event.getPlayer().getInventory().getItemInMainHand().getType() != Material.ENCHANTED_BOOK) {
			return;
		}
		if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta() == null) {
			return;
		}
		if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName() == null) {
			return;
		}
		if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("Меню")) {
			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), String.format("sudo %s menu", event.getPlayer().getName()));
		}
	}

	@EventHandler
	public void UnbreakingDiamondHoe(PlayerInteractEvent event) {
		if(event.getPlayer().getInventory() == null) {
			return;
		}
		if(event.getPlayer().getInventory().getItemInMainHand() == null) {
			return;
		}
		if(event.getPlayer().getInventory().getItemInMainHand().getType() != Material.DIAMOND_HOE) {
			return;
		}
		if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta() == null) {
			return;
		}
		event.getPlayer().getInventory().getItemInMainHand().getItemMeta().setUnbreakable(true);
	}
	@EventHandler
	public void PlayerDeath(PlayerDeathEvent event) {
		event.getEntity().spigot().respawn();
	}

	@EventHandler
	public void RespawnEvent(PlayerRespawnEvent event) {
 

			if (Bukkit.getOnlinePlayers().contains(event.getPlayer())) {
				NarutoPlayer player = NarutoPlayer.getNarutoPlayer(event.getPlayer().getName());
				player.setHealth(player.getDouble("medical") + 20);
		}
		
	}
	@EventHandler
	public void onRegeneration(final EntityRegainHealthEvent event) {

		final Entity ent = event.getEntity();
		if (ent instanceof Player) {
			event.setCancelled(true);
			 
			NarutoPlayer player = NarutoPlayer.getNarutoPlayer(event.getEntity().getName());
            player.addHealth(event.getAmount());
		 
		}

	}

}
