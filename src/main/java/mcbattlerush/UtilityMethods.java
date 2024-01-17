package mcbattlerush;

import java.util.List;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.types.PermissionNode;

public class UtilityMethods {
	
	public void summonRedstoneCircle(Location location, int size, Color color, float colorSize) {
	    for (int d = 0; d <= 90; d += 1) {
	        Location particleLoc = new Location(location.getWorld(), location.getX(), location.getY(), location.getZ());
	        particleLoc.setX(location.getX() + Math.cos(d) * size);
	        particleLoc.setZ(location.getZ() + Math.sin(d) * size);
	        location.getWorld().spawnParticle(Particle.REDSTONE, particleLoc, 1, new Particle.DustOptions(color, colorSize));
	    }
	}
	
	public void spawnFireworks(Location location, int amount, Color color){
        Location loc = location;
        Firework fw = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
        FireworkMeta fwm = fw.getFireworkMeta();
       
        fwm.setPower(2);
        fwm.addEffect(FireworkEffect.builder().withColor(color).flicker(true).build());
       
        fw.setFireworkMeta(fwm);
        new BukkitRunnable() {
        	public void run() {
                fw.detonate();
        	}
        }.runTaskLater(Main.getInstance(), 20);
       
        for(int i = 0;i<amount; i++){
            Firework fw2 = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
            fw2.setFireworkMeta(fwm);
        }
    }
	
	public void launchEntitiesAway(Player player, double radius, double power) {
	    List<Entity> nearbyEntities = player.getNearbyEntities(radius, radius, radius);

	    for (Entity entity : nearbyEntities) {
	        if (entity instanceof LivingEntity) {
	            Vector direction = entity.getLocation().subtract(player.getLocation()).toVector();
	            direction.setY(0.5);
	            Vector velocity = direction.normalize().multiply(power);

	            entity.setVelocity(velocity);
	        }
	    }
	}
	
	public String getCardinalDirection(Player player) {
	    double rotation = (player.getLocation().getYaw() - 90) % 360;
	    if (rotation < 0) {
	        rotation += 360.0;
	    }
	    if (0 <= rotation && rotation < 45.0) {
	        return "N";
	    } else if (45.0 <= rotation && rotation < 135.0) {
	        return "E";
	    } else if (135.0 <= rotation && rotation < 225.0) {
	        return "S";
	    } else if (225.0 <= rotation && rotation < 315.0) {
	        return "W";
	    } else if (315.0 <= rotation && rotation < 360.0) {
	        return "N";
	    } else {
	        return null;
	    }
	}
	
	
	public void givePlayerNode(Player player, String node) {
	    LuckPerms api = LuckPermsProvider.get();
	    User user = api.getUserManager().getUser(player.getUniqueId());

	    if (user != null) {
	        PermissionNode permissionNode = PermissionNode.builder(node).build();
	        user.data().add(permissionNode);
	        api.getUserManager().saveUser(user);
	    }
	}
	
	public void takePlayerNode(Player player, String node) {
	    LuckPerms api = LuckPermsProvider.get();
	    User user = api.getUserManager().getUser(player.getUniqueId());

	    if (user != null) {
	        PermissionNode permissionNode = PermissionNode.builder(node).build();
	        user.data().remove(permissionNode);
	        api.getUserManager().saveUser(user);
	    }
	}

}
