package mcbattlerush;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class GameTeleport {

	private GameManager gameManager;
	private DeathSystem deathSystem;

	public GameTeleport(GameManager gameManager) {
		this.gameManager = gameManager;
		this.deathSystem = new DeathSystem(gameManager);
	}

	public void teleportPlayersSTART() {

		for (Player player : Bukkit.getOnlinePlayers()) {
			if (Teams.isInTeam(player)) {
				Location loc;
				if (Teams.getTeamType(player) == TeamType.BLUETEAM) {
					loc = new Location(Bukkit.getWorld("world"), 10074, 118, 9978);
					loc.setYaw(0);
					loc.setPitch(1);
					player.teleport(loc);
				} else if (Teams.getTeamType(player) == TeamType.REDTEAM) {
					loc = new Location(Bukkit.getWorld("world"), 10074, 118, 10038);
					loc.setYaw(180);
					loc.setPitch(0);
					player.teleport(loc);
				}
			}
		}
	}

	public static void teleportPlayersEND() {
		for (Player players : Bukkit.getOnlinePlayers()) {
			Location loc = new Location(Bukkit.getWorld("world"), 9896, 64, 9880);
			players.teleport(loc);
		}
	}

	public static void teleportPlayerEND(Player player) {
		Location loc = new Location(Bukkit.getWorld("world"), 9896, 64, 9880);
		player.teleport(loc);

	}

}
