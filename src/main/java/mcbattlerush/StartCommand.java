package mcbattlerush;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StartCommand implements CommandExecutor {
	
	private GameManager gameManager;
	private MapRotation mapRotation; 
	
	public StartCommand(GameManager gameManager, MapRotation mapRotation) { // Modify this
		this.gameManager = gameManager;
		this.mapRotation = mapRotation; // Add this
	}
	
	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
		if(commandSender instanceof Player) {
			Player player = (Player) commandSender;
			if(player.isOp() || player.getName().equalsIgnoreCase("DarkenedFusion")) {
				this.mapRotation.startGame();
				this.mapRotation.placeFlags();
				this.gameManager.setGameState(GameState.PREGAME);
			}
		}
		
		return false;
	}
}

