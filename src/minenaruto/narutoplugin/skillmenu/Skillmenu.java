package minenaruto.narutoplugin.skillmenu;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.Inventory;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.main.Main;

public class Skillmenu implements Listener {

	@EventHandler
	public void openMenu(PlayerSwapHandItemsEvent event) {

		onInv(event.getPlayer());
		event.setCancelled(true);
	}
	public void onInv(Player p) {
		NarutoPlayer player = NarutoPlayer.getNarutoPlayer(p.getName());
		p.closeInventory();
		Inventory inv = Bukkit.createInventory(null, 54, "§f\uE101\uC002" + "§7" + player.getLine2(player.getString("level")) + player.getLine2("level:") + "                 " + player.getLine3(player.getString("exp")) + player.getLine3("exp:"));

		p.openInventory(inv);
	}
	public void onInvTwo(Player p) {
		NarutoPlayer player = NarutoPlayer.getNarutoPlayer(p.getName());
		p.closeInventory();
		Inventory inv = Bukkit.createInventory(null, 54, "§f\uE101\uC003" + "§7" + player.getNinjustu() + player.getGenjustu() + player.getTaijustu() + player.getShurikenjustu() + player.getKenjustu());

		p.openInventory(inv);
	}
	public void onInvThree(Player p) {
		NarutoPlayer player = NarutoPlayer.getNarutoPlayer(p.getName());
		p.closeInventory();
		Inventory inv = Bukkit.createInventory(null, 54, "§f\uE101\uC004" + "§7" + player.getSummoning() + player.getKinjustu() + player.getMedical() + player.getSpeed() + player.getSenjustu());

		p.openInventory(inv);
	}
	public void setParametr(NarutoPlayer player, String param, int count, Player pl) {
		if(player.getInt("justupoint") >= count) {

			player.setInt(param, player.getInt(param) + count);
			player.setInt("justupoint", player.getInt("justupoint") - count);
		}
		else {
			pl.sendMessage("§7[§6Naruto§7] §cУ вас не хватает джутсупоинтов");
		}

	}
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		if(event.getSlot() == 999) {
			return;
		}
		if(event.getInventory().getTitle() == null) {
			return;
		}
		if(!event.getInventory().getTitle().contains("§f\uE101\uC002") && !event.getInventory().getTitle().contains("§f\uE101\uC003") && !event.getInventory().getTitle().contains("§f\uE101\uC004")) {
			return;
		}
		if(event.getClickedInventory() == null) {
			event.setCancelled(true);
			return;
		}
		if(event.getInventory() == null) {
			event.setCancelled(true);
			return;
		}
		if(!event.getClickedInventory().equals(event.getInventory())) {
			event.setCancelled(true);
			return;
		}

