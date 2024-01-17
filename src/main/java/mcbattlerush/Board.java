package mcbattlerush;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import mcbattlerush.CooldownManager.CustomEffects;


public class Board implements Runnable {

	private final static Board instance = new Board();
	private String skill1ScoreString = ChatColor.WHITE + "Skill 1: ";
	private String skill2ScoreString = ChatColor.WHITE + "Skill 2: ";
	
	
   

	Board() {
	}

	@Override
	public void run() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			if (player.getScoreboard() != null
					&& player.getScoreboard().getObjective(Main.getInstance().getName()) != null)
				updateScoreboard(player);
			else
				createNewScoreboard(player);
		}
	}

	private void createNewScoreboard(Player player) {
	    Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
	    @SuppressWarnings("deprecation")
	    Objective objective = scoreboard.registerNewObjective(Main.getInstance().getName(), "dummy");

	    objective.setDisplaySlot(DisplaySlot.SIDEBAR);
	    objective.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "MC Battle Rush");

	    objective.getScore(ChatColor.WHITE + " ").setScore(8);
	    objective.getScore(ChatColor.WHITE + "Online: " + ChatColor.GREEN + "" + Bukkit.getOnlinePlayers().size()).setScore(7);
	    objective.getScore(ChatColor.GREEN + " ").setScore(6);

	    // Replace "Crystals: TBD" with the player's current balance
	    String balance = String.valueOf(Main.getInstance().getBalance(player.getName()));
	    objective.getScore(ChatColor.WHITE + "Crystals: " + ChatColor.GREEN + balance).setScore(5);

	    Team redTeam = scoreboard.registerNewTeam("redTeam");
	    String teamKey = ChatColor.WHITE.toString();

	    redTeam.addEntry(teamKey);
	    redTeam.setPrefix(ChatColor.WHITE + "Team: ");
	    redTeam.setSuffix(ChatColor.DARK_RED + "None");

	    objective.getScore(teamKey).setScore(4);
	    objective.getScore(ChatColor.WHITE + "Total Flags: " + ChatColor.GRAY + "0").setScore(3);
	    objective.getScore(ChatColor.WHITE + " ").setScore(2);
	    objective.getScore(ChatColor.WHITE + "Red Flags: " + ChatColor.RED + "0").setScore(1);
	    objective.getScore(ChatColor.WHITE + "Blue Flags: " + ChatColor.BLUE + "0").setScore(0);
	    
//	    objective.getScore(ChatColor.WHITE + "  ").setScore(-1); // Empty space
//	    Team skill1Team = scoreboard.registerNewTeam("skill1Team");
//	    skill1Team.addEntry(skill1ScoreString);
//	    skill1Team.setSuffix(ChatColor.GREEN + "0");
//	    objective.getScore(skill1ScoreString).setScore(-2);
//
//	    Team skill2Team = scoreboard.registerNewTeam("skill2Team");
//	    skill2Team.addEntry(skill2ScoreString);
//	    skill2Team.setSuffix(ChatColor.GREEN + "0");
//	    objective.getScore(skill2ScoreString).setScore(-3);

	    player.setScoreboard(scoreboard);
	    
	    


	    player.setScoreboard(scoreboard);
	}

	private void updateScoreboard(Player player) {
	    Scoreboard scoreboard = player.getScoreboard();
	    Objective objective = scoreboard.getObjective(Main.getInstance().getName());

	    Team redTeam = scoreboard.getTeam("redTeam");

	    if (redTeam.getSuffix() == null || !Teams.isInTeam(player)) {
	        redTeam.setSuffix(ChatColor.DARK_RED + "None");
	    } else if(Teams.getTeamType(player) == TeamType.REDTEAM) {
	        redTeam.setSuffix(ChatColor.RED + "Red");
	    } else {
	        redTeam.setSuffix(ChatColor.BLUE + "Blue");
	    }

	    // Define the strings locally
	    String flagsScoreString = ChatColor.WHITE + "Total Flags: " + ChatColor.GRAY + FlagBlock.getFlagCount();
	    String redFlagScoreString = ChatColor.WHITE + "Red Flags: " + ChatColor.RED + FlagBlock.getRedFlagCount();
	    String blueFlagScoreString = ChatColor.WHITE + "Blue Flags: " + ChatColor.BLUE + FlagBlock.getBlueFlagCount();
	    String onlineScoreString = ChatColor.WHITE + "Online: " + ChatColor.GREEN + Bukkit.getOnlinePlayers().size();

	    // Remove the old scores
	    for (String entry : scoreboard.getEntries()) {
	        if (entry.startsWith(ChatColor.WHITE + "Total Flags: ")
	                || entry.startsWith(ChatColor.WHITE + "Red Flags: ")
	                || entry.startsWith(ChatColor.WHITE + "Blue Flags: ")
	                || entry.startsWith(ChatColor.WHITE + "Online: ")) {
	            scoreboard.resetScores(entry);
	        }
	    }

	    // Create new scores with the updated strings
	    objective.getScore(flagsScoreString).setScore(3);
	    objective.getScore(redFlagScoreString).setScore(1);
	    objective.getScore(blueFlagScoreString).setScore(0);
	    objective.getScore(onlineScoreString).setScore(7); // Update the "Online" score

	    // Update the "Crystals" score
	    String balanceScoreString = ChatColor.WHITE + "Crystals: " + ChatColor.GREEN + Main.getInstance().getBalance(player.getName());
	    for (String entry : scoreboard.getEntries()) {
	        if (entry.startsWith(ChatColor.WHITE + "Crystals: ")) {
	            scoreboard.resetScores(entry);
	        }
	    }
	    objective.getScore(balanceScoreString).setScore(5);
	    
//	    List<CustomEffects> skills = CooldownManager.getSkills(player.getUniqueId());
//	    if (skills.size() >= 1) {
//	        Team skill1Team = scoreboard.getTeam("skill1Team");
//	        skill1Team.setSuffix(ChatColor.GREEN + "" + CooldownManager.getCooldown(player.getUniqueId(), skills.get(0)));
//	    }
//	    if (skills.size() >= 2) {
//	        Team skill2Team = scoreboard.getTeam("skill2Team");
//	        skill2Team.setSuffix(ChatColor.GREEN + "" + CooldownManager.getCooldown(player.getUniqueId(), skills.get(1)));
//	    }
	}

	public static Board getInstance() {
		return instance;
	}

}
