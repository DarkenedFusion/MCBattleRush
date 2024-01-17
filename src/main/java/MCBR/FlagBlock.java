package MCBR;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class FlagBlock implements Listener {

	UtilityMethods utility = new UtilityMethods();

	HashMap<Player, Location> blockLocation = new HashMap<Player, Location>();
	HashMap<Player, Block> blockInfo = new HashMap<Player, Block>();

	private final Map<Player, BukkitRunnable> flagTasks = new HashMap<>();
	private final List<Player> flaggedPlayers = new ArrayList<>();


	@EventHandler
	public void onPlaceFlag(BlockPlaceEvent event) {
		Block block = event.getBlock();
		final Player player = event.getPlayer();

		// Stage 1/Placement of Flag block

		// && event.getItemInHand().getItemMeta().getDisplayName().contains("Block
		// Flag")

		if (block.getType() == Material.STONE) {
			Bukkit.broadcastMessage(
					player.getDisplayName().toString() + " has placed a " + ChatColor.GREEN + "Block Flag");

			final Location bLoc = block.getLocation();

			blockLocation.put(player, bLoc);
			blockInfo.put(player, block);

			utility.summonRedstoneCircle(bLoc, 3, Color.WHITE, 1);

		}

	}

	@EventHandler
	public void flagDefense(PlayerMoveEvent event) {
	    final Player player = event.getPlayer();
	    final Location blockLoc = blockLocation.get(player);
	    final Block block = blockInfo.get(player);

	    if (player.getLocation().distance(blockLoc) <= 3 && !flaggedPlayers.contains(player)) {
	        flaggedPlayers.add(player); // Add the player to the flagged list

	        BukkitRunnable flagTask = new BukkitRunnable() {
	            int t = 0;

	            public void run() {
	                t++;

	                switch (t) {
	                    case 20:
	                        block.setType(Material.COBBLESTONE);
	                        break;
	                    case 200:
	                        block.setType(Material.COBBLED_DEEPSLATE);
	                        break;
	                    case 400:
	                        block.setType(Material.DEEPSLATE_COPPER_ORE);
	                        break;
	                    case 600:
	                        block.setType(Material.GOLD_BLOCK);
	                        this.cancel(); 
	                        break;
	                    default:
	                        break;
	                }
	            }
	        };

	        flagTasks.put(player, flagTask); // Store the task associated with the player
	        flagTask.runTaskTimer(MCBR.getInstance(), 0, 1);
	    }
	    flaggedPlayers.remove(player);
	}

	@SuppressWarnings("unlikely-arg-type")
	@EventHandler
	public void onBreakFlag(BlockBreakEvent event) {
	    Block block = event.getBlock();
	    Player player = event.getPlayer();

	    if (flagTasks.containsKey(player)) {
	        BukkitRunnable flagTask = flagTasks.get(player);
	        flagTask.cancel(); 
	        flagTasks.remove(player); 
	        flaggedPlayers.remove(player); 

	        if (block.getType() == blockInfo.get(block).getType()) {
	            // Your logic for handling the block break event
	        }
	    }
	}



}
