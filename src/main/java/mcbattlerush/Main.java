package mcbattlerush;

import java.io.File;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public class Main extends JavaPlugin implements Listener {

	private final ReentrantLock _Lock = new ReentrantLock();
	private int defaultBalance = 0;
	private String currencySingular = ChatColor.AQUA + "Crystal";
	private String currencyPlural = ChatColor.AQUA + "Crystals";

	private static Main instance;
	private BukkitTask task;

	private GameManager gameManager;
	private DeathSystem deathSystem;
	private MapRotation mapRotation;
	private UtilityMethods utilityMethods = new UtilityMethods();

	@Override
	public void onEnable() {
		instance = this;

		mapRotation = new MapRotation(); 
		gameManager = new GameManager(this, mapRotation); 		
		deathSystem = new DeathSystem(gameManager);
		this.getServer().getPluginManager().registerEvents(deathSystem, this);

		File configFile = new File(getDataFolder(), "config.yml");
		if (!configFile.exists()) {
			saveDefaultConfig();
		} else {
			reloadConfig();
		}

		GameStartCountdownTask countdownTask = new GameStartCountdownTask(gameManager);

		this.getServer().getPluginManager().registerEvents(new FlagBlock(), this);
		this.getServer().getPluginManager().registerEvents(new ClassUI(), this);
		this.getServer().getPluginManager().registerEvents(new ItemListener(countdownTask), this);

		this.getServer().getPluginManager().registerEvents(this, this);

		gameManager.cleanup();
		Teams.clearTeams();

		this.getCommand("assignToTeam").setExecutor(this);
		this.getCommand("teamcheck").setExecutor(this);
		this.getCommand("assignRed").setExecutor(this);
		this.getCommand("assignBlue").setExecutor(this);
		this.getCommand("resetTeam").setExecutor(this);
		this.getCommand("exchange").setExecutor(this);
		this.getCommand("grant").setExecutor(new ItemGranter());
		this.getCommand("start").setExecutor(new StartCommand(gameManager, mapRotation));
		this.getCommand("money").setExecutor(new MoneyCommand(this));

		task = getServer().getScheduler().runTaskTimer(this, Board.getInstance(), 0, 10);

	}

	@Override
	public void onDisable() {
		InGameTimer.clearBossBar();

		if (task != null && !task.isCancelled()) {
			task.cancel();
		}
		

		Teams.clearTeams();

		gameManager.cleanup();

		saveConfig();
	}

	public static Main getInstance() {
		return instance;
	}

	@EventHandler
	public void cancelFood(FoodLevelChangeEvent event) {
		event.setCancelled(true);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("assignToTeam")) {

			if (!sender.isOp()) {
				return true;
			}

			int i = 0;
			for (Player player : Bukkit.getOnlinePlayers()) {
				if (i < Bukkit.getOnlinePlayers().size() / 2) {
					Teams.addToTeam(TeamType.REDTEAM, player);
					player.sendMessage(
							ChatColor.GRAY + "You are now on the " + ChatColor.RED + "Red Team" + ChatColor.GRAY + ".");
				} else {
					Teams.addToTeam(TeamType.BLUETEAM, player);
					player.sendMessage(ChatColor.GRAY + "You are now on the " + ChatColor.BLUE + "Blue Team"
							+ ChatColor.GRAY + ".");
				}
				i++;
			}
		}

		if (cmd.getName().equalsIgnoreCase("exchange")) {
			if (getBalance(sender.getName()) >= 100) {
				sender.sendMessage(ChatColor.GRAY + "You have spent " + ChatColor.RED + "100" + ChatColor.GRAY
						+ " crystals on a class key!");
				removeBalance(sender.getName(), 100);
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
						"excellentcrates key give " + sender.getName() + " class_key 1");
			} else {
				sender.sendMessage(ChatColor.RED + "You do not have enough crystals for a key.");
			}
		}

		if (cmd.getName().equalsIgnoreCase("assignRed")) {
			Player player = (Player) sender;

			if (!sender.isOp()) {
				return true;
			}
			if (args.length == 0) {
				Teams.setRedTeam(player);
				player.sendMessage(
						ChatColor.GRAY + "You are now on the " + ChatColor.RED + "Red Team" + ChatColor.GRAY + ".");
			} else if (args.length == 1) {
				String playerName = args[0];
				Player otherPlayer = Bukkit.getPlayer(playerName);
				if (otherPlayer != null) {
					Teams.setRedTeam(otherPlayer);
					otherPlayer.sendMessage(
							ChatColor.GRAY + "You are now on the " + ChatColor.RED + "Red Team" + ChatColor.GRAY + ".");
				} else {
					sender.sendMessage("Player " + playerName + " not found.");
				}
			} else {
				sender.sendMessage("Invalid number of arguments.");
			}
		}

		if (cmd.getName().equalsIgnoreCase("assignBlue")) {
			Player player = (Player) sender;

			if (!sender.isOp()) {
				return true;
			}
			if (args.length == 0) {
				Teams.setBlueTeam(player);
				player.sendMessage(
						ChatColor.GRAY + "You are now on the " + ChatColor.BLUE + "Blue Team" + ChatColor.GRAY + ".");
			} else if (args.length == 1) {
				String playerName = args[0];
				Player otherPlayer = Bukkit.getPlayer(playerName);
				if (otherPlayer != null) {
					Teams.setBlueTeam(otherPlayer);
					otherPlayer.sendMessage(ChatColor.GRAY + "You are now on the " + ChatColor.BLUE + "Blue Team"
							+ ChatColor.GRAY + ".");
				} else {
					sender.sendMessage("Player " + playerName + " not found.");
				}
			} else {
				sender.sendMessage("Invalid number of arguments.");
			}
		}

		if (cmd.getName().equalsIgnoreCase("resetTeam")) {

			if (!sender.isOp()) {
				return true;
			}
			Player player = (Player) sender;
			if (args.length == 0) {
				Teams.resetTeam(player);
			} else if (args.length == 1) {
				String playerName = args[0];
				Player otherPlayer = Bukkit.getPlayer(playerName);
				if (otherPlayer != null) {
					Teams.resetTeam(player);
				} else {
					sender.sendMessage("Player " + playerName + " not found.");
				}
			} else {
				sender.sendMessage("Invalid number of arguments.");
			}
		}

		if (cmd.getName().equalsIgnoreCase("teamcheck")) {
			if (args.length == 0) {
				Player player = (Player) sender;
				TeamType team = Teams.getTeamType(player);
				if (team != null) {
					player.sendMessage("You are on " + team.name() + " Team");
				} else {
					player.sendMessage("You are not on a team");
				}
			} else if (args.length == 1) {
				String playerName = args[0];
				Player otherPlayer = Bukkit.getPlayer(playerName);
				if (otherPlayer != null) {
					TeamType team = Teams.getTeamType(otherPlayer);
					if (team != null) {
						sender.sendMessage(playerName + " is on " + team.name() + " Team");
					} else {
						sender.sendMessage(playerName + " is not on a team");
					}
				} else {
					sender.sendMessage("Player " + playerName + " not found.");
				}
			} else {
				sender.sendMessage("Invalid number of arguments.");
			}
		}

		return false;
	}
	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
	    if (event instanceof EntityDamageByEntityEvent) {
	        EntityDamageByEntityEvent entityDamageEvent = (EntityDamageByEntityEvent) event;
	        Entity damagerEntity = entityDamageEvent.getDamager();
	        if (damagerEntity instanceof Firework) {
	            Firework firework = (Firework) damagerEntity;
	            String playerName = firework.getCustomName();
	            Player damager = Bukkit.getPlayer(playerName);
	            if (damager != null && event.getEntity() instanceof Player) {
	                Player damaged = (Player) event.getEntity();
	                // Check if the damager and the damaged player are on the same team
	                if (Teams.isInTeam(damager) && Teams.isInTeam(damaged) && Teams.getTeamType(damager) == Teams.getTeamType(damaged)) {
	                    // Cancel the event to prevent the damage
	                    event.setCancelled(true);
	                }
	            }
	        }
	    }
	}






	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {

	    String[] classes = { "air", "fire", "earth" };
	    Random random = new Random();

	    Player player = event.getPlayer();
	    Teams.clearTeams();

	    if (!player.hasPlayedBefore()) {
	        int randomIndex = random.nextInt(classes.length);
	        utilityMethods.givePlayerNode(player, "mcbr." + classes[randomIndex]);
	    }

	}

	@EventHandler
	public void drop(PlayerDropItemEvent event) {
		if (event.getPlayer().getGameMode() == GameMode.CREATIVE)
			return;
		event.setCancelled(true);

	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		Teams.clearTeams();
	}

	@EventHandler
	public void disableFriendlyFire(EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof Player) {
			Player player = (Player) event.getDamager();
			if (event.getEntity() instanceof Player) {
				Player hitPlayer = (Player) event.getEntity();

				if (Teams.isInTeam(player) && Teams.isInTeam(hitPlayer)) {
					if (Teams.getTeamType(player) == Teams.getTeamType(hitPlayer)) {
						event.setCancelled(true);
					}
				}
			}
		}

	}

	public void reloadPlugin() {
		_Lock.lock();
		try {
			reloadConfig();
			defaultBalance = getConfig().getInt("config.default_balance", 0);
			currencySingular = getConfig().getString("config.currency.singular", "Dollar");
			currencyPlural = getConfig().getString("config.currency.plural", "Dollars");
		} finally {
			_Lock.unlock();
		}
	}

	public int getBalance(String Player) {
		_Lock.lock();
		try {
			String playerName = Player.toLowerCase();
			return getConfig().getInt("accounts." + playerName, defaultBalance);
		} finally {
			_Lock.unlock();
		}
	}

	public Boolean setBalance(String Player, int NewBalance) {
		_Lock.lock();
		try {
			String playerName = Player.toLowerCase();
			if (NewBalance < 0) {
				return false;
			} else {
				getConfig().set("accounts." + playerName, NewBalance);
				saveConfig();
				return true;
			}
		} finally {
			_Lock.unlock();
		}
	}

	public Boolean addBalance(String Player, int Amount) {
		_Lock.lock();
		try {
			String playerName = Player.toLowerCase();
			if (Amount < 0) {
				return false;
			} else {
				int newBalance = getConfig().getInt("accounts." + playerName, defaultBalance) + Amount;
				getConfig().set("accounts." + playerName, newBalance);
				saveConfig();
				return true;
			}
		} finally {
			_Lock.unlock();
		}
	}

	public Boolean removeBalance(String Player, int Amount) {
		_Lock.lock();
		try {
			String playerName = Player.toLowerCase();
			if (Amount < 0) {
				return false;
			} else {
				int newBalance = getConfig().getInt("accounts." + playerName, defaultBalance) - Amount;
				if (newBalance < 0) {
					return false;
				} else {
					getConfig().set("accounts." + playerName, newBalance);
					saveConfig();
					return true;
				}
			}
		} finally {
			_Lock.unlock();
		}
	}

	public String getCurrencyName(Boolean Plural) {
		_Lock.lock();
		try {
			return Plural ? currencyPlural : currencySingular;
		} finally {
			_Lock.unlock();
		}
	}

	public Boolean payPlayer(String Player, String PaidPlayer, int Amount) {
		_Lock.lock();
		try {
			if (Amount > 0) {
				String playerName = Player.toLowerCase();
				String paidPlayerName = PaidPlayer.toLowerCase();
				if (playerName.equals(paidPlayerName)) {
					return false;
				} else {
					int playerBalance = getConfig().getInt("accounts." + playerName, defaultBalance) - Amount;
					if (playerBalance < 0) {
						return false;
					} else {
						int paidPlayerBalance = getConfig().getInt("accounts." + paidPlayerName, defaultBalance)
								+ Amount;
						getConfig().set("accounts." + playerName, playerBalance);
						getConfig().set("accounts." + paidPlayerName, paidPlayerBalance);
						saveConfig();
						return true;
					}
				}
			} else {
				return false;
			}
		} finally {
			_Lock.unlock();
		}
	}
}
