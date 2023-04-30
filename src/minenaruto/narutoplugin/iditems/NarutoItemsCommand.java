package minenaruto.narutoplugin.iditems;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.main.Main;
import minenaruto.narutoplugin.swords.SwordMain;
import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.*;

import io.netty.util.internal.ThreadLocalRandom;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;

public class NarutoItemsCommand extends EasyCommand {

	public NarutoItemsCommand() {
		super("narutois", "Использование: /narutois", "narutodb", "Naruto.admin",
				Arrays.asList("narutoitem", "narutoitems"));
	}

	@Override
	public boolean onCommand(final CommandSender s, final Command cmd, final String label, final String[] args) {
		if (s instanceof Player) {
			Player p = (Player) s;
			NarutoPlayer np = NarutoPlayer.getNarutoPlayer(s.getName());
			if(args.length == 0) {
				np.sendMessage("&7Помощь по команде:");
				np.sendMessage("&7/" + cmd.getName() + " help - чекнуть список команд");
				np.sendMessage("&7/" + cmd.getName() + " list - список игровых предметов");
				np.sendMessage("&7/" + cmd.getName() + " give <player> <id> - выдать игроку предмет");
				np.sendMessage("&7/" + cmd.getName() + " item <id> - выдать предмет");
				return true;
			}
			switch (args[0]) {
				case "list":
					np.sendMessage("&7Список игровых предметов:");
					int id = 0;
					for(AbilitiesMain ability : Main.getInstance().getAbilities()) {
						String displayname = ability.getItem().getItemStack().getItemMeta().getDisplayName();

						np.sendMessage("&7ID: &4" + id + " &7- &8[" + displayname + "&8]");
						id++;
						//
					}
					for(SwordMain sword : Main.getInstance().getSwords()) {
						String displayname = sword.getItem().getItemStack().getItemMeta().getDisplayName();

						np.sendMessage("&7ID: &4" + id + " &7- &8[" + displayname + "&8]");
						id++;
						//
					}
					break;
				case "give":
					if(args.length >= 3) {
						int i = 0;
						for(AbilitiesMain ability : Main.getInstance().getAbilities()) {
							if(i == Integer.parseInt(args[2])) {
								Bukkit.getPlayer(args[1]).getInventory().addItem(ability.getItem().getItemStack());
								i=-1;
								break;
							}

							i++;
							//
						}
						for(SwordMain swordMain : Main.getInstance().getSwords()) {
							if(i == -1) {
								break;
							}
							if(i == Integer.parseInt(args[2])) {
								Bukkit.getPlayer(args[1]).getInventory().addItem(swordMain.getItem().getItemStack());
								break;
							}

							i++;
							//
						}

					}
					else {
						np.sendMessage("&7/" + cmd.getName() + " give <player> <id> - выдать игроку предмет");
					}
					break;
				case "item":
					if(args.length >= 2) {

						int i = 0;
						for(AbilitiesMain ability : Main.getInstance().getAbilities()) {
							if(i == Integer.parseInt(args[1])) {
								p.getInventory().addItem(ability.getItem().getItemStack());
								i = -1;
								break;
							}

							i++;
							//
						}
						for(SwordMain sword : Main.getInstance().getSwords()) {
							if(i == -1) {
								break;
							}
							if(i == Integer.parseInt(args[1])) {
								p.getInventory().addItem(sword.getItem().getItemStack());
								break;
							}

							i++;
							//
						}
					}
					else {
						np.sendMessage("&7/" + cmd.getName() + " item <id> - выдать предмет");
					}
					break;
				default:
					np.sendMessage("&7Помощь по команде:");
					np.sendMessage("&7/" + cmd.getName() + " help - чекнуть список команд");
					np.sendMessage("&7/" + cmd.getName() + " list - список игровых предметов");
					np.sendMessage("&7/" + cmd.getName() + " give <player> <id> - выдать игроку предмет");
					np.sendMessage("&7/" + cmd.getName() + " item <id> - выдать предмет");
					np.sendMessage("&7/" + cmd.getName()
							+ " additem <id> - добавить предмет по айди, либо заменить (держать в руке предмет)");
					break;
			}
			return true;
		}
		s.sendMessage("&7User, join in game, and input command".replace("&", "§"));
		return true;
	}
}
