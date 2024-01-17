package MCBR;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class MCBR extends JavaPlugin implements Listener {

	private static MCBR instance;

	@Override
	public void onEnable() {
		instance = this;

		// this.getServer().getPluginManager().registerEvents(new FlagBlock(), this);
		this.getServer().getPluginManager().registerEvents(this, this);

//		new Scoreboard(this);

		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard board = manager.getNewScoreboard();

		Objective objective = board.registerNewObjective("showhealth", "health");
		objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
		objective.setDisplayName("/ 20");

		for (Player online : Bukkit.getOnlinePlayers()) {
			online.setScoreboard(board);
			online.setHealth(online.getHealth()); // Update their health
		}

	}

	@Override
	public void onDisable() {

	}

	public static MCBR getInstance() {
		return instance;
	}

}
