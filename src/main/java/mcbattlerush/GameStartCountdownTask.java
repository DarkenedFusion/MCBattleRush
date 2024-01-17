package mcbattlerush;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class GameStartCountdownTask extends BukkitRunnable {

	private GameManager gameManager;
	private UtilityMethods utilityMethods = new UtilityMethods();
	private ItemDictionary itemDictionary = new ItemDictionary();

	ArrayList<String> permissions = new ArrayList<String>();

	public GameStartCountdownTask(GameManager gameManager) {
		this.gameManager = gameManager;
	}

	private int timeLeft = 15;  //REMEMBER TO CHANGE BACK To 15

	@Override
	public void run() {
		timeLeft--;

		if (timeLeft <= 0) {

			this.cancel();
			for (Player player : Bukkit.getOnlinePlayers()) {

				if (Teams.isInTeam(player)) {
					if (Teams.getTeamType(player) == TeamType.REDTEAM) {
						player.getInventory().setHelmet(itemDictionary.redHelmet());
						player.getInventory().setChestplate(itemDictionary.redChestplate());
						player.getInventory().setLeggings(itemDictionary.redLeggings());
						player.getInventory().setBoots(itemDictionary.redBoots());
					} else if (Teams.getTeamType(player) == TeamType.BLUETEAM) {
						player.getInventory().setHelmet(itemDictionary.blueHelmet());
						player.getInventory().setChestplate(itemDictionary.blueChestplate());
						player.getInventory().setLeggings(itemDictionary.blueLeggings());
						player.getInventory().setBoots(itemDictionary.blueBoots());
					}
				}

				gameManager.setGameState(GameState.INGAME);

				player.removePotionEffect(PotionEffectType.SLOW);
				player.removePotionEffect(PotionEffectType.BLINDNESS);

			}

			return;
		}

		for (Player player : Bukkit.getOnlinePlayers()) {
			player.sendTitle(ChatColor.RED + "" + timeLeft + "", ChatColor.GRAY + "Don't forget to choose a class!", 1,
					20, 1);

			player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20 * 3, 0));
			player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20 * 3, 254));
			player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 20*3, 254));

		}
	}

}
