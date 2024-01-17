package mcbattlerush;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class WinCondition {

	private static GameManager gameManager;

	static int totalFlagCount = FlagBlock.getFlagCount();
	static int redFlagCount = FlagBlock.getRedFlagCount();
	static int blueFlagCount = FlagBlock.getBlueFlagCount();

	public static HashMap<String, TeamType> firstIterationWinner = new HashMap<>();

	private static TeamType winningTeam;

	public WinCondition(GameManager gameManager) {
		WinCondition.gameManager = gameManager;
	}

	public static TeamType checkWhoWon() {
		if (gameManager.gameState == GameState.RESTARTING) {
			return null;
		}
		totalFlagCount = FlagBlock.getFlagCount();
		redFlagCount = FlagBlock.getRedFlagCount();
		blueFlagCount = FlagBlock.getBlueFlagCount();

		System.out
				.println(totalFlagCount + " is the total, " + redFlagCount + " is red, " + blueFlagCount + " is blue.");

		for (Player player : Bukkit.getOnlinePlayers()) {
			System.out.println(
					totalFlagCount + " is the total, " + redFlagCount + " is red, " + blueFlagCount + " is blue.");
			if (Teams.isInTeam(player)) {
				System.out.println(
						totalFlagCount + " is the total, " + redFlagCount + " is red, " + blueFlagCount + " is blue.");
				if (blueFlagCount > redFlagCount) {
					if (Teams.getTeamType(player) == TeamType.BLUETEAM) {
						player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
					} else if (Teams.getTeamType(player) == TeamType.REDTEAM) {
						player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1, 0);
					}
					player.sendTitle(ChatColor.BLUE + "Blue Team" + ChatColor.GOLD + " has won the game!", "", 10,
							20 * 6, 10);
					winningTeam = TeamType.BLUETEAM;
				} else if (redFlagCount > blueFlagCount) {
					if (Teams.getTeamType(player) == TeamType.REDTEAM) {
						player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
					} else if (Teams.getTeamType(player) == TeamType.BLUETEAM) {
						player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1, 0);
					}

					player.sendTitle(ChatColor.RED + "Red Team" + ChatColor.GOLD + " has won the game!", "", 10, 20 * 6,
							10);
					winningTeam = TeamType.REDTEAM;
				} else {
					player.sendTitle(ChatColor.WHITE + "It is a DRAW", "", 10, 20 * 3, 10);
					winningTeam = null;
				}
				if (firstIterationWinner.isEmpty()) {
					firstIterationWinner.put("winner", winningTeam);
				}
			}
		}

		Bukkit.broadcastMessage(ChatColor.WHITE + "The winner of the last game is the " + winningTeam + "");
		gameManager.setGameState(GameState.RESTARTING);
		return winningTeam;

	}

	public static void grantEndGameRewards() {
	    TeamType firstWinner = firstIterationWinner.get("winner");

	    if (firstWinner == null) {
	        System.out.println("No winner in the first iteration");
	        return;
	    }

	    System.out.println(firstWinner + " is the winner of the first iteration");

	    Random random = new Random();
	    int minWinReward = 10;
	    int maxWinReward = 20;
	    int minLoseReward = 5;
	    int maxLoseReward = 15;

	    for (Player player : Bukkit.getOnlinePlayers()) {
	        if (Teams.isInTeam(player)) {
	            TeamType playerTeam = Teams.getTeamType(player);
	            int reward;

	            if (playerTeam == firstWinner) {
	                reward = random.nextInt(maxWinReward - minWinReward + 1) + minWinReward;
	                System.out.println(playerTeam + " team won. Reward: " + reward);
	            } else {
	                reward = random.nextInt(maxLoseReward - minLoseReward + 1) + minLoseReward;
	                System.out.println(playerTeam + " team lost. Reward: " + reward);
	            }

	            Main.getInstance().addBalance(player.getName(), reward);
	        }
	    }
	}


	public void setWinningTeam(TeamType team) {
		winningTeam = team;
	}

}
