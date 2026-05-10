package minenaruto.narutoplugin.dataplayer;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.PolarBear;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import minenaruto.narutoplugin.main.Main;

public class PlayerListener implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		event.setJoinMessage(null);
		if(!event.getPlayer().hasPlayedBefore()) {
			event.getPlayer().getInventory().addItem(Main.getInstance().getAbilities().get(0).getItem().getItemStack());
		}
		NarutoPlayer np = new NarutoPlayer(event.getPlayer().getName());

		np.load();

	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		event.setQuitMessage(null);
		NarutoPlayer np = NarutoPlayer.getNarutoPlayer(event.getPlayer().getName());
		np.unload();
		event.getPlayer().setAllowFlight(false);
		BossBar bossBar = Chakra.chakraBar.get(event.getPlayer().getName());
		bossBar.removePlayer(event.getPlayer());
		bossBar.removeAll();
		Chakra.chakraBar.remove(event.getPlayer().getName());
	}

	@EventHandler
	public void ArmorStand(PlayerArmorStandManipulateEvent e) {
		if(e.getRightClicked()  instanceof ArmorStand) {
			 if(((ArmorStand) e.getRightClicked()).isInvisible()) {
				 e.setCancelled(true);

			 }

		}
	}
}
