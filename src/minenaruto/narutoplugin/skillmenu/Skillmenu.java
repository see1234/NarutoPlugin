package minenaruto.narutoplugin.skillmenu;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.Inventory;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;

public class Skillmenu implements Listener {

	private static final String MENU_TITLE_1 = "§f\uE101\uC002";
	private static final String MENU_TITLE_2 = "§f\uE101\uC003";
	private static final String MENU_TITLE_3 = "§f\uE101\uC004";
	private static final String INSUFFICIENT_SKILL_POINTS = "§7[§6Naruto§7] §cУ вас не хватает скиллпоинтов";

	@EventHandler
	public void openMenu(PlayerSwapHandItemsEvent event) {
		event.setCancelled(true);
		openInventory(event.getPlayer(), 1);
	}

	private void openInventory(Player player, int menuType) {
		NarutoPlayer narutoPlayer = NarutoPlayer.getNarutoPlayer(player.getName());
		player.closeInventory();

		Inventory inv;
		switch (menuType) {
			case 1:
				inv = Bukkit.createInventory(null, 54, MENU_TITLE_1 + "§7" +
						narutoPlayer.getLine2(narutoPlayer.getString("level")) +
						narutoPlayer.getLine2("level:") + "                 " +
						narutoPlayer.getLine3(narutoPlayer.getString("exp")) +
						narutoPlayer.getLine3("exp:"));
				break;
			case 2:
				inv = Bukkit.createInventory(null, 54, MENU_TITLE_2 + "§7" +
						narutoPlayer.getNinjustu() + narutoPlayer.getGenjustu() +
						narutoPlayer.getTaijustu() + narutoPlayer.getShurikenjustu() +
						narutoPlayer.getKenjustu());
				break;
			case 3:
				inv = Bukkit.createInventory(null, 54, MENU_TITLE_3 + "§7" +
						narutoPlayer.getSummoning() + narutoPlayer.getKinjustu() +
						narutoPlayer.getMedical() + narutoPlayer.getSpeed() +
						narutoPlayer.getSenjustu());
				break;
			default:
				return;
		}

		player.openInventory(inv);
	}

	private void setParameter(NarutoPlayer player, String param, int count, Player pl) {
		if (player.getInt("skillpoint") >= count) {
			player.setInt(param, player.getInt(param) + count);
			player.setInt("skillpoint", player.getInt("skillpoint") - count);
		} else {
			pl.sendMessage(INSUFFICIENT_SKILL_POINTS);
		}
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {

		if (event.getSlot() == 999 || event.getView().getTitle() == null || event.getClickedInventory() == null ||
				event.getInventory() == null  || !event.getClickedInventory().equals(event.getInventory())) {

			return;
		}

		String title = event.getView().getTitle();
		Player player = (Player) event.getWhoClicked();

		if (title.contains(MENU_TITLE_1)) {


			event.setCancelled(true);
			if (event.getSlot() == 53) {
				openInventory(player, 2);
			}
		} else if (title.contains(MENU_TITLE_2)) {
			NarutoPlayer narutoPlayer = NarutoPlayer.getNarutoPlayer(player.getName());
			handleMenuTwoClick(event, narutoPlayer, player);
		} else if (title.contains(MENU_TITLE_3)) {
			NarutoPlayer narutoPlayer = NarutoPlayer.getNarutoPlayer(player.getName());
			handleMenuThreeClick(event, narutoPlayer, player);
		}
	}

	private void handleMenuTwoClick(InventoryClickEvent event, NarutoPlayer narutoPlayer, Player player) {
		int slot = event.getSlot();
		if (slot == 53) {
			openInventory(player, 3);
		} else if (slot == 45) {
			openInventory(player, 1);
		} else {
			String parameter = getParameterForSlot(slot);
			if (parameter != null) {
				int count = event.isShiftClick() ? 10 : 1;
				setParameter(narutoPlayer, parameter, count, player);
				openInventory(player, 2);
			}
		}
	}

	private void handleMenuThreeClick(InventoryClickEvent event, NarutoPlayer narutoPlayer, Player player) {
		int slot = event.getSlot();
		if (slot == 45) {
			openInventory(player, 2);
		} else {
			String parameter = getParameterForSlot(slot);
			if (parameter != null) {
				int count = event.isShiftClick() ? 10 : 1;
				setParameter(narutoPlayer, parameter, count, player);
				openInventory(player, 3);
			}
		}
	}

	private String getParameterForSlot(int slot) {
		switch (slot) {
			case 0: return "ninjustu";
			case 9: return "taijustu";
			case 18: return "genjustu";
			case 27: return "kenjustu";
			case 36: return "shurikenjustu";
			default: return null;
		}
	}
}