package mcbattlerush;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class InGameTimer extends BukkitRunnable {
    private boolean shouldRun = true;


	private GameManager gameManager;
	private UtilityMethods utilityMethods = new UtilityMethods();
	static BossBar bossBar;

	public InGameTimer(GameManager gameManager) {
	    this.gameManager = gameManager;

	    // Check if the boss bar already exists and is not empty
	    if (InGameTimer.bossBar == null || InGameTimer.bossBar.getTitle().isEmpty()) {
	        InGameTimer.bossBar = Bukkit.createBossBar("", BarColor.PURPLE, BarStyle.SOLID);
	    }
	    


	    // Add players to the boss bar when the timer is created
	    for (Player player : Bukkit.getOnlinePlayers()) {
	        if (bossBar.getPlayers().contains(player))
	            return;

	        bossBar.addPlayer(player);
	    }
	}
	
	 public void stop() {
	        this.shouldRun = false;
	    }

	private final int totalTime = 60;
	private int timeLeft = 60;

	@Override
	public void run() {
		timeLeft--;
		
		if (!this.shouldRun) {
            this.cancel();
            return;
        }

		if (timeLeft <= 10) {
			for (Player player : Bukkit.getOnlinePlayers()) {
				utilityMethods.givePlayerNode(player, "mcbr.norespawn");
			}
		}

		if (timeLeft <= 0) {

			gameManager.setGameState(GameState.ENDGAME);
			for (Player player : Bukkit.getOnlinePlayers()) {
				if (bossBar.getPlayers().contains(player)) {
					bossBar.removePlayer(player);
				}
				utilityMethods.takePlayerNode(player, "mcbr.norespawn");
			}
			this.cancel();
			return;
		}

		bossBar.setTitle(ChatColor.GOLD + "" + timeLeft + " seconds left");
		bossBar.setProgress((double) timeLeft / totalTime);
	}
	
	public static void clearBossBar() {
	    for (Player player : bossBar.getPlayers()) {
	        bossBar.removePlayer(player);
	    }
	    bossBar = null;
	}


}
