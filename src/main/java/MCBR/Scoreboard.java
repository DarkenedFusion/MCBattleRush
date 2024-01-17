/*package MCBR;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.mrmicky.fastboard.FastBoard;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.scoreboard.Team;

public class Scoreboard implements Listener {
	private final Map<UUID, FastBoard> boards = new HashMap<>();
	
	public Scoreboard(MCBR plugin) {
		Bukkit.getPluginManager().registerEvents(this, plugin);

		
		plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
			
			for(FastBoard board : boards.values()) {
				updateBoard(board,
						"&aOnline: " + Bukkit.getOnlinePlayers().size(),
						"&cTeam: " ,
						"",
						"&aY-Cord:" + board.getPlayer().getLocation().getBlockY());
			}
		
		} , 0L, 10L); //10 ticks
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		FastBoard board = new FastBoard(player);
		
		String title = "&6&lMC Battle Rush";
		board.updateTitle(ChatColor.translateAlternateColorCodes('&', title));
		
		boards.put(player.getUniqueId(), board);
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		FastBoard board = boards.remove(player.getUniqueId());
		
		if(board != null) {
			board.delete();
		}
	}
	
	private void updateBoard(FastBoard board, String ... lines) {
		for(int i = 0; i < lines.length; ++i) {
			lines[i] = ChatColor.translateAlternateColorCodes('&', lines[i]);
		}
		
		board.updateLines(lines);
	}

}
*/
