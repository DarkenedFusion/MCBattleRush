package mcbattlerush;

import java.util.HashMap;
import java.util.Set;

import org.bukkit.Location;

public class GameMap {
	private String name;
	private Location redSpawn;
	private Location blueSpawn;
	private HashMap<String, Location> flagLocations;

	public GameMap(String name, Location redSpawn, Location blueSpawn) {
		this.name = name;
		this.redSpawn = redSpawn;
		this.blueSpawn = blueSpawn;
		this.flagLocations = new HashMap<>();
	}

	public Location getRedSpawn() {
		return redSpawn;
	}

	public Location getBlueSpawn() {
		return blueSpawn;
	}

	public void setFlagLocation(String flagName, Location location) {
		this.flagLocations.put(flagName, location);
	}

	public Location getFlagLocation(String flagName) {
		return this.flagLocations.get(flagName);
	}

	public Set<String> getFlagNames() {
    return this.flagLocations.keySet();
}
}
