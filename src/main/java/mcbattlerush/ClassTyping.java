package mcbattlerush;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ClassTyping {
	
	private GameManager gameManager;
	
	private UtilityMethods utilityMethods = new UtilityMethods();

	
	public ClassTyping(GameManager gameManager) {
		this.gameManager = gameManager;
	}
	
	
	public void applyTypeEffects(Player player) {
	    if(Teams.isInTeam(player)) {
	        if (player.hasPermission("mcbr.attacker")) {
	            player.sendMessage(ChatColor.GRAY + "You are an " + ChatColor.DARK_RED + "attacker" + ChatColor.GRAY + ". Kill the enemy team to prevent them from capturing flags.");
	        }
	        else if (player.hasPermission("mcbr.defender")) {
	            player.sendMessage(ChatColor.GRAY + "You are a " + ChatColor.DARK_GREEN + "defender" + ChatColor.GRAY + ". Protect your teams flags at all costs.");
	        }
	        else if (player.hasPermission("mcbr.runner")) {
	            player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 180*20, 2));
	            player.sendMessage(ChatColor.GRAY + "You are a " + ChatColor.DARK_AQUA + "runner" + ChatColor.GRAY + ". Try to capture as many flags as possible.");
	        } else {
	        	return;
	        }
	    }
	}



}
