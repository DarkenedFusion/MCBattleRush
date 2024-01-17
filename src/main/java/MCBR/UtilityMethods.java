package MCBR;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;

public class UtilityMethods {
	
	public void summonRedstoneCircle(Location location, int size, Color color, float colorSize) {
	    for (int d = 0; d <= 90; d += 1) {
	        Location particleLoc = new Location(location.getWorld(), location.getX(), location.getY(), location.getZ());
	        particleLoc.setX(location.getX() + Math.cos(d) * size);
	        particleLoc.setZ(location.getZ() + Math.sin(d) * size);
	        location.getWorld().spawnParticle(Particle.REDSTONE, particleLoc, 1, new Particle.DustOptions(color, colorSize));
	    }
	}

}
