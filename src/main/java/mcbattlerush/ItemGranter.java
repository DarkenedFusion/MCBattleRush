package mcbattlerush;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemGranter implements CommandExecutor, TabCompleter {

	private ItemDictionary itemDictionary = new ItemDictionary();

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("grant")) {
			Player player = null;

			if (sender instanceof Player) {
				player = (Player) sender;
				if (!player.isOp()) {
					player.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
					return true;
				}
			}

			Player targetPlayer;
			String itemName;
			int amount = 1; // Default amount is 1 if not specified

			if (args.length >= 2) {
				itemName = args[0].toLowerCase();
				targetPlayer = Bukkit.getPlayer(args[args.length - 1]);

				if (targetPlayer == null) {
					sender.sendMessage(ChatColor.RED + "The specified target player is not online!");
					return true;
				}

				if (args.length >= 3) {
					try {
						amount = Integer.parseInt(args[1]);
					} catch (NumberFormatException e) {
						sender.sendMessage(ChatColor.RED + "Invalid amount specified!");
						return true;
					}
				}
			} else {
				if (player == null) {
					sender.sendMessage(
							ChatColor.RED + "Please provide both the item name and the target player's name!");
					return true;
				}
				itemName = args[0].toLowerCase();
				targetPlayer = player;
			}

			switch (itemName) {
			//MAKE SURE ALL NAMES ARE LOWERCASE
			case "redhelmet":
				giveItem(targetPlayer, itemDictionary.redHelmet(), amount);
				break;
			case "redchestplate":
				giveItem(targetPlayer, itemDictionary.redChestplate(), amount);
				break;
			case "redleggings":
				giveItem(targetPlayer, itemDictionary.redLeggings(), amount);
				break;
			case "redboots":
				giveItem(targetPlayer, itemDictionary.redBoots(), amount);
				break;
			case "bluehelmet":
				giveItem(targetPlayer, itemDictionary.blueHelmet(), amount);
				break;
			case "bluechestplate":
				giveItem(targetPlayer, itemDictionary.blueChestplate(), amount);
				break;
			case "blueleggings":
				giveItem(targetPlayer, itemDictionary.blueLeggings(), amount);
				break;
			case "blueboots":
				giveItem(targetPlayer, itemDictionary.blueBoots(), amount);
				break;
			case "classstar":
				giveItem(targetPlayer, itemDictionary.classStar(), amount);
				break;
			case "firecore":
				giveItem(targetPlayer, itemDictionary.fireCore(), amount);
				break;
			case "basicsword":
				giveItem(targetPlayer, itemDictionary.basicSword(), amount);
				break;
			case "rocketeercore":
				giveItem(targetPlayer, itemDictionary.rocketeerCore(), amount);
				break;
			case "thundercore":
				giveItem(targetPlayer, itemDictionary.thunderCore(), amount);
				break;
			case "necromancercore":
				giveItem(targetPlayer, itemDictionary.necromancerCore(), amount);
				break;
			case "icecore":
				giveItem(targetPlayer, itemDictionary.iceCore(), amount);
				break;
			case "airtoken":
				giveItem(targetPlayer, itemDictionary.airToken(), amount);
				break;
			case "firetoken":
				giveItem(targetPlayer, itemDictionary.fireToken(), amount);
				break;
			case "earthtoken":
				giveItem(targetPlayer, itemDictionary.earthToken(), amount);
				break;
			case "rocketeertoken":
				giveItem(targetPlayer, itemDictionary.rocketeerToken(), amount);
				break;
			case "thundertoken":
				giveItem(targetPlayer, itemDictionary.thunderToken(), amount);
				break;
			case "icetoken":
				giveItem(targetPlayer, itemDictionary.iceToken(), amount);
				break;
			case "necrotoken":
				giveItem(targetPlayer, itemDictionary.necromancerToken(), amount);
				break;
			default:
				sender.sendMessage(ChatColor.RED + "Invalid item name!");
				return true;
			}

			String senderName = (player != null) ? player.getName() : "Console";
			sender.sendMessage(ChatColor.GREEN + "You have granted " + amount + " " + itemName + " to "
					+ targetPlayer.getName() + " as " + senderName + ".");
			return true;
		}

		return false;
	}

	private void giveItem(Player player, ItemStack itemStack, int amount) {
		ItemStack clonedItem = itemStack.clone();
		clonedItem.setAmount(amount);
		player.getInventory().addItem(clonedItem);
	}

	public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
		List<String> completions = new ArrayList<>();
		if (args.length == 1 && sender instanceof Player && sender.isOp()) {
			completions.add("redHelmet");
			completions.add("redChestplate");
			completions.add("redLeggings");
			completions.add("redBoots");
			completions.add("blueHelmet");
			completions.add("blueChestplate");
			completions.add("blueLeggings");
			completions.add("blueBoots");
			completions.add("classStar");
			completions.add("fireCore");
			completions.add("basicSword");
			completions.add("rocketeerCore");
			completions.add("thunderCore");
			completions.add("necromancerCore");
			completions.add("iceCore");
			completions.add("airToken");
			completions.add("fireToken");
			completions.add("earthToken");
			completions.add("rocketeerToken");
			completions.add("thunderToken");
			completions.add("iceToken");
			completions.add("necroToken");
			









		} else if (args.length == 3 && sender instanceof Player && sender.isOp()) {
			String partialName = args[args.length - 1].toLowerCase();
			for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
				if (onlinePlayer.getName().toLowerCase().startsWith(partialName)) {
					completions.add(onlinePlayer.getName());
				}
			}
		}
		return completions;
	}
}
