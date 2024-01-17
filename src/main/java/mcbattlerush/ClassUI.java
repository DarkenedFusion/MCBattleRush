package mcbattlerush;

import java.util.Arrays;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ClassUI implements Listener {

	private final Inventory inv;

	private ItemDictionary itemDictionary = new ItemDictionary();
	private UtilityMethods utilityMethods = new UtilityMethods();

	// Hashmap to store specific class types within.

	public ClassUI() {
		inv = Bukkit.createInventory(null, 54, "Classes");

		// Put the items into the inventory
		initializeClassItems();
	}

	public void initializeClassItems() {
		for (int i = 0; i < inv.getSize(); i++) {
			inv.setItem(i, createGuiItem(Material.GRAY_STAINED_GLASS_PANE, "", ""));

			if (i == 10) {
				inv.setItem(i, createGuiItem(Material.LIGHT_GRAY_DYE, ChatColor.WHITE + "Air Class",
						ChatColor.YELLOW + "Type: " + ChatColor.DARK_GRAY + "Runner"));
			}

			if (i == 11) {
				inv.setItem(i, createGuiItem(Material.ORANGE_DYE, ChatColor.WHITE + "Fire Class",
						ChatColor.YELLOW + "Type: " + ChatColor.DARK_GRAY + "Attacker"));

			}
			if (i == 12) {
				inv.setItem(i, createGuiItem(Material.BROWN_DYE, ChatColor.WHITE + "Earth Class",
						ChatColor.YELLOW + "Type: " + ChatColor.DARK_GRAY + "Defender"));

			}
			if (i == 13) {
				inv.setItem(i, createGuiItem(Material.SPYGLASS, ChatColor.WHITE + "Rocketeer Class",
						ChatColor.YELLOW + "Type: " + ChatColor.DARK_GRAY + "Runner"));
			}
			if (i == 14) {
				inv.setItem(i, createGuiItem(Material.YELLOW_DYE, ChatColor.WHITE + "Thunder Class",
						ChatColor.YELLOW + "Type: " + ChatColor.DARK_GRAY + "Attacker"));
			}
			if (i == 15) {
				inv.setItem(i, createGuiItem(Material.LIGHT_BLUE_DYE, ChatColor.WHITE + "Ice Class",
						ChatColor.YELLOW + "Type: " + ChatColor.DARK_GRAY + "Defender"));
			}
			if (i == 16) {
				inv.setItem(i, createGuiItem(Material.BLACK_DYE, ChatColor.WHITE + "Necromancer Class",
						ChatColor.YELLOW + "Type: " + ChatColor.DARK_GRAY + "Defender"));
			}
		}
	}

	protected ItemStack createGuiItem(final Material material, final String name, final String... lore) {
		final ItemStack item = new ItemStack(material, 1);
		final ItemMeta meta = item.getItemMeta();

		// Set the name of the item
		meta.setDisplayName(name);

		// Set the lore of the item
		meta.setLore(Arrays.asList(lore));

		item.setItemMeta(meta);

		return item;
	}

	public void openInventory(final HumanEntity ent) {
		ent.openInventory(inv);
	}

	@EventHandler
	public void onInventoryClick(final InventoryClickEvent e) {
		if (!e.getInventory().equals(inv))
			return;

		e.setCancelled(true);

		final ItemStack clickedItem = e.getCurrentItem();

		if (clickedItem == null || clickedItem.getType().isAir())
			return;

		final Player player = (Player) e.getWhoClicked();

		if (e.getSlot() == 10) {
			// AIR CLASS
			if (player.hasPermission("mcbr.air") || player.isOp()) {

				if (player.hasPermission("mcbr.attacker")) {
					utilityMethods.takePlayerNode(player, "mcbr.attacker");
				}
				if (player.hasPermission("mcbr.defender")) {
					utilityMethods.takePlayerNode(player, "mcbr.defender");
				}
				if (player.hasPermission("mcbr.runner")) {
					utilityMethods.takePlayerNode(player, "mcbr.runner");
				}

				// Removes any typing from the player beforehand
				player.getInventory().clear();
				player.getInventory().setItem(8, itemDictionary.classStar());

				utilityMethods.givePlayerNode(player, "mcbr.runner");

				// give air items

				if (Teams.isInTeam(player)) {
					if (Teams.getTeamType(player) == TeamType.REDTEAM) {
						player.getInventory().setHelmet(itemDictionary.redHelmet());
						player.getInventory().setChestplate(itemDictionary.redChestplate());
						player.getInventory().setLeggings(itemDictionary.redLeggings());
						player.getInventory().setBoots(itemDictionary.redBoots());
					} else if (Teams.getTeamType(player) == TeamType.BLUETEAM) {
						player.getInventory().setHelmet(itemDictionary.blueHelmet());
						player.getInventory().setChestplate(itemDictionary.blueChestplate());
						player.getInventory().setLeggings(itemDictionary.blueLeggings());
						player.getInventory().setBoots(itemDictionary.blueBoots());
					}
				}

				player.getInventory().setItem(0, itemDictionary.basicSword());
				player.getInventory().setItem(1, itemDictionary.airCore());

				player.closeInventory();

			} else {
				player.sendMessage(ChatColor.RED + "You do not have this class unlocked!");
			}
		}

		if (e.getSlot() == 11) {
			// FIRE CLASS
			if (player.hasPermission("mcbr.fire") || player.isOp()) {

				if (player.hasPermission("mcbr.attacker")) {
					utilityMethods.takePlayerNode(player, "mcbr.attacker");
				}
				if (player.hasPermission("mcbr.defender")) {
					utilityMethods.takePlayerNode(player, "mcbr.defender");
				}
				if (player.hasPermission("mcbr.runner")) {
					utilityMethods.takePlayerNode(player, "mcbr.runner");
				}

				// Removes any typing from the player beforehand
				player.getInventory().clear();
				player.getInventory().setItem(8, itemDictionary.classStar());

				utilityMethods.givePlayerNode(player, "mcbr.attacker");

				// give fire items
				if (Teams.isInTeam(player)) {
					if (Teams.getTeamType(player) == TeamType.REDTEAM) {
						player.getInventory().setHelmet(itemDictionary.redHelmet());
						player.getInventory().setChestplate(itemDictionary.redChestplate());
						player.getInventory().setLeggings(itemDictionary.redLeggings());
						player.getInventory().setBoots(itemDictionary.redBoots());
					} else if (Teams.getTeamType(player) == TeamType.BLUETEAM) {
						player.getInventory().setHelmet(itemDictionary.blueHelmet());
						player.getInventory().setChestplate(itemDictionary.blueChestplate());
						player.getInventory().setLeggings(itemDictionary.blueLeggings());
						player.getInventory().setBoots(itemDictionary.blueBoots());
					}
				}

				player.getInventory().setItem(0, itemDictionary.basicSword());
				player.getInventory().setItem(1, itemDictionary.fireCore());

				player.closeInventory();

			} else {
				player.sendMessage(ChatColor.RED + "You do not have this class unlocked!");
			}
		}

		if (e.getSlot() == 12) {
			// EARTH CLASS
			if (player.hasPermission("mcbr.earth") || player.isOp()) {

				if (player.hasPermission("mcbr.attacker")) {
					utilityMethods.takePlayerNode(player, "mcbr.attacker");
				}
				if (player.hasPermission("mcbr.defender")) {
					utilityMethods.takePlayerNode(player, "mcbr.defender");
				}
				if (player.hasPermission("mcbr.runner")) {
					utilityMethods.takePlayerNode(player, "mcbr.runner");
				}

				// Removes any typing from the player beforehand
				player.getInventory().clear();
				player.getInventory().setItem(8, itemDictionary.classStar());

				utilityMethods.givePlayerNode(player, "mcbr.defender");

				// give earth items
				if (Teams.isInTeam(player)) {
					if (Teams.getTeamType(player) == TeamType.REDTEAM) {
						player.getInventory().setHelmet(itemDictionary.redHelmet());
						player.getInventory().setChestplate(itemDictionary.redChestplate());
						player.getInventory().setLeggings(itemDictionary.redLeggings());
						player.getInventory().setBoots(itemDictionary.redBoots());
					} else if (Teams.getTeamType(player) == TeamType.BLUETEAM) {
						player.getInventory().setHelmet(itemDictionary.blueHelmet());
						player.getInventory().setChestplate(itemDictionary.blueChestplate());
						player.getInventory().setLeggings(itemDictionary.blueLeggings());
						player.getInventory().setBoots(itemDictionary.blueBoots());
					}
				}
				player.getInventory().setItem(0, itemDictionary.basicSword());
				player.getInventory().setItem(1, itemDictionary.earthCore());

				player.closeInventory();

			} else {
				player.sendMessage(ChatColor.RED + "You do not have this class unlocked!");
			}
		}
		if (e.getSlot() == 13) {
			// Rocketeer CLASS
			if (player.hasPermission("mcbr.rocketeer") || player.isOp()) {

				if (player.hasPermission("mcbr.attacker")) {
					utilityMethods.takePlayerNode(player, "mcbr.attacker");
				}
				if (player.hasPermission("mcbr.defender")) {
					utilityMethods.takePlayerNode(player, "mcbr.defender");
				}
				if (player.hasPermission("mcbr.runner")) {
					utilityMethods.takePlayerNode(player, "mcbr.runner");
				}

				// Removes any typing from the player beforehand
				player.getInventory().clear();
				player.getInventory().setItem(8, itemDictionary.classStar());

				utilityMethods.givePlayerNode(player, "mcbr.runner");

				// give air items

				if (Teams.isInTeam(player)) {
					if (Teams.getTeamType(player) == TeamType.REDTEAM) {
						player.getInventory().setHelmet(itemDictionary.redHelmet());
						player.getInventory().setChestplate(itemDictionary.redChestplate());
						player.getInventory().setLeggings(itemDictionary.redLeggings());
						player.getInventory().setBoots(itemDictionary.redBoots());
					} else if (Teams.getTeamType(player) == TeamType.BLUETEAM) {
						player.getInventory().setHelmet(itemDictionary.blueHelmet());
						player.getInventory().setChestplate(itemDictionary.blueChestplate());
						player.getInventory().setLeggings(itemDictionary.blueLeggings());
						player.getInventory().setBoots(itemDictionary.blueBoots());
					}
				}

				player.getInventory().setItem(0, itemDictionary.basicSword());
				player.getInventory().setItem(1, itemDictionary.rocketeerCore());

				player.closeInventory();

			} else {
				player.sendMessage(ChatColor.RED + "You do not have this class unlocked!");
			}
		}
		if (e.getSlot() == 14) {
			// Thunder CLASS
			if (player.hasPermission("mcbr.thunder") || player.isOp()) {

				if (player.hasPermission("mcbr.attacker")) {
					utilityMethods.takePlayerNode(player, "mcbr.attacker");
				}
				if (player.hasPermission("mcbr.defender")) {
					utilityMethods.takePlayerNode(player, "mcbr.defender");
				}
				if (player.hasPermission("mcbr.runner")) {
					utilityMethods.takePlayerNode(player, "mcbr.runner");
				}

				// Removes any typing from the player beforehand
				player.getInventory().clear();
				player.getInventory().setItem(8, itemDictionary.classStar());

				utilityMethods.givePlayerNode(player, "mcbr.attacker");

				// give air items

				if (Teams.isInTeam(player)) {
					if (Teams.getTeamType(player) == TeamType.REDTEAM) {
						player.getInventory().setHelmet(itemDictionary.redHelmet());
						player.getInventory().setChestplate(itemDictionary.redChestplate());
						player.getInventory().setLeggings(itemDictionary.redLeggings());
						player.getInventory().setBoots(itemDictionary.redBoots());
					} else if (Teams.getTeamType(player) == TeamType.BLUETEAM) {
						player.getInventory().setHelmet(itemDictionary.blueHelmet());
						player.getInventory().setChestplate(itemDictionary.blueChestplate());
						player.getInventory().setLeggings(itemDictionary.blueLeggings());
						player.getInventory().setBoots(itemDictionary.blueBoots());
					}
				}

				player.getInventory().setItem(0, itemDictionary.basicSword());
				player.getInventory().setItem(1, itemDictionary.thunderCore());

				player.closeInventory();

			} else {
				player.sendMessage(ChatColor.RED + "You do not have this class unlocked!");
			}
		}
		if (e.getSlot() == 15) {
			// Ice CLASS
			if (player.hasPermission("mcbr.ice") || player.isOp()) {

				if (player.hasPermission("mcbr.attacker")) {
					utilityMethods.takePlayerNode(player, "mcbr.attacker");
				}
				if (player.hasPermission("mcbr.defender")) {
					utilityMethods.takePlayerNode(player, "mcbr.defender");
				}
				if (player.hasPermission("mcbr.runner")) {
					utilityMethods.takePlayerNode(player, "mcbr.runner");
				}

				// Removes any typing from the player beforehand
				player.getInventory().clear();
				player.getInventory().setItem(8, itemDictionary.classStar());

				utilityMethods.givePlayerNode(player, "mcbr.defender");

				// give air items

				if (Teams.isInTeam(player)) {
					if (Teams.getTeamType(player) == TeamType.REDTEAM) {
						player.getInventory().setHelmet(itemDictionary.redHelmet());
						player.getInventory().setChestplate(itemDictionary.redChestplate());
						player.getInventory().setLeggings(itemDictionary.redLeggings());
						player.getInventory().setBoots(itemDictionary.redBoots());
					} else if (Teams.getTeamType(player) == TeamType.BLUETEAM) {
						player.getInventory().setHelmet(itemDictionary.blueHelmet());
						player.getInventory().setChestplate(itemDictionary.blueChestplate());
						player.getInventory().setLeggings(itemDictionary.blueLeggings());
						player.getInventory().setBoots(itemDictionary.blueBoots());
					}
				}

				player.getInventory().setItem(0, itemDictionary.basicSword());
				player.getInventory().setItem(1, itemDictionary.iceCore());

				player.closeInventory();

			} else {
				player.sendMessage(ChatColor.RED + "You do not have this class unlocked!");
			}
		}
		if (e.getSlot() == 16) {
			// Necromancer CLASS
			if (player.hasPermission("mcbr.necromancer") || player.isOp()) {

				if (player.hasPermission("mcbr.attacker")) {
					utilityMethods.takePlayerNode(player, "mcbr.attacker");
				}
				if (player.hasPermission("mcbr.defender")) {
					utilityMethods.takePlayerNode(player, "mcbr.defender");
				}
				if (player.hasPermission("mcbr.runner")) {
					utilityMethods.takePlayerNode(player, "mcbr.runner");
				}

				// Removes any typing from the player beforehand
				player.getInventory().clear();
				player.getInventory().setItem(8, itemDictionary.classStar());

				utilityMethods.givePlayerNode(player, "mcbr.defender");

				// give earth items
				if (Teams.isInTeam(player)) {
					if (Teams.getTeamType(player) == TeamType.REDTEAM) {
						player.getInventory().setHelmet(itemDictionary.redHelmet());
						player.getInventory().setChestplate(itemDictionary.redChestplate());
						player.getInventory().setLeggings(itemDictionary.redLeggings());
						player.getInventory().setBoots(itemDictionary.redBoots());
					} else if (Teams.getTeamType(player) == TeamType.BLUETEAM) {
						player.getInventory().setHelmet(itemDictionary.blueHelmet());
						player.getInventory().setChestplate(itemDictionary.blueChestplate());
						player.getInventory().setLeggings(itemDictionary.blueLeggings());
						player.getInventory().setBoots(itemDictionary.blueBoots());
					}
				}
				player.getInventory().setItem(0, itemDictionary.basicSword());
				player.getInventory().setItem(1, itemDictionary.necromancerCore());

				player.closeInventory();

			} else {
				player.sendMessage(ChatColor.RED + "You do not have this class unlocked!");
			}
		}

	}

	@EventHandler
	public void onInventoryClick(final InventoryDragEvent e) {
		if (e.getInventory().equals(inv)) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void classUIHolder(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		Action a = event.getAction();
		if (a.equals(Action.RIGHT_CLICK_AIR) || a.equals(Action.RIGHT_CLICK_BLOCK)) {
			if (player.getInventory().getItemInMainHand().equals(itemDictionary.classStar())) {
				openInventory(player);
			}
		}
	}

}
