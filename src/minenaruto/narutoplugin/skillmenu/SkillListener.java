package minenaruto.narutoplugin.skillmenu;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
 
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
				System.out.println(player.getDouble("medical") );
				System.out.println(player.getDouble("health") );
				System.out.println(((Player) event.getEntity()).getHealth() );
				player.setDouble("health", player.getDouble("health") - ((double) event.getDamage()));
				double healthmax = player.getDouble("medical") + 20;
				double health = player.getDouble("health") <= 0 ? 0 : player.getDouble("health");
				((Player) event.getEntity()).setHealth((((double) health / (double) healthmax) * 20.0));
				event.setDamage(0);

			}
		}
	}
	@EventHandler
	public void RespawnEvent(org.bukkit.event.entity.PlayerDeathEvent event) {
 
		if (event.getEntity() instanceof Player) {
			if (Bukkit.getOnlinePlayers().contains(event.getEntity())) {
				NarutoPlayer player = NarutoPlayer.getNarutoPlayer(event.getEntity().getName());

				player.setDouble("health", player.getDouble("medical") + 20);
				((Player) event.getEntity()).setHealth(20.0);


			}
		}
		
	}
	@EventHandler
	public void onRegeneration(final EntityRegainHealthEvent event) {

		final Entity ent = event.getEntity();
		if (ent instanceof Player) {
			event.setCancelled(true);
			 
			NarutoPlayer player = NarutoPlayer.getNarutoPlayer(event.getEntity().getName());
 
			player.setDouble("health", player.getDouble("health") + ((double) event.getAmount()));
			double healthmax = player.getDouble("medical") + 20;
			double health = player.getDouble("health") > healthmax ? healthmax : player.getDouble("health");
			((Player) event.getEntity()).setHealth((health / healthmax) * 20);
		 
		}

	}

}