		event.setCancelled(true);
		if(event.getClickedInventory().getTitle().contains("§f\uE101\uC002")) {
			if(event.getSlot() == 53) {
				onInvTwo((Player) event.getWhoClicked());
			}

		}
		if(event.getClickedInventory().getTitle().contains("§f\uE101\uC003")) {
			if(event.getSlot() == 53) {
				onInvThree((Player) event.getWhoClicked());
			}
			if(event.getSlot() == 45) {
				onInv((Player) event.getWhoClicked());
			}
			if(event.getSlot() == 0) {
				NarutoPlayer player = NarutoPlayer.getNarutoPlayer(event.getWhoClicked().getName());
				if(!event.isShiftClick()) {
					setParametr(player, "ninjustu", 1, (Player) event.getWhoClicked());
				}
				else {
					setParametr(player, "ninjustu", 10, (Player) event.getWhoClicked());
				}

				onInvTwo((Player) event.getWhoClicked());
			}
			if(event.getSlot() == 9) {
				NarutoPlayer player = NarutoPlayer.getNarutoPlayer(event.getWhoClicked().getName());
				if(!event.isShiftClick()) {
					setParametr(player, "taijustu", 1, (Player) event.getWhoClicked());
				}
				else {
					setParametr(player, "taijustu", 10, (Player) event.getWhoClicked());
				}
				onInvTwo((Player) event.getWhoClicked());
			}
			if(event.getSlot() == 18) {
				NarutoPlayer player = NarutoPlayer.getNarutoPlayer(event.getWhoClicked().getName());
				if(!event.isShiftClick()) {
					setParametr(player, "genjustu", 1, (Player) event.getWhoClicked());
				}
				else {
					setParametr(player, "genjustu", 10, (Player) event.getWhoClicked());
				}
				onInvTwo((Player) event.getWhoClicked());
			}
			if(event.getSlot() == 27) {
				NarutoPlayer player = NarutoPlayer.getNarutoPlayer(event.getWhoClicked().getName());
				if(!event.isShiftClick()) {
					setParametr(player, "kenjustu", 1, (Player) event.getWhoClicked());
				}
				else {
					setParametr(player, "kenjustu", 10, (Player) event.getWhoClicked());
				}
				onInvTwo((Player) event.getWhoClicked());
			}
			if(event.getSlot() == 36) {
				NarutoPlayer player = NarutoPlayer.getNarutoPlayer(event.getWhoClicked().getName());
				if(!event.isShiftClick()) {
					setParametr(player, "shurikenjustu", 1, (Player) event.getWhoClicked());
				}
				else {
					setParametr(player, "shurikenjustu", 10, (Player) event.getWhoClicked());
				}
				onInvTwo((Player) event.getWhoClicked());
			}
		}
		if(event.getClickedInventory().getTitle().contains("§f\uE101\uC004")) {
			if(event.getSlot() == 45) {
				onInvTwo((Player) event.getWhoClicked());
			}
			if(event.getSlot() == 0) {
				NarutoPlayer player = NarutoPlayer.getNarutoPlayer(event.getWhoClicked().getName());
				if(!event.isShiftClick()) {
					setParametr(player, "summoning", 1, (Player) event.getWhoClicked());
				}
				else {
					setParametr(player, "summoning", 10, (Player) event.getWhoClicked());
				}
				onInvThree((Player) event.getWhoClicked());
			}
			if(event.getSlot() == 9) {
				NarutoPlayer player = NarutoPlayer.getNarutoPlayer(event.getWhoClicked().getName());
				if(!event.isShiftClick()) {
					setParametr(player, "kinjustu", 1, (Player) event.getWhoClicked());
				}
				else {
					setParametr(player, "kinjustu", 10, (Player) event.getWhoClicked());
				}
				onInvThree((Player) event.getWhoClicked());
			}
			if(event.getSlot() == 18) {
				NarutoPlayer player = NarutoPlayer.getNarutoPlayer(event.getWhoClicked().getName());
				if(!event.isShiftClick()) {
					setParametr(player, "senjustu", 1, (Player) event.getWhoClicked());
				}
				else {
					setParametr(player, "senjustu", 10, (Player) event.getWhoClicked());
				}
				onInvThree((Player) event.getWhoClicked());
			}
			if(event.getSlot() == 27) {
				NarutoPlayer player = NarutoPlayer.getNarutoPlayer(event.getWhoClicked().getName());
				if(!event.isShiftClick()) {
					setParametr(player, "medical", 1, (Player) event.getWhoClicked());
				}
				else {
					setParametr(player, "medical", 10, (Player) event.getWhoClicked());
				}
				onInvThree((Player) event.getWhoClicked());
			}
			if(event.getSlot() == 36) {
				NarutoPlayer player = NarutoPlayer.getNarutoPlayer(event.getWhoClicked().getName());
				if(!event.isShiftClick()) {
					setParametr(player, "speed", 1, (Player) event.getWhoClicked());
				}
				else {
					setParametr(player, "speed", 10, (Player) event.getWhoClicked());
				}
				onInvThree((Player) event.getWhoClicked());
			}
		}
	}




}
