package minenaruto.narutoplugin.iditems;

import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.main.Main;
import minenaruto.narutoplugin.swords.SwordMain;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NarutoItemsCommand extends EasyCommand {

	public NarutoItemsCommand() {
		super("narutois",
				"Использование: /narutois",
				"narutodb",
				"Naruto.admin",
				Arrays.asList("narutoitem", "narutoitems")
		);
	}

	@Override
	public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("§cВы должны быть игроком, чтобы использовать эту команду.");
			return true;
		}

		Player player = (Player) sender;
		NarutoPlayer narutoPlayer = NarutoPlayer.getNarutoPlayer(player.getName());

		if (args.length == 0) {
			sendHelpMessage(narutoPlayer, command);
			return true;
		}

		switch (args[0].toLowerCase()) {

			case "list":
				listItems(narutoPlayer);
				break;

			case "give":
				if (args.length >= 3) {
					giveItemToPlayer(args[1], args[2], narutoPlayer);
				} else {
					narutoPlayer.sendMessage("§cИспользование: /" + command.getName() + " give <игрок> <id>");
				}
				break;
			case "inv":
				if (args.length >= 2) {
					createInventory(player, Integer.parseInt(args[1]));
				} else {
					narutoPlayer.sendMessage("&7/" + command.getName() + " inventory <id> - меню способок");
				}
				break;
			case "item":
				if (args.length >= 2) {
					giveItemToPlayer(player.getName(), args[1], narutoPlayer);
				} else {
					narutoPlayer.sendMessage("§cИспользование: /" + command.getName() + " item <id>");
				}
				break;
			case "test":
				narutoPlayer.sendMessage(String.valueOf(player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData()));
				break;
			default:
				sendHelpMessage(narutoPlayer, command);
				break;
		}

		return true;
	}

	/**
	 * Отправляет игроку сообщение с помощью по команде.
	 *
	 * @param narutoPlayer Игрок, которому отправляется сообщение.
	 * @param command      Команда, для которой выводится справка.
	 */
	private void sendHelpMessage(NarutoPlayer narutoPlayer, Command command) {
		narutoPlayer.sendMessage("§7Помощь по команде:");
		narutoPlayer.sendMessage("§7/" + command.getName() + " help - список команд");
		narutoPlayer.sendMessage("§7/" + command.getName() + " list - список игровых предметов");
		narutoPlayer.sendMessage("&7/" + command.getName() + " inv <id> - меню игровых предметов");
		narutoPlayer.sendMessage("§7/" + command.getName() + " give <игрок> <id> - выдать предмет игроку");
		narutoPlayer.sendMessage("§7/" + command.getName() + " item <id> - выдать предмет себе");

	}


	public void createInventory(Player p,int c) {
		Inventory inv = Bukkit.createInventory(null, 54, "меню");
		for(int i = 54*(c-1); i < 54*c;i++) {
			if(getItemById(i) != null) {
				inv.addItem(getItemById(i));
			}
		}
		p.openInventory(inv);

	}
	/**
	 * Отображает список всех доступных предметов.
	 *
	 * @param narutoPlayer Игрок, которому отправляется список.
	 */
	private void listItems(NarutoPlayer narutoPlayer) {
		narutoPlayer.sendMessage("§7Список игровых предметов:");

		int id = 0;
		for (AbilitiesMain ability : Main.getInstance().getAbilities()) {
			String displayName = ability.getItem().getItemStack().getItemMeta().getDisplayName();
			narutoPlayer.sendMessage("§7ID: §4" + id + " §7- §8[" + displayName + "§8]");
			id++;
		}

		for (SwordMain sword : Main.getInstance().getSwords()) {
			String displayName = sword.getItem().getItemStack().getItemMeta().getDisplayName();
			narutoPlayer.sendMessage("§7ID: §4" + id + " §7- §8[" + displayName + "§8]");
			id++;
		}
	}

	/**
	 * Выдает предмет игроку по ID.
	 *
	 * @param targetPlayer Имя игрока, которому выдается предмет.
	 * @param itemId       ID предмета.
	 * @param narutoPlayer Игрок, который инициировал команду.
	 */
	private void giveItemToPlayer(String targetPlayer, String itemId, NarutoPlayer narutoPlayer) {
		Player target = Bukkit.getPlayer(targetPlayer);
		if (target == null) {
			narutoPlayer.sendMessage("§cИгрок не найден.");
			return;
		}

		try {
			int id = Integer.parseInt(itemId);
			ItemStack item = getItemById(id);

			if (item != null) {
				target.getInventory().addItem(item);
				narutoPlayer.sendMessage("§aПредмет успешно выдан игроку " + target.getName() + ".");
			} else {
				narutoPlayer.sendMessage("§cПредмет с ID " + id + " не найден.");
			}
		} catch (NumberFormatException e) {
			narutoPlayer.sendMessage("§cID предмета должен быть числом.");
		}
	}

	/**
	 * Возвращает предмет по его ID.
	 *
	 * @param id ID предмета.
	 * @return Предмет или null, если предмет не найден.
	 */

	private ItemStack getItemById(int id) {
		int currentId = 0;

		for (AbilitiesMain ability : Main.getInstance().getAbilities()) {
			if (currentId == id) {
				return ability.getItem().getItemStack();
			}
			currentId++;
		}

		for (SwordMain sword : Main.getInstance().getSwords()) {
			if (currentId == id) {
				return sword.getItem().getItemStack();
			}
			currentId++;
		}

		return null;
	}
}