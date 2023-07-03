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
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
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
		double healthmax = np.getDouble("medical") + 20;
		double health = np.getDouble("health") <= 0 ? 0 : np.getDouble("health");
		if(((double) health / (double) healthmax) * 20.0 >= 20.0) {
			((Player) event.getPlayer()).setHealth(20.0);
		}
		else {
			((Player) event.getPlayer()).setHealth(((double) health / (double) healthmax) * 20.0);
		}
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
	public void onInteract(final PlayerInteractEvent e) {
		if (e.getItem() == null) {
			return;
		}
		if (e.getItem().getType() == Material.DIAMOND_HOE && e.getItem().getType().getMaxDurability() > 16 && !e.getItem().getItemMeta().isUnbreakable()) {
			final ItemStack itemStack = e.getItem();
			final ItemMeta itemMeta = itemStack.getItemMeta();
					itemMeta.setUnbreakable(true);
					itemStack.setDurability((short)0);

			itemStack.setItemMeta(itemMeta);
			e.getPlayer().updateInventory();
		}
	}
}
