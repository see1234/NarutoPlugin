package minenaruto.narutoplugin.iditems;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import minenaruto.narutoplugin.main.Main;

public class Item {

	private String material_name;
	private int durability;
	private String displayname;
	private List<String> lore;



	public Item(Material material, int durability, String displayname, List<String> lore) {
	    this.material_name = material.name();
		this.durability = durability;
		this.displayname = displayname;
		this.lore = lore;
	}


	public String getName() {
		return displayname;
	}

	public ItemStack getItemStack() {

		ItemStack is = new ItemStack(Material.valueOf(material_name.toUpperCase()), 1, (short)durability);
		ItemMeta im = is.getItemMeta();
		List<String> displaylore = new ArrayList<String>();
		if (this.lore != null) {
			if (this.lore.size() != 0) {
				for (String lorename : this.lore) {
					displaylore.add(lorename.replace("&", "§"));
				}
			}
		}
		im.setLore(displaylore);
		if (displayname != null) {
			im.setDisplayName(displayname.replace("&", "§"));
		}
		im.setUnbreakable(true);
	 	im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		im.addItemFlags(ItemFlag.HIDE_DESTROYS);
		im.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		im.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		im.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		is.setDurability((short) durability);
		is.setItemMeta(im);

		return is;
	}




}
