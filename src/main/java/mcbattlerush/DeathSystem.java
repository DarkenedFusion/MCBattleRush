package mcbattlerush;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import mcbattlerush.CooldownManager.CustomEffects;

public class DeathSystem implements Listener {
	private GameManager gameManager;

	private static HashMap<UUID, BukkitRunnable> deathTimers = new HashMap<>();
	private ItemDictionary itemDictionray = new ItemDictionary();
	private UtilityMethods utilityMethods = new UtilityMethods();
	private ClassTyping classTyping = new ClassTyping(gameManager);
	private InGameTimer inGameTimer = new InGameTimer(gameManager);
	private CooldownManager cooldownManager = new CooldownManager();
    private MapRotation mapRotation;

	
	int redBaseX = 10074;
	int redBaseY = 118;
	int redBaseZ = 10038;
	int blueBaseX = 10074;
	int blueBaseY = 118;
	int blueBaseZ = 9978;
	

	public DeathSystem(GameManager gameManager) {
	    this.gameManager = gameManager;
        this.mapRotation = gameManager.getMapRotation();

	}


	@EventHandler
	public void onDie(PlayerRespawnEvent event) {
		Player player = event.getPlayer();

		if (this.gameManager.gameState == GameState.ENDGAME || this.gameManager.gameState == GameState.RESTARTING
				|| this.gameManager.gameState == GameState.LOBBY || player.hasPermission("mcbr.norespawn")) {
			GameTeleport.teleportPlayerEND(player);
			return;

		}

		if (Teams.isInTeam(player)) {
			// Cancel the old timer if it exists
			BukkitRunnable oldTimer = deathTimers.get(player.getUniqueId());
			if (oldTimer != null) {
				oldTimer.cancel();
			}

			// Create a new timer
			BukkitRunnable newTimer = new BukkitRunnable() {
				private int timeBeforeRespawn = 8;

				@Override
				public void run() {
					timeBeforeRespawn--;

					if (timeBeforeRespawn <= 0) {
						
						
						
						
						CustomEffects[] customEffects = CustomEffects.values();

						for (CustomEffects effect : customEffects) {
							CooldownManager.removeCooldown(player.getUniqueId(), effect);
						}

						this.cancel();
						player.removePotionEffect(PotionEffectType.BLINDNESS);
						player.removePotionEffect(PotionEffectType.SLOW);

						if (Teams.getTeamType(player) == TeamType.REDTEAM) {
			                mapRotation.teleportToSpawn(player, "red");
			            } else if (Teams.getTeamType(player) == TeamType.BLUETEAM) {
			                mapRotation.teleportToSpawn(player, "blue");
			            } else {
							Location loc = new Location(Bukkit.getWorld("world"), 9896, 64, 9880);
							player.teleport(loc);
						}
						player.getInventory().remove(itemDictionray.classStar());

						classTyping.applyTypeEffects(player);

						deathTimers.remove(player.getUniqueId());

						return;
					}

					player.sendTitle(ChatColor.RED + "" + timeBeforeRespawn + "",
							ChatColor.GRAY + "You can change classes.", 1, 20, 1);

					player.getInventory().setItem(8, itemDictionray.classStar());
					player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20 * 3, 254));
					player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20 * 3, 254));
					player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 3 * 20, 254));
				}
			};

			// Start the new timer
			newTimer.runTaskTimer(Main.getInstance(), 0L, 20L);

			// Store the new timer
			deathTimers.put(player.getUniqueId(), newTimer);
		}
	}
	
}
