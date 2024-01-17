package mcbattlerush;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

public class GameManager {

	private Main plugin;
	public GameState gameState = GameState.LOBBY;

	private GameStartCountdownTask gameStartCountdownTask;
	private GameTeleport gameTeleport;
	private InGameTimer inGameTimer;
	private WinCondition winCondition;
	private ClassTyping classTyping;
	private UtilityMethods utilityMethods = new UtilityMethods();
	private ItemDictionary itemDictionary = new ItemDictionary();
	
    private MapRotation mapRotation;



    public GameManager(Main plugin, MapRotation mapRotation) { // Add MapRotation parameter
    	this.plugin = plugin;
    	this.classTyping = new ClassTyping(this);
    	this.mapRotation = mapRotation; // Use passed MapRotation instance
    	this.winCondition = new WinCondition(this);
    }

	public void setGameState(GameState gameState) {
		if (this.gameState == GameState.INGAME && gameState == GameState.PREGAME)
			return;

		this.gameState = gameState;

		switch (gameState) {
		case LOBBY:
			winCondition.setWinningTeam(null);
			Bukkit.broadcastMessage("Current Mode: " + gameState + "");
			break;
		case PREGAME:
			if (this.gameStartCountdownTask != null) {
		        this.gameStartCountdownTask.cancel();
		        this.gameStartCountdownTask = null; // Ensure the old task is discarded
		    }
		    // insert all the starting needs for the game
		    Bukkit.broadcastMessage("Current Mode: " + gameState + "");
		    this.gameStartCountdownTask = new GameStartCountdownTask(this);
		    this.gameStartCountdownTask.runTaskTimer(plugin, 0, 20);
			
		    
			
			
			List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
			Collections.shuffle(players);

			int halfSize = players.size() / 2;

			for (int i = 0; i < players.size(); i++) {
			    Player player = players.get(i);
			    player.getInventory().clear();

			    if (i < halfSize) {
			        Teams.addToTeam(TeamType.REDTEAM, player);
			        player.sendMessage(ChatColor.GRAY + "You are now on the " + ChatColor.RED + "Red Team" + ChatColor.GRAY + ".");
			    } else {
			        Teams.addToTeam(TeamType.BLUETEAM, player);
			        player.sendMessage(ChatColor.GRAY + "You are now on the " + ChatColor.BLUE + "Blue Team" + ChatColor.GRAY + ".");
			    }

			    player.getInventory().setItem(8, itemDictionary.classStar());
			}
			
			
			break;

		case INGAME:
			
			for (Player player : Bukkit.getOnlinePlayers()) {
	            String team = Teams.getTeamType(player) == TeamType.REDTEAM ? "red" : "blue";
	            mapRotation.teleportToSpawn(player, team);
	        }
			
			Bukkit.broadcastMessage("Current Mode: " + gameState + "");
			if (this.inGameTimer != null) {
		        this.inGameTimer.stop();
		        this.inGameTimer = null;
		    }
			 if (this.inGameTimer == null) {
			        this.inGameTimer = new InGameTimer(this);
			    }
			    this.inGameTimer.runTaskTimer(plugin, 0, 20);

			for (Player player : Bukkit.getOnlinePlayers()) {
				this.classTyping.applyTypeEffects(player);

				player.getInventory().remove(itemDictionary.classStar());

			}

			break;
		case ENDGAME:
			Bukkit.broadcastMessage("Current Mode: " + gameState + "");

			this.winCondition = new WinCondition(this);
			WinCondition.checkWhoWon();
			
			WinCondition.grantEndGameRewards();
			cleanup();

			GameTeleport.teleportPlayersEND();

			// Remove all kits/items
			for (Player player : Bukkit.getOnlinePlayers()) {
				//
				 for (PotionEffect effect : player.getActivePotionEffects()) {
				        player.removePotionEffect(effect.getType());
			}
				
				player.getInventory().clear();
				player.getInventory().setArmorContents(null);

			}

			break;
		case RESTARTING:
			Bukkit.broadcastMessage("Current Mode: " + gameState + "");
			break;
		default:
			break;
		}
	}

	public void cleanup() {

		for (Player player : Bukkit.getOnlinePlayers()) {
			
			this.mapRotation.removeFlags();

			if (player.hasPermission("mcbr.attacker")) {
				utilityMethods.takePlayerNode(player, "mcbr.attacker");
			}
			if (player.hasPermission("mcbr.defender")) {
				utilityMethods.takePlayerNode(player, "mcbr.defender");
			}
			if (player.hasPermission("mcbr.runner")) {
				utilityMethods.takePlayerNode(player, "mcbr.runner");
			}
			

		}

		FlagBlock.getDefFlags().clear();
		FlagBlock.getRedFlags().clear();
		FlagBlock.getBlueFlags().clear();

		FlagBlock.setFlagCount(0);
		FlagBlock.setRedFlagCount(0);
		FlagBlock.setBlueFlagCount(0);
		

		this.gameState = GameState.LOBBY;

	}
	
	
	public MapRotation getMapRotation() {
        return mapRotation;
    }



}
