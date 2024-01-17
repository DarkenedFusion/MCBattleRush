package mcbattlerush;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Teams {

	private static List<String> redTeam = new ArrayList<String>();
	private static List<String> blueTeam = new ArrayList<String>();

	public static void addToTeam(TeamType type, Player player) {
		resetTeam(player);
		switch (type) {
		case REDTEAM:
			redTeam.add(player.getName());
			player.setPlayerListName(ChatColor.RED + "[Red] " + ChatColor.WHITE + "" + player.getName());
			break;
		case BLUETEAM:
			blueTeam.add(player.getName());
			player.setPlayerListName(ChatColor.BLUE + "[Blue] " + ChatColor.WHITE + "" + player.getName());
			break;
		}
	}
	
	public static void setRedTeam(Player player) {
		resetTeam(player);
		Teams.addToTeam(TeamType.REDTEAM, player);
		player.setPlayerListName(ChatColor.RED + "[Red] " + ChatColor.WHITE + "" + player.getName());
	}
	
	public static void setBlueTeam(Player player) {
		resetTeam(player);
		Teams.addToTeam(TeamType.BLUETEAM, player);
		player.setPlayerListName(ChatColor.BLUE + "[Blue] " + ChatColor.WHITE + "" + player.getName());

	}
	
	public static boolean isInTeam(Player player) {
		return redTeam.contains(player.getName()) || blueTeam.contains(player.getName());
	}
	
	public static void clearTeams() {		
		redTeam.clear();
		blueTeam.clear();
	}
	
	public static void resetTeam(Player player) {
		if(getTeamType(player) == TeamType.BLUETEAM) {
			player.setPlayerListName(ChatColor.WHITE + "" + player.getName());
			blueTeam.remove(player.getName());
		}
		if(getTeamType(player) == TeamType.REDTEAM) {
			player.setPlayerListName(ChatColor.WHITE + "" + player.getName());
			redTeam.remove(player.getName());
		}
	}
	
	public static List<String> getRedTeam(){
		return redTeam;
	}
	
	public static List<String> getBlueTeam(){
		return blueTeam;
	}
	
	public static List<String> getAllPlayersInTeam(){
		
		List<String> combinedTeams = new ArrayList<String>();
		combinedTeams.addAll(redTeam);
		combinedTeams.addAll(blueTeam);
		return combinedTeams;
	}
	
	public static TeamType getTeamType(Player player) {
		if(!isInTeam(player)) 
			return null;
		return (redTeam.contains(player.getName())) ? TeamType.REDTEAM : TeamType.BLUETEAM;
	}

}