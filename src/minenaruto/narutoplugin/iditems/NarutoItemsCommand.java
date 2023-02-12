package minenaruto.narutoplugin.iditems;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.*;

import io.netty.util.internal.ThreadLocalRandom;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;

public class NarutoItemsCommand extends EasyCommand {

	public NarutoItemsCommand() {
		super("narutois", "�������������: /narutois", "narutodb", "Naruto.admin",
				Arrays.asList("narutoitem", "narutoitems"));
	}

	@Override
	public boolean onCommand(final CommandSender s, final Command cmd, final String label, final String[] args) {
		if (s instanceof Player) {
			Player p = (Player) s;
			NarutoPlayer np = NarutoPlayer.getNarutoPlayer(s.getName());
            if(args.length == 0) {
    			np.sendMessage("&7������ �� �������:");
				np.sendMessage("&7/" + cmd.getName() + " help - ������� ������ ������");
				np.sendMessage("&7/" + cmd.getName() + " list - ������ ������� ���������");
				np.sendMessage("&7/" + cmd.getName() + " give <player> <id> - ������ ������ �������");
				np.sendMessage("&7/" + cmd.getName() + " item <id> - ������ �������");
				np.sendMessage("&7/" + cmd.getName()
						+ " additem <id> - �������� ������� �� ����, ���� �������� (������� � ���� �������)");
            	return true;
            }
			switch (args[0]) {
			case "list":
				np.sendMessage("&7������ ������� ���������:");
				Item.items.keySet().forEach(key -> {
					String displayname = Item.items.get(key).getItemStack().getItemMeta().getDisplayName();
					int id = key;
					np.sendMessage("&7ID: &4" + id + " &7- &8[" + displayname + "&8]");
					//
				});
				break;
			case "give":
				if(args.length >= 3) {
				int id = Integer.parseInt(args[2]);
				Bukkit.getPlayer(args[1]).getInventory().addItem(Item.items.get(id).getItemStack());
				}
				else {
					np.sendMessage("&7/" + cmd.getName() + " give <player> <id> - ������ ������ �������");
				}
				break;
			case "item":
				if(args.length >= 2) {
				int iditem = Integer.parseInt(args[1]);
				p.getInventory().addItem(Item.items.get(iditem).getItemStack());
				}
				else {
					np.sendMessage("&7/" + cmd.getName() + " item <id> - ������ �������");
				}
				break;
			case "additem":
				if (args.length == 2) {
					if (Item.items.containsKey(Integer.parseInt(args[1]))) {
						Item.items.remove(Integer.parseInt(args[1]));
					}
					new Item(p.getInventory().getItemInMainHand(), Integer.parseInt(args[1])).save();
					np.sendMessage("&a������� ��� �����������");
				} else {
					new Item(p.getInventory().getItemInMainHand(), -1).addIteminDB();
					np.sendMessage("&a������� ��� �������� � ����");
				}
				break;
			default:
				np.sendMessage("&7������ �� �������:");
				np.sendMessage("&7/" + cmd.getName() + " help - ������� ������ ������");
				np.sendMessage("&7/" + cmd.getName() + " list - ������ ������� ���������");
				np.sendMessage("&7/" + cmd.getName() + " give <player> <id> - ������ ������ �������");
				np.sendMessage("&7/" + cmd.getName() + " item <id> - ������ �������");
				np.sendMessage("&7/" + cmd.getName()
						+ " additem <id> - �������� ������� �� ����, ���� �������� (������� � ���� �������)");
				break;
			}
			return true;
		}
		s.sendMessage("&7User, join in game, and input command".replace("&", "�"));
		return true;
	}
}
