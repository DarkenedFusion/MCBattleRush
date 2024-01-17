package mcbattlerush;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class MapRotation {
	private List<GameMap> maps;
	private GameMap currentMap;
	private HashMap<String, Location> teamSpawns;
    private HashMap<String, Block> placedFlags;


	public MapRotation() {
		maps = new ArrayList<>();
		teamSpawns = new HashMap<>();
		placedFlags = new HashMap<>();

		World world = Bukkit.getServer().getWorld("world");
		if (world == null) {
			System.err.println("World not found: " + "worldName");
		} else {
			Location belowZeroRedSpawn = new Location(world, 10074, 118, 10038, 180, 0);
			Location belowZeroBlueSpawn = new Location(world, 10074, 118, 9978, 0, 1);
			
			Location belowZeroFlag1 = new Location(world, 10087, 112, 9995); //in dark pit
			Location belowZeroFlag2 = new Location(world, 10087, 124, 10020); //On top of tower
			Location belowZeroFlag3 = new Location(world, 10059, 113, 10020); //between quad lava
			Location belowZeroFlag4 = new Location(world, 10061, 120, 9996); //spikey lava peak
			Location belowZeroFlag5 = new Location(world, 10074, 119, 10008); //middle
			
			GameMap belowZero = new GameMap("BelowZero", belowZeroRedSpawn, belowZeroBlueSpawn);
			belowZero.setFlagLocation("flag1", belowZeroFlag1);
			belowZero.setFlagLocation("flag2", belowZeroFlag2);
			belowZero.setFlagLocation("flag3", belowZeroFlag3);
			belowZero.setFlagLocation("flag4", belowZeroFlag4);
			belowZero.setFlagLocation("flag5", belowZeroFlag5);
			
			Location defaultRedSpawn = new Location(world, 10015, 117, 10037, 180, 1);
			Location defaultBlueSpawn = new Location(world, 10015, 117, 9979, 0, 0);
			
			Location defaultMap1 = new Location(world, 9995, 120, 10028); //hill with pipe
			Location defaultMap2 = new Location(world, 9996, 115, 9989); //Pond
			Location defaultMap3 = new Location(world, 10034, 115, 9988); //upside down tree
			Location defaultMap4 = new Location(world, 10034, 115, 10027); //forest
			Location defaultMap5 = new Location(world, 10015, 118, 10008); //middle
			
			GameMap defaultMap = new GameMap("Default", defaultRedSpawn, defaultBlueSpawn);
			
			defaultMap.setFlagLocation("flag1", defaultMap1);
			defaultMap.setFlagLocation("flag2", defaultMap2);
			defaultMap.setFlagLocation("flag3", defaultMap3);
			defaultMap.setFlagLocation("flag4", defaultMap4);
			defaultMap.setFlagLocation("flag5", defaultMap5);
			
			Location netherRedSpawn = new Location(world, 9815, 118, 10039, 180, 1);
			Location netherBlueSpawn = new Location(world, 9815, 118, 9981, 0, 1);
			
			Location netherFlag1 = new Location(world, 9797, 114, 10026); //blue forest
			Location netherFlag2 = new Location(world, 9799, 119, 9996); //smokey area
			Location netherFlag3 = new Location(world, 9831, 126, 9995); //soul
			Location netherFlag4 = new Location(world, 9831, 114, 10027); //spikey lava peak
			Location netherFlag5 = new Location(world, 9815, 116, 10010); //middle
			
			
			GameMap nether = new GameMap("Nether", netherRedSpawn, netherBlueSpawn);
			
			nether.setFlagLocation("flag1", netherFlag1);
			nether.setFlagLocation("flag2", netherFlag2);
			nether.setFlagLocation("flag3", netherFlag3);
			nether.setFlagLocation("flag4", netherFlag4);
			nether.setFlagLocation("flag5", netherFlag5);
			

			maps.add(belowZero);
			maps.add(defaultMap);
			maps.add(nether);
			Collections.shuffle(maps);
		}
	}

	public void startGame() {
	    currentMap = maps.get(0);
	    System.out.println("Red Spawn Location: " + currentMap.getRedSpawn());
	    System.out.println("Blue Spawn Location: " + currentMap.getBlueSpawn());
	    teamSpawns.put("red", currentMap.getRedSpawn());
	    teamSpawns.put("blue", currentMap.getBlueSpawn());
	}


	public void teleportToSpawn(Player player, String team) {
	    System.out.println("Team: " + team);
	    System.out.println("Spawn Location: " + teamSpawns.get(team));
	    player.teleport(teamSpawns.get(team));
	}


	public void respawn(Player player, String team) {
		teleportToSpawn(player, team);
	}
	public void placeFlags() {
        for (String flagName : currentMap.getFlagNames()) {
        	FlagBlock.setFlagCount(FlagBlock.getFlagCount() + 1);
            Location flagLocation = currentMap.getFlagLocation(flagName);
            Block flagBlock = flagLocation.getBlock();
            flagBlock.setType(Material.WHITE_TERRACOTTA); // or whatever material your flags are
            placedFlags.put(flagName, flagBlock);
        }
    }

	public void removeFlags() {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "minecraft:kill @e[type=minecraft:armor_stand]");
        for (Block flagBlock : placedFlags.values()) {
        	FlagBlock.setFlagCount(FlagBlock.getFlagCount() - 1);
            flagBlock.setType(Material.AIR);
        }
        placedFlags.clear(); // remove all entries from the HashMap
    }
}