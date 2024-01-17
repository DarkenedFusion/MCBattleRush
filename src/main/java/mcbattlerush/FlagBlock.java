package mcbattlerush;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class FlagBlock implements Listener {

	UtilityMethods utility = new UtilityMethods();

	static HashMap<Player, Block> defFlagBlock = new HashMap<>();
	static HashMap<TeamType, Block> redFlagBlock = new HashMap<>();
	static HashMap<TeamType, Block> blueFlagBlock = new HashMap<>();

	private static int flagCount = 0;
	private static int redFlagCount = 0;
	private static int blueFlagCount = 0;

	private int RADIUS = 3; // Radius for flag defense

	@EventHandler
	public void onPlaceFlag(BlockPlaceEvent event) {
		final Block block = event.getBlock();
		final Player player = event.getPlayer();

		if (block.getType() == Material.WHITE_TERRACOTTA) {
			Bukkit.broadcastMessage(
					player.getDisplayName().toString() + " has placed a " + ChatColor.GREEN + "Block Flag");

			final Location bLoc = block.getLocation();

			defFlagBlock.put(player, block);

			utility.summonRedstoneCircle(bLoc, 3, Color.WHITE, 1);

			ArmorStand armorStand = (ArmorStand) bLoc.getWorld().spawnEntity(bLoc.add(0.5D, -0.8D, 0.5D),
					EntityType.ARMOR_STAND);
			armorStand.setArms(false);
			armorStand.setGravity(false);
			armorStand.setSmall(false);
			armorStand.setCustomNameVisible(true);
			armorStand.setCustomName(ChatColor.WHITE + "Flag");
			armorStand.setVisible(false);
			armorStand.setCanPickupItems(false);

			flagCount++;
		}

	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Block block = event.getBlock();
		Location armorLoc = block.getLocation().add(0.5, 0, 0.5);
		Location bLoc = block.getLocation();

		if (block.getType() == Material.WHITE_TERRACOTTA) {
			flagCount--;
			if (flagCount <= 0) {
				flagCount = 0;
			}
		}

		for (Entity entity : armorLoc.getWorld().getNearbyEntities(armorLoc, 0.5, 0.5, 0.5)) {
			if (entity instanceof ArmorStand && entity.isCustomNameVisible()) {
				entity.remove();
			}
		}
		event.setDropItems(false);

		// Replaces flag at same spot with the proper Team Type
		if (!event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
			if (Teams.isInTeam(event.getPlayer())) {
				TeamType playerTeam = Teams.getTeamType(event.getPlayer());

				event.setCancelled(true);
				switch (playerTeam) {
				case REDTEAM:
					if (block.getType() == Material.BLUE_GLAZED_TERRACOTTA) {
						// Attacking a Blue Team Flag as a Red Team
						blueFlagCount--;
						block.setType(Material.RED_GLAZED_TERRACOTTA);
						Bukkit.broadcastMessage(ChatColor.RED + event.getPlayer().getName() + " " + ChatColor.WHITE
								+ "has captured a " + ChatColor.BLUE + "Blue Team" + ChatColor.WHITE + " flag.");
						utility.summonRedstoneCircle(block.getLocation(), 3, Color.RED, 1);
						utility.spawnFireworks(block.getLocation(), 1, Color.RED);
						ArmorStand armorStand = (ArmorStand) block.getWorld()
								.spawnEntity(block.getLocation().add(0.5D, -0.8D, 0.5D), EntityType.ARMOR_STAND);
						armorStand.setArms(false);
						armorStand.setGravity(false);
						armorStand.setSmall(false);
						armorStand.setCustomNameVisible(true);
						armorStand.setCustomName(ChatColor.RED + "Red Team Flag");
						armorStand.setVisible(false);
						armorStand.setCanPickupItems(false);
						redFlagBlock.put(TeamType.REDTEAM, block);

						redFlagCount++;

						break;
					}
					if (block.getType() == Material.RED_GLAZED_TERRACOTTA) {
						ArmorStand armorStand = (ArmorStand) block.getWorld()
								.spawnEntity(block.getLocation().add(0.5D, -0.8D, 0.5D), EntityType.ARMOR_STAND);
						armorStand.setArms(false);
						armorStand.setGravity(false);
						armorStand.setSmall(false);
						armorStand.setCustomNameVisible(true);
						armorStand.setCustomName(ChatColor.RED + "Red Team Flag");
						armorStand.setVisible(false);
						armorStand.setCanPickupItems(false);

						event.getPlayer().sendMessage(ChatColor.RED + "You can't break your own teams flag.");
						// Attacking a Red Team Flag as a Red Team
						break;
					}
					if (block.getType() == Material.WHITE_TERRACOTTA) {
						block.setType(Material.RED_GLAZED_TERRACOTTA);
						Bukkit.broadcastMessage(ChatColor.RED + event.getPlayer().getName() + " " + ChatColor.WHITE
								+ "has captured a " + ChatColor.WHITE + "Flag.");

						utility.summonRedstoneCircle(block.getLocation(), 3, Color.RED, 1);
						utility.spawnFireworks(block.getLocation(), 1, Color.RED);

						ArmorStand redStand = (ArmorStand) bLoc.getWorld().spawnEntity(bLoc.add(0.5D, -0.8D, 0.5D),
								EntityType.ARMOR_STAND);
						redStand.setArms(false);
						redStand.setGravity(false);
						redStand.setSmall(false);
						redStand.setCustomNameVisible(true);
						redStand.setCustomName(ChatColor.RED + "Red Team Flag");
						redStand.setVisible(false);
						redStand.setCanPickupItems(false);
						flagCount++;
						redFlagCount++;
						break;
					} else {
						break;
					}

				case BLUETEAM:
					if (block.getType() == Material.RED_GLAZED_TERRACOTTA) {
						// Attacking a Red Team Flag as a Blue Team
						redFlagCount--;
						block.setType(Material.BLUE_GLAZED_TERRACOTTA);
						Bukkit.broadcastMessage(ChatColor.BLUE + event.getPlayer().getName() + " " + ChatColor.WHITE
								+ "has captured a " + ChatColor.RED + "Red Team" + ChatColor.WHITE + " flag.");
						utility.summonRedstoneCircle(block.getLocation(), 3, Color.BLUE, 1);
						utility.spawnFireworks(block.getLocation(), 1, Color.BLUE);
						ArmorStand armorStand = (ArmorStand) block.getWorld()
								.spawnEntity(block.getLocation().add(0.5D, -0.8D, 0.5D), EntityType.ARMOR_STAND);
						armorStand.setArms(false);
						armorStand.setGravity(false);
						armorStand.setSmall(false);
						armorStand.setCustomNameVisible(true);
						armorStand.setCustomName(ChatColor.BLUE + "Blue Team Flag");
						armorStand.setVisible(false);
						armorStand.setCanPickupItems(false);
						blueFlagBlock.put(TeamType.BLUETEAM, block);
						blueFlagCount++;
						break;
					}
					if (block.getType() == Material.BLUE_GLAZED_TERRACOTTA) {
						// Attacking a Blue Team Flag as a Blue Team
						ArmorStand armorStand = (ArmorStand) block.getWorld()
								.spawnEntity(block.getLocation().add(0.5D, -0.8D, 0.5D), EntityType.ARMOR_STAND);
						armorStand.setArms(false);
						armorStand.setGravity(false);
						armorStand.setSmall(false);
						armorStand.setCustomNameVisible(true);
						armorStand.setCustomName(ChatColor.BLUE + "Blue Team Flag");
						armorStand.setVisible(false);
						armorStand.setCanPickupItems(false);
						event.getPlayer().sendMessage(ChatColor.RED + "You can't break your own teams flag.");
						break;
					}
					if (block.getType() == Material.WHITE_TERRACOTTA) {
						block.setType(Material.BLUE_GLAZED_TERRACOTTA);
						Bukkit.broadcastMessage(ChatColor.BLUE + event.getPlayer().getName() + " " + ChatColor.WHITE
								+ "has captured a " + ChatColor.WHITE + "Flag.");
						ArmorStand blueStand = (ArmorStand) bLoc.getWorld().spawnEntity(bLoc.add(0.5D, -0.8D, 0.5D),
								EntityType.ARMOR_STAND);
						utility.summonRedstoneCircle(block.getLocation(), 3, Color.BLUE, 1);
						utility.spawnFireworks(block.getLocation(), 1, Color.BLUE);
						blueStand.setArms(false);
						blueStand.setGravity(false);
						blueStand.setSmall(false);
						blueStand.setCustomNameVisible(true);
						blueStand.setCustomName(ChatColor.AQUA + "Blue Team Flag");
						blueStand.setVisible(false);
						blueStand.setCanPickupItems(false);
						flagCount++;
						blueFlagCount++;

						break;
					} else {
						break;
					}
				default:
					break;
				}

			} else {
				Bukkit.broadcastMessage("Somehow a player has broken a flag without being in a team.");
			}
		} else {
			if (block.getType() == Material.BLUE_GLAZED_TERRACOTTA) {
				blueFlagCount--;
				if (blueFlagCount <= 0) {
					blueFlagCount = 0;
				}
			} else if (block.getType() == Material.RED_GLAZED_TERRACOTTA) {
				redFlagCount--;
				if (redFlagCount <= 0) {
					redFlagCount = 0;
				}
			}
		}

	}

	// Grants buffs to defenders who defend their own flag
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();

		// Check blocks within a 3-block radius
		for (int x = -RADIUS; x <= RADIUS; x++) {
			for (int y = -RADIUS; y <= RADIUS; y++) {
				for (int z = -RADIUS; z <= RADIUS; z++) {

					if (Teams.isInTeam(player)) {
						if (Teams.getTeamType(player) == TeamType.BLUETEAM) {

							if (player.getLocation().add(x, y, z).getBlock()
									.getType() == Material.BLUE_GLAZED_TERRACOTTA) {
								if (player.hasPermission("mcbr.defender")) {
									
									//defender bonus
									player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 20*3, 1));
									player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20*3, 0));
									
									
								}
								return;
							}
						} else if (Teams.getTeamType(player) == TeamType.REDTEAM) {
							if (player.getLocation().add(x, y, z).getBlock()
									.getType() == Material.RED_GLAZED_TERRACOTTA) {
								if (player.hasPermission("mcbr.defender")) {
									
									
									//defender bonus
									player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 20*3, 1));
									player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20*3, 0));

								}
								return;
							}
						}
					}
				}
			}
		}
	}

	public static int getFlagCount() {
		return flagCount;
	}

	public static int getRedFlagCount() {
		return redFlagCount;
	}

	public static int getBlueFlagCount() {
		return blueFlagCount;
	}

	public static HashMap<Player, Block> getDefFlags() {
		return defFlagBlock;
	}

	public static HashMap<TeamType, Block> getRedFlags() {
		return redFlagBlock;
	}

	public static HashMap<TeamType, Block> getBlueFlags() {
		return blueFlagBlock;
	}

	public static void setFlagCount(int fCount) {
		flagCount = fCount;
	}

	public static void setRedFlagCount(int redCount) {
		redFlagCount = redCount;
	}

	public static void setBlueFlagCount(int blueCount) {
		blueFlagCount = blueCount;
	}

}
