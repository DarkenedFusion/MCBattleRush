package mcbattlerush;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.EvokerFangs;
import org.bukkit.entity.Firework;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class ItemListener implements Listener {

	private ItemDictionary itemDictionary = new ItemDictionary();
	private UtilityMethods utilityMethods = new UtilityMethods();
	private GameStartCountdownTask countdownTask;
	private FlagBlock flagBlock = new FlagBlock();

	public ItemListener(GameStartCountdownTask countdownTask) {
		this.countdownTask = countdownTask;
	}

	private int fireFistCD = 20;
	private double fireFistDMG = 3.0;
	private int flameEmperorCD = 35;
	private double flameEmperorDMG = 5.0;
	private int airDashCD = 15;
	private int windVortexCD = 30;
	private int rockWallCD = 25;
	private int rockWallTime = 3;
	private int earthDomeCD = 40;
	private int earthDomeTime = 5;
	private int spyglassCD = 25;
	private int rocketLauncherCD = 30;
	private double rocketLauncherDMG = 2.5;
	private int lightningBlastCD = 30;
	private double lightningBlastDMG = 5.5;
	private int thunderLanceCD = 40;
	private double thunderLanceDMG = 7.5;
	private int iceAgeCD = 30;
	private double iceAgeDMG = 3.5;
	private int glacialSphereCD = 25;
	private int raisingDeathCD = 35;
	private double raisingDeathDMG = 6.0;
	private int attunedSoulsCD = 60;

	private HashMap<UUID, Player> evokerFangsOwners = new HashMap<>();
	private HashMap<UUID, BukkitRunnable> healingEffectPlayers = new HashMap<>();

	@EventHandler
	public void tokenExchange(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		Action a = event.getAction();
		if (a.equals(Action.RIGHT_CLICK_AIR) || a.equals(Action.RIGHT_CLICK_BLOCK)) {

			if (player.getInventory().getItemInMainHand().isSimilar(itemDictionary.airToken())) {
				player.getInventory().remove(itemDictionary.airToken());
				if (!player.hasPermission("mcbr.air")) {
					utilityMethods.givePlayerNode(player, "mcbr.air");
					player.sendMessage(ChatColor.GRAY + "You have just unlocked the " + ChatColor.WHITE + "Air "
							+ ChatColor.GRAY + "class!");
				}
				player.getWorld().playSound(player, Sound.BLOCK_AMETHYST_BLOCK_BREAK, 1, 0);
			} else if (player.getInventory().getItemInMainHand().isSimilar(itemDictionary.fireToken())) {
				player.getInventory().remove(itemDictionary.fireToken());
				if (!player.hasPermission("mcbr.fire")) {
					utilityMethods.givePlayerNode(player, "mcbr.fire");
					player.sendMessage(ChatColor.GRAY + "You have just unlocked the " + ChatColor.WHITE + "Fire "
							+ ChatColor.GRAY + "class!");
				}
				player.getWorld().playSound(player, Sound.BLOCK_AMETHYST_BLOCK_BREAK, 1, 0);
			} else if (player.getInventory().getItemInMainHand().isSimilar(itemDictionary.earthToken())) {
				player.getInventory().remove(itemDictionary.earthToken());
				if (!player.hasPermission("mcbr.earth")) {
					utilityMethods.givePlayerNode(player, "mcbr.earth");
					player.sendMessage(ChatColor.GRAY + "You have just unlocked the " + ChatColor.WHITE + "Earth "
							+ ChatColor.GRAY + "class!");
				}
				player.getWorld().playSound(player, Sound.BLOCK_AMETHYST_BLOCK_BREAK, 1, 0);
			} else if (player.getInventory().getItemInMainHand().isSimilar(itemDictionary.rocketeerToken())) {
				player.getInventory().remove(itemDictionary.rocketeerToken());
				if (!player.hasPermission("mcbr.rocketeer")) {
					utilityMethods.givePlayerNode(player, "mcbr.rocketeer");
					player.sendMessage(ChatColor.GRAY + "You have just unlocked the " + ChatColor.GREEN + "Rocketeer "
							+ ChatColor.GRAY + "class!");
				}
			} else if (player.getInventory().getItemInMainHand().isSimilar(itemDictionary.thunderToken())) {
				player.getInventory().remove(itemDictionary.thunderToken());
				if (!player.hasPermission("mcbr.thunder")) {
					utilityMethods.givePlayerNode(player, "mcbr.thunder");
					player.sendMessage(ChatColor.GRAY + "You have just unlocked the " + ChatColor.BLUE + "Thunder "
							+ ChatColor.GRAY + "class!");
				}
				player.getWorld().playSound(player, Sound.BLOCK_AMETHYST_BLOCK_BREAK, 1, 0);
			} else if (player.getInventory().getItemInMainHand().isSimilar(itemDictionary.iceToken())) {
				player.getInventory().remove(itemDictionary.iceToken());
				if (!player.hasPermission("mcbr.ice")) {
					utilityMethods.givePlayerNode(player, "mcbr.ice");
					player.sendMessage(ChatColor.GRAY + "You have just unlocked the " + ChatColor.GREEN + "Ice "
							+ ChatColor.GRAY + "class!");
				}
				player.getWorld().playSound(player, Sound.BLOCK_AMETHYST_BLOCK_BREAK, 1, 0);
			} else if (player.getInventory().getItemInMainHand().isSimilar(itemDictionary.necromancerToken())) {
				player.getInventory().remove(itemDictionary.necromancerToken());
				if (!player.hasPermission("mcbr.necromancer")) {
					utilityMethods.givePlayerNode(player, "mcbr.necromancer");
					player.sendMessage(ChatColor.GRAY + "You have just unlocked the " + ChatColor.BLUE + "Necromancer "
							+ ChatColor.GRAY + "class!");
				}
				player.getWorld().playSound(player, Sound.BLOCK_AMETHYST_BLOCK_BREAK, 1, 0);
			}
		}
	}

	@EventHandler
	public void fireClassAbilities(PlayerInteractEvent event) {
		final Player player = event.getPlayer();
		Action a = event.getAction();

		if (a.equals(Action.LEFT_CLICK_AIR) || a.equals(Action.LEFT_CLICK_BLOCK)) {
			if (player.getInventory().getItemInMainHand().isSimilar(itemDictionary.fireCore())) {
				if (!CooldownManager.hasCooldown(player.getUniqueId(), CooldownManager.CustomEffects.FIREFIST)) {

					Location eyeLocation = player.getEyeLocation();
					Vector direction = eyeLocation.getDirection();

					player.getWorld().playSound(player, Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 10, 2);

					// Spawn the fire particle beam the i < number represents the distance in blocks
					// supposedly
					for (int i = 0; i < 15; i++) {
						Location location = eyeLocation.add(direction);
						// Increase the particle count to make the beam look chunkier
						player.getWorld().spawnParticle(Particle.FLAME, location, 10, 0.1, 0.1, 0.1, 0.1);

						// Get the entities within 1 block of the current location

						for (Entity e : location.getWorld().getEntities()) {
							if (!e.equals(player) && e instanceof LivingEntity || e instanceof Player) {
								if (e.getLocation().distance(location) < 2.0) {
									if (Teams.isInTeam((Player) e)) {
										if (Teams.getTeamType(player) == Teams.getTeamType((Player) e)) {
											break;
										}
									}

									Damageable d = (Damageable) e;
									d.damage(fireFistDMG, player);
									d.setFireTicks(20 * 3);
								}
							}
						}
						CooldownManager.setCooldown(player.getUniqueId(), CooldownManager.CustomEffects.FIREFIST,
								fireFistCD);

					}
				} else {
					player.sendMessage(ChatColor.RED + "This ability is on cooldown for "
							+ CooldownManager.getCooldown(player.getUniqueId(), CooldownManager.CustomEffects.FIREFIST)
							+ ChatColor.RED + " seconds.");
				}
			}
		} else if (a.equals(Action.RIGHT_CLICK_AIR) || a.equals(Action.RIGHT_CLICK_BLOCK)) {
			if (player.getInventory().getItemInMainHand().isSimilar(itemDictionary.fireCore())) {
				if (!CooldownManager.hasCooldown(player.getUniqueId(), CooldownManager.CustomEffects.FLAMEEMPEROR)) {

					player.getWorld().playSound(player, Sound.BLOCK_FIRE_EXTINGUISH, 10, 2);
					player.getWorld().playSound(player, Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 10, 0);

					new BukkitRunnable() {
						Location eyeLocation = player.getEyeLocation();
						Vector direction = eyeLocation.getDirection().normalize();
						int i = 0;

						@Override
						public void run() {
							if (i >= 30) {
								// Cancel the task when i reaches 100
								this.cancel();
								return;
							}

							Location currentLocation = eyeLocation.add(direction.multiply(0.85));
							double radius = 1.0; // Increase this value to increase the size of the sphere

							// Check if the particle hits a block
							if (currentLocation.getBlock().getType() != Material.AIR) {
								this.cancel();
								return;
							}

							for (double theta = 0; theta <= 2 * Math.PI; theta += Math.PI / 10) {
								for (double phi = 0; phi <= Math.PI; phi += Math.PI / 10) {
									double x = radius * Math.sin(phi) * Math.cos(theta);
									double y = radius * Math.sin(phi) * Math.sin(theta);
									double z = radius * Math.cos(phi);
									player.getWorld().spawnParticle(Particle.FLAME,
											currentLocation.clone().add(x, y, z), 0, 0, 0, 0);
								}
							}

							for (Entity e : currentLocation.getWorld().getEntities()) {
								if (e.getLocation().distance(currentLocation) < 3.0) {
									if (!e.equals(player) && e instanceof LivingEntity) {
										LivingEntity lEntity = (LivingEntity) e;

										if (lEntity instanceof Player) {
											if (Teams.isInTeam((Player) lEntity)) {
												if (Teams.getTeamType(player) == Teams.getTeamType((Player) lEntity)) {
													break;
												}
											}
										}

										lEntity.damage(flameEmperorDMG, player);
										lEntity.setFireTicks(20 * 3);

										this.cancel();
										return;
									}
								}
							}

							i++;
						}
					}.runTaskTimer(Main.getInstance(), 0L, 1l);

					CooldownManager.setCooldown(player.getUniqueId(), CooldownManager.CustomEffects.FLAMEEMPEROR,
							flameEmperorCD);

				} else {
					player.sendMessage(ChatColor.RED + "This ability is on cooldown for " + CooldownManager
							.getCooldown(player.getUniqueId(), CooldownManager.CustomEffects.FLAMEEMPEROR)
							+ ChatColor.RED + " seconds.");
				}
			}

		}
	}

	@EventHandler
	public void airClassAbilities(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		Action a = event.getAction();

		if (a.equals(Action.LEFT_CLICK_AIR) || a.equals(Action.LEFT_CLICK_BLOCK)) {
			if (player.getInventory().getItemInMainHand().isSimilar(itemDictionary.airCore())) {
				if (!CooldownManager.hasCooldown(player.getUniqueId(), CooldownManager.CustomEffects.AIRDASH)) {

					Vector direction = player.getLocation().getDirection();
					direction.setY(0.5);
					direction.multiply(1.3);
					player.setVelocity(direction);

					player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 30, 0));

					player.getWorld().spawnParticle(Particle.CLOUD, player.getLocation(), 100, 0.5, 0.5, 0.5, 0.1);

					CooldownManager.setCooldown(player.getUniqueId(), CooldownManager.CustomEffects.AIRDASH, airDashCD);

				} else {
					player.sendMessage(ChatColor.RED + "This ability is on cooldown for "
							+ CooldownManager.getCooldown(player.getUniqueId(), CooldownManager.CustomEffects.AIRDASH)
							+ ChatColor.RED + " seconds.");
				}
			}
		} else if (a.equals(Action.RIGHT_CLICK_AIR) || a.equals(Action.RIGHT_CLICK_BLOCK)) {
			if (player.getInventory().getItemInMainHand().isSimilar(itemDictionary.airCore())) {
				if (!CooldownManager.hasCooldown(player.getUniqueId(), CooldownManager.CustomEffects.WINDVORTEX)) {

					// Create a vortex of particles
					Particle.DustOptions dustOptions = new Particle.DustOptions(Color.WHITE, 1);

					for (int i = 0; i < 20; i++) { // Reduced number of iterations
						final int index = i;
						Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
							double radius = 0.05 * index; // Increased radius increment
							for (double t = 0; t <= Math.PI; t += Math.PI / 25) { // Increased step size
								for (double phi = 0; phi <= Math.PI * 2; phi += Math.PI / 12) { // Increased step
																								// size
									double x = radius * Math.sin(t) * Math.cos(phi);
									double y = radius * Math.cos(t);
									double z = radius * Math.sin(t) * Math.sin(phi);
									player.getWorld().spawnParticle(Particle.REDSTONE,
											player.getLocation().add(x, y + 1, z), 0, dustOptions);
								}
							}
						}, i / 2); // Decreased delay
					}

					utilityMethods.launchEntitiesAway(player, 3, 2.5);

					CooldownManager.setCooldown(player.getUniqueId(), CooldownManager.CustomEffects.WINDVORTEX,
							windVortexCD);

				} else {
					player.sendMessage(
							ChatColor.RED
									+ "This ability is on cooldown for " + CooldownManager
											.getCooldown(player.getUniqueId(), CooldownManager.CustomEffects.WINDVORTEX)
									+ ChatColor.RED + " seconds.");
				}
			}

		}
	}

	@EventHandler
	public void earthClassAbilities(PlayerInteractEvent event) {
		final Player player = event.getPlayer();
		Action a = event.getAction();

		if (a.equals(Action.LEFT_CLICK_AIR) || a.equals(Action.LEFT_CLICK_BLOCK)) {
			if (player.getInventory().getItemInMainHand().isSimilar(itemDictionary.earthCore())) {
				if (!CooldownManager.hasCooldown(player.getUniqueId(), CooldownManager.CustomEffects.ROCKWALL)) {
					// Get the block in the air in front of the player
					Location loc = player.getLocation().add(player.getLocation().getDirection().multiply(5));
					Block blockInFront = loc.getBlock();
					if (blockInFront != null) {
						// Create a 3x3 wall
						Material[][] originalBlocks = new Material[3][3];
						String dir = utilityMethods.getCardinalDirection(player);
						for (int i = -1; i <= 1; i++) {
							for (int y = 0; y <= 2; y++) {
								Block currentBlock;
								if (dir.equals("N") || dir.equals("S")) {
									currentBlock = blockInFront.getRelative(0, y, i);
								} else { // dir is "E" or "W"
									currentBlock = blockInFront.getRelative(i, y, 0);
								}
								originalBlocks[i + 1][y] = currentBlock.getType();
								currentBlock.setType(Material.DIRT);
							}
						}

						// Schedule a task to remove the wall after x seconds
						Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
							@Override
							public void run() {
								for (int i = -1; i <= 1; i++) {
									for (int y = 0; y <= 2; y++) {
										Block currentBlock;
										if (dir.equals("N") || dir.equals("S")) {
											currentBlock = blockInFront.getRelative(0, y, i);
										} else { // dir is "E" or "W"
											currentBlock = blockInFront.getRelative(i, y, 0);
										}
										currentBlock.setType(originalBlocks[i + 1][y]);
									}
								}
							}
						}, rockWallTime * 20); // Convert seconds to ticks
					}

					CooldownManager.setCooldown(player.getUniqueId(), CooldownManager.CustomEffects.ROCKWALL,
							rockWallCD);
				} else {
					player.sendMessage(ChatColor.RED + "This ability is on cooldown for "
							+ CooldownManager.getCooldown(player.getUniqueId(), CooldownManager.CustomEffects.ROCKWALL)
							+ ChatColor.RED + " seconds.");
				}
			}
		} else if (a.equals(Action.RIGHT_CLICK_AIR) || a.equals(Action.RIGHT_CLICK_BLOCK)) {
			if (player.getInventory().getItemInMainHand().isSimilar(itemDictionary.earthCore())) {
				if (!CooldownManager.hasCooldown(player.getUniqueId(), CooldownManager.CustomEffects.EARTHDOME)) {
					// Get the player's location
					Location playerLocation = player.getLocation();

					// Create a hollow sphere of YourChosenBlock at the player's location
					int radius = 5;
					createHollowSphere(playerLocation, Material.DIRT, radius);

					// Schedule a task to restore the blocks after x seconds
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {

						public void run() {
							restoreBlocks(playerLocation, radius);
						}

					}, earthDomeTime * 20); // Convert seconds to ticks

					CooldownManager.setCooldown(player.getUniqueId(), CooldownManager.CustomEffects.EARTHDOME,
							earthDomeCD);
				} else {
					player.sendMessage(ChatColor.RED + "This ability is on cooldown for "
							+ CooldownManager.getCooldown(player.getUniqueId(), CooldownManager.CustomEffects.EARTHDOME)
							+ ChatColor.RED + " seconds.");
				}
			}
		}
	}

	@EventHandler
	public void rocketeerClassAbilities(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		Action a = event.getAction();

		if (a.equals(Action.LEFT_CLICK_AIR) || a.equals(Action.LEFT_CLICK_BLOCK)) {
			if (player.getInventory().getItemInMainHand().isSimilar(itemDictionary.rocketeerCore())) {
				if (!CooldownManager.hasCooldown(player.getUniqueId(), CooldownManager.CustomEffects.SPYGLASS)) {
					ItemStack spyGlass = null;

					if (Teams.isInTeam(player)) {
						if (Teams.getTeamType(player) == TeamType.REDTEAM) {

							if (FlagBlock.getRedFlagCount() == 0) {
								spyGlass = new ItemStack(itemDictionary.spyGlass6());
							} else if (FlagBlock.getRedFlagCount() == 1) {
								spyGlass = new ItemStack(itemDictionary.spyGlass5());
							} else if (FlagBlock.getRedFlagCount() == 2) {
								spyGlass = new ItemStack(itemDictionary.spyGlass4());
							} else if (FlagBlock.getRedFlagCount() == 3) {
								spyGlass = new ItemStack(itemDictionary.spyGlass3());
							} else if (FlagBlock.getRedFlagCount() == 4) {
								spyGlass = new ItemStack(itemDictionary.spyGlass2());
							} else {
								spyGlass = new ItemStack(itemDictionary.spyGlass1());
							}
							player.getInventory().setItemInOffHand(spyGlass);

						} else if (Teams.getTeamType(player) == TeamType.BLUETEAM) {
							if (FlagBlock.getBlueFlagCount() == 0) {
								spyGlass = new ItemStack(itemDictionary.spyGlass6());
							} else if (FlagBlock.getBlueFlagCount() == 1) {
								spyGlass = new ItemStack(itemDictionary.spyGlass5());
							} else if (FlagBlock.getBlueFlagCount() == 2) {
								spyGlass = new ItemStack(itemDictionary.spyGlass4());
							} else if (FlagBlock.getBlueFlagCount() == 3) {
								spyGlass = new ItemStack(itemDictionary.spyGlass3());
							} else if (FlagBlock.getBlueFlagCount() == 4) {
								spyGlass = new ItemStack(itemDictionary.spyGlass2());
							} else {
								spyGlass = new ItemStack(itemDictionary.spyGlass1());
							}

							player.getInventory().setItemInOffHand(spyGlass);
						}
						ItemStack finalSpyGlass = spyGlass;
						if (finalSpyGlass != null) {
							Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
								@Override
								public void run() {
									if (player.getInventory().getItemInOffHand().isSimilar(finalSpyGlass)) {
										player.getInventory().setItemInOffHand(new ItemStack(Material.AIR));
									}
								}
							}, 20L * 10);
						}
					}

					CooldownManager.setCooldown(player.getUniqueId(), CooldownManager.CustomEffects.SPYGLASS,
							spyglassCD);

				} else {
					player.sendMessage(ChatColor.RED + "This ability is on cooldown for "
							+ CooldownManager.getCooldown(player.getUniqueId(), CooldownManager.CustomEffects.SPYGLASS)
							+ ChatColor.RED + " seconds.");
				}
			}
		} else if (a.equals(Action.RIGHT_CLICK_AIR) || a.equals(Action.RIGHT_CLICK_BLOCK)) {
			if (player.getInventory().getItemInMainHand().isSimilar(itemDictionary.rocketeerCore())) {
				if (!CooldownManager.hasCooldown(player.getUniqueId(), CooldownManager.CustomEffects.ROCKETLAUNCHER)) {

					// Check if the player is sneaking while right-clicking
					if (player.isSneaking()) {
						Firework firework = player.getWorld().spawn(player.getEyeLocation(), Firework.class);

						FireworkMeta fireworkMeta = firework.getFireworkMeta();

						fireworkMeta.setPower(1);
						fireworkMeta.addEffect(FireworkEffect.builder().withColor(Color.PURPLE).withColor(Color.RED)
								.withColor(Color.BLUE).with(FireworkEffect.Type.BALL_LARGE).build());
						firework.setShotAtAngle(true);
						firework.setFireworkMeta(fireworkMeta);

						Vector direction = player.getEyeLocation().getDirection().multiply(2);
						firework.setVelocity(direction);

						new BukkitRunnable() {

							public void run() {
								if (firework.isDead() || firework.isOnGround()) {
									Vector oppositeDirection = player.getLocation().subtract(firework.getLocation())
											.toVector().normalize();
									oppositeDirection.setY(0.5);

									player.setVelocity(oppositeDirection.multiply(2));

									this.cancel();
								}
							}
						}.runTaskTimer(Main.getInstance(), 0, 1);

					} else {
						Firework[] fireworks = new Firework[3];

						for (int i = 0; i < 3; i++) {
							final int index = i;
							new BukkitRunnable() {
								public void run() {
									fireworks[index] = player.getWorld().spawn(player.getEyeLocation(), Firework.class);
									FireworkMeta fireworkMeta = fireworks[index].getFireworkMeta();
									fireworks[index].setCustomName(player.getName());
									fireworkMeta.setPower(1);
									fireworkMeta.addEffect(FireworkEffect.builder().withColor(Color.PURPLE)
											.withColor(Color.RED).withColor(Color.BLUE)
											.with(FireworkEffect.Type.BALL_LARGE).build());
									fireworks[index].setShotAtAngle(true);
									fireworks[index].setFireworkMeta(fireworkMeta);
									Vector direction = player.getEyeLocation().getDirection().multiply(2);
									fireworks[index].setVelocity(direction);
								}
							}.runTaskLater(Main.getInstance(), index * 10);
						}

						for (Firework firework : fireworks) {
							new BukkitRunnable() {
								public void run() {
									if (firework != null) {
										if ((firework.isDead() || firework.isOnGround())) {
											if (player.getLocation().distance(firework.getLocation()) <= 2) {
												player.getWorld().playSound(player, Sound.ENTITY_FIREWORK_ROCKET_BLAST,
														10, 1);
												player.getWorld().playSound(player, Sound.ENTITY_GENERIC_EXPLODE, 5, 1);
												Vector direction1 = player.getLocation()
														.subtract(firework.getLocation()).toVector().normalize();
												direction1.setY(0.5);
												player.setVelocity(direction1);
											}
											player.getWorld().playSound(player, Sound.ENTITY_FIREWORK_ROCKET_BLAST, 10,
													1);
											player.getWorld().playSound(player, Sound.ENTITY_GENERIC_EXPLODE, 5, 1);
											this.cancel();
										}
									} else {
										return;
									}
								}
							}.runTaskTimer(Main.getInstance(), 0, 1);
						}

						for (Firework firework : fireworks) {
							if (firework != null) {
								for (Entity entity : firework.getNearbyEntities(1, 1, 1)) {
									if (entity instanceof Player) {
										Player otherPlayer = (Player) entity;
										if (Teams.isInTeam(player) && Teams.isInTeam(otherPlayer)) {
											if (Teams.getTeamType(player) == Teams.getTeamType(otherPlayer)) {
												// If the entity is a teammate, don't detonate the firework
												return;
											} else {
												firework.detonate();
											}
										}
									}
								}
								player.getWorld().playSound(player, Sound.ENTITY_FIREWORK_ROCKET_BLAST, 10, 1);
								player.getWorld().playSound(player, Sound.ENTITY_GENERIC_EXPLODE, 10, 1);
							}
						}

					}

					CooldownManager.setCooldown(player.getUniqueId(), CooldownManager.CustomEffects.ROCKETLAUNCHER,
							rocketLauncherCD);
				} else {
					player.sendMessage(
							ChatColor.RED + "This ability is on cooldown for "
									+ CooldownManager.getCooldown(player.getUniqueId(),
											CooldownManager.CustomEffects.ROCKETLAUNCHER)
									+ ChatColor.RED + " seconds.");
				}
			}
		}

	}

	@EventHandler
	public void thunderClassAbilities(PlayerInteractEvent event) {
		final Player player = event.getPlayer();
		Action a = event.getAction();

		if (a.equals(Action.LEFT_CLICK_AIR) || a.equals(Action.LEFT_CLICK_BLOCK)) {
			if (player.getInventory().getItemInMainHand().isSimilar(itemDictionary.thunderCore())) {
				if (!CooldownManager.hasCooldown(player.getUniqueId(), CooldownManager.CustomEffects.LIGHTNINGBLAST)) {

					player.getWorld().playSound(player, Sound.WEATHER_RAIN_ABOVE, 1, 1);
					player.getWorld().playSound(player, Sound.ENTITY_GENERIC_WIND_BURST, 1, 0);
					player.getWorld().playSound(player, Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1, 0);

					Block targetBlock = player.getTargetBlock(null, 5);
					if (targetBlock != null) {
						while (targetBlock.getType() == Material.AIR) {
							targetBlock = targetBlock.getRelative(BlockFace.DOWN);
						}

						double cloudSize = 3.0;
						int cloudHeight = 7; // Height of the cloud above the target block
						int spawnDuration = 20; // Duration of particle spawning in ticks (20 ticks = 1 second)
						int layers = 3; // Number of cloud layers

						new BukkitRunnable() {
							Block target = player.getTargetBlock(null, 5);
							int ticks = 0; // Number of ticks since the start of the task

							public void run() {
								while (target.getType() == Material.AIR) {
									target = target.getRelative(BlockFace.DOWN);
								}

								if (ticks >= spawnDuration) {
									this.cancel(); // Stop the task after spawnDuration ticks
								}

								for (int i = 0; i < 100; i++) {
									// Generate random coordinates within a filled circle for each layer
									for (int j = 0; j < layers; j++) {
										double theta = 2 * Math.PI * Math.random();
										double r = cloudSize * Math.sqrt(Math.random()); // Use square root of random
																							// number for more
																							// cloud-like shape
										double x = target.getLocation().getX() + r * Math.cos(theta);
										double y = target.getLocation().getY() + cloudHeight + j; // Add j to create
																									// multiple layers
										double z = target.getLocation().getZ() + r * Math.sin(theta);

										player.getWorld().spawnParticle(Particle.SMOKE_NORMAL,
												new Location(player.getWorld(), x, y, z), 0);
										player.getWorld().spawnParticle(Particle.WATER_DROP,
												new Location(player.getWorld(), x, y, z), 0);
										player.getWorld().spawnParticle(Particle.ASH,
												new Location(player.getWorld(), x, y, z), 0);
										player.getWorld().spawnParticle(Particle.REDSTONE,
												new Location(player.getWorld(), x, y, z), 0,
												new DustOptions(Color.fromRGB(47, 79, 79), 2));
									}
								}

								ticks++;
							}
						}.runTaskTimer(Main.getInstance(), 0, 1);

						Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
							Player player = event.getPlayer();
							Block target = player.getTargetBlock(null, 5);

							public void run() {

								while (target.getType() == Material.AIR) {
									target = target.getRelative(BlockFace.DOWN);
								}

								player.getWorld().strikeLightningEffect(target.getLocation());
								player.getWorld().strikeLightningEffect(target.getLocation());
								player.getWorld().strikeLightningEffect(target.getLocation());
								player.getWorld().strikeLightningEffect(target.getLocation());
								player.getWorld().playSound(player, Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 1, 0);
								player.getWorld().playSound(player, Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 1, 0);
								player.getWorld().playSound(player, Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 1, 0);
								player.getWorld().playSound(player, Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 1, 0);

								// Get all entities within a certain radius of the target location
								Collection<Entity> nearbyEntities = target.getLocation().getWorld()
										.getNearbyEntities(target.getLocation(), 5.0, 5.0, 5.0);

								for (Entity entity : nearbyEntities) {
									if (entity instanceof LivingEntity && !entity.equals(player)) {
										LivingEntity otherPlayer = (LivingEntity) entity;

										if (Teams.isInTeam(player)) {
											if (Teams.getTeamType(player) == Teams.getTeamType((Player) otherPlayer)) {
												return;
											}
										}

										// Apply damage to the player
										otherPlayer.damage(lightningBlastDMG);

										// Apply slowness effect to the player
										otherPlayer.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 254));
										otherPlayer.setFireTicks(20);
									}
								}
							}
						}, 20L);

					}
					CooldownManager.setCooldown(player.getUniqueId(), CooldownManager.CustomEffects.LIGHTNINGBLAST,
							lightningBlastCD);
				} else {
					player.sendMessage(
							ChatColor.RED + "This ability is on cooldown for "
									+ CooldownManager.getCooldown(player.getUniqueId(),
											CooldownManager.CustomEffects.LIGHTNINGBLAST)
									+ ChatColor.RED + " seconds.");
				}
			}
		} else if (a.equals(Action.RIGHT_CLICK_AIR) || a.equals(Action.RIGHT_CLICK_BLOCK)) {
			if (player.getInventory().getItemInMainHand().isSimilar(itemDictionary.thunderCore())) {
				if (!CooldownManager.hasCooldown(player.getUniqueId(), CooldownManager.CustomEffects.THUNDERLANCE)) {

					Location eyeLocation = player.getEyeLocation();
					Vector direction = eyeLocation.getDirection();

					player.getWorld().playSound(player, Sound.ENTITY_GUARDIAN_ATTACK, 10, 2);

					new BukkitRunnable() {

						public void run() {
							for (int i = 0; i < 15; i++) {

								Location location = eyeLocation.add(direction);
								player.getWorld().spawnParticle(Particle.REDSTONE, location, 10, 0, 0, 0, 0,
										new DustOptions(Color.YELLOW, 1));

								for (Entity e : location.getWorld().getEntities()) {
									if (e instanceof Player && !e.equals(player)) {
										if (e.getLocation().distance(location) < 2.0) {
											Damageable d = (Damageable) e;

											if (Teams.isInTeam(player)) {
												if (Teams.getTeamType(player) == Teams.getTeamType((Player) d)) {
													return;
												}
											}
											Player p = (Player) d;
											p.damage(thunderLanceDMG, player);
											p.setFireTicks(20 * 1);
											p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20 * 3, 254));
											p.getWorld().strikeLightningEffect(p.getLocation());
										}
									}
								}
							}
						}

					}.runTaskLater(Main.getInstance(), 10);

					CooldownManager.setCooldown(player.getUniqueId(), CooldownManager.CustomEffects.THUNDERLANCE,
							thunderLanceCD);

				} else {
					player.sendMessage(ChatColor.RED + "This ability is on cooldown for " + CooldownManager
							.getCooldown(player.getUniqueId(), CooldownManager.CustomEffects.THUNDERLANCE)
							+ ChatColor.RED + " seconds.");
				}
			}

		}
	}

	@EventHandler
	public void iceClassAbilities(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		Action a = event.getAction();

		if (a.equals(Action.LEFT_CLICK_AIR) || a.equals(Action.LEFT_CLICK_BLOCK)) {
			if (player.getInventory().getItemInMainHand().isSimilar(itemDictionary.iceCore())) {
				if (!CooldownManager.hasCooldown(player.getUniqueId(), CooldownManager.CustomEffects.ICEAGE)) {

					int ICERADIUS = 5;

					for (Entity e : player.getWorld().getNearbyEntities(player.getLocation(), ICERADIUS, ICERADIUS,
							ICERADIUS)) {
						if (!e.equals(player) && e instanceof LivingEntity || e instanceof Player) {
							if (Teams.isInTeam((Player) e)) {
								if (Teams.getTeamType(player) == Teams.getTeamType((Player) e)) {
									break;
								} else {
									LivingEntity lEntity = (LivingEntity) e;
									lEntity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 5 * 20, 255));
									lEntity.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 5 * 20, 255));
									lEntity.damage(iceAgeDMG);
								}
							}
						}
					}

					Location loc = player.getLocation();
					World world = loc.getWorld();
					Map<Location, Material> originalBlocks = new HashMap<>();
					Random rand = new Random();

					for (int x = -ICERADIUS; x <= ICERADIUS; x++) {
						for (int z = -ICERADIUS; z <= ICERADIUS; z++) {
							if (Math.abs(x) == ICERADIUS || Math.abs(z) == ICERADIUS) {
								if (rand.nextBoolean()) {
									continue;
								}
							}

							Location blockLoc = loc.clone().add(x, -1, z);
							Block block = world.getBlockAt(blockLoc);
							originalBlocks.put(blockLoc, block.getType());
							block.setType(Material.BLUE_ICE);
						}
					}

					Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
						@Override
						public void run() {
							for (Map.Entry<Location, Material> entry : originalBlocks.entrySet()) {
								world.getBlockAt(entry.getKey()).setType(entry.getValue());
							}
						}
					}, 5 * 20L);

					CooldownManager.setCooldown(player.getUniqueId(), CooldownManager.CustomEffects.ICEAGE, iceAgeCD);
				} else {
					player.sendMessage(ChatColor.RED + "This ability is on cooldown for "
							+ CooldownManager.getCooldown(player.getUniqueId(), CooldownManager.CustomEffects.ICEAGE)
							+ ChatColor.RED + " seconds.");
				}
			}
		} else if (a.equals(Action.RIGHT_CLICK_AIR) || a.equals(Action.RIGHT_CLICK_BLOCK)) {
			if (player.getInventory().getItemInMainHand().isSimilar(itemDictionary.iceCore())) {
				if (!CooldownManager.hasCooldown(player.getUniqueId(), CooldownManager.CustomEffects.GLACIALPATH)) {

					Block targetBlock = player.getTargetBlock(null, 100);
					Location center = targetBlock.getLocation();

					// Create the sphere
					int radius = 4; // Set the radius of your sphere
					createHollowSphere(center, Material.ICE, radius);

					// Schedule the sphere to be replaced with the original blocks after x seconds
					new BukkitRunnable() {
						@Override
						public void run() {
							restoreBlocks(center, radius);
						}
					}.runTaskLater(Main.getInstance(), 20 * 5);

					CooldownManager.setCooldown(player.getUniqueId(), CooldownManager.CustomEffects.GLACIALPATH,
							glacialSphereCD);

				} else {
					player.sendMessage(ChatColor.RED + "This ability is on cooldown for " + CooldownManager
							.getCooldown(player.getUniqueId(), CooldownManager.CustomEffects.GLACIALPATH)
							+ ChatColor.RED + " seconds.");
				}
			}
		}
	}

	@EventHandler
	public void necromancerClassAbilities(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		Action a = event.getAction();

		if (a.equals(Action.LEFT_CLICK_AIR) || a.equals(Action.LEFT_CLICK_BLOCK)) {
			if (player.getInventory().getItemInMainHand().isSimilar(itemDictionary.necromancerCore())) {
				if (!CooldownManager.hasCooldown(player.getUniqueId(), CooldownManager.CustomEffects.RAISINGDEATH)) {

					Location loc = player.getLocation();
					Vector dir = loc.getDirection();

					// Calculate the starting point 3 blocks in front of the player
					Location start = loc.add(dir.multiply(3));

					// Spawn the Evoker Fangs in a 3x3 grid
					for (int x = -1; x <= 1; x++) {
						for (int z = -1; z <= 1; z++) {
							Location spawnLoc = start.clone().add(x, 0, z);
							EvokerFangs evokerFangs = (EvokerFangs) player.getWorld().spawnEntity(spawnLoc,
									EntityType.EVOKER_FANGS);
							evokerFangsOwners.put(evokerFangs.getUniqueId(), player);
						}
					}

					CooldownManager.setCooldown(player.getUniqueId(), CooldownManager.CustomEffects.RAISINGDEATH,
							raisingDeathCD);
				} else {
					player.sendMessage(ChatColor.RED + "This ability is on cooldown for " + CooldownManager
							.getCooldown(player.getUniqueId(), CooldownManager.CustomEffects.RAISINGDEATH)
							+ ChatColor.RED + " seconds.");
				}
			}
		} else if (a.equals(Action.RIGHT_CLICK_AIR) || a.equals(Action.RIGHT_CLICK_BLOCK)) {
			if (player.getInventory().getItemInMainHand().isSimilar(itemDictionary.necromancerCore())) {
				if (!CooldownManager.hasCooldown(player.getUniqueId(), CooldownManager.CustomEffects.ATTUNEDSOULS)) {

					BukkitRunnable runnable = new BukkitRunnable() {
						double t = 0;

						public void run() {
							t++;

							if (t >= 30) {
								this.cancel();
								healingEffectPlayers.remove(player.getUniqueId());
							} else {
								if (t >= 20) {
									player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1, 1);
								}
								player.sendTitle(ChatColor.DARK_RED + "" + (int) (30 - t) + "",
										ChatColor.RED + "Time left!", 1, 20, 1);
							}
						}

					};

					runnable.runTaskTimer(Main.getInstance(), 0, 20);
					healingEffectPlayers.put(player.getUniqueId(), runnable);

					CooldownManager.setCooldown(player.getUniqueId(), CooldownManager.CustomEffects.ATTUNEDSOULS,
							attunedSoulsCD);
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		Player killer = event.getEntity().getKiller();

		if (killer != null && healingEffectPlayers.containsKey(killer.getUniqueId())) {
			killer.setHealth(Math.min(killer.getHealth() + 4, killer.getMaxHealth()));
			killer.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.GRAY + "+4 " + ChatColor.RED + "♥"));

		}
	}

	@EventHandler
	public void damageFromEvokerFangs(EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof EvokerFangs && event.getEntity() instanceof Player) {
			EvokerFangs evokFangs = (EvokerFangs) event.getDamager();
			Player player = (Player) event.getEntity();

			Player caster = evokerFangsOwners.get(evokFangs.getUniqueId());
			if (Teams.getTeamType(caster) == Teams.getTeamType(player)) {
				event.setCancelled(true);
			} else {
				event.setDamage(raisingDeathDMG);
				player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 20 * 10, 0));
			}
		}
	}

	Map<Location, Material> originalBlocks = new HashMap<>();

	public void createHollowSphere(Location center, Material blockType, int radius) {
		for (int x = -radius; x <= radius; x++) {
			for (int y = -radius; y <= radius; y++) {
				for (int z = -radius; z <= radius; z++) {
					Location loc = center.clone().add(x, y, z);
					if (center.distance(loc) <= radius && center.distance(loc) > radius - 1) {
						// Save the original block state
						originalBlocks.put(loc, loc.getBlock().getType());

						// Set the new block
						loc.getBlock().setType(blockType);
					}
				}
			}
		}
	}

	public void restoreBlocks(Location center, int radius) {
		for (int x = -radius; x <= radius; x++) {
			for (int y = -radius; y <= radius; y++) {
				for (int z = -radius; z <= radius; z++) {
					Location loc = center.clone().add(x, y, z);
					if (center.distance(loc) <= radius && center.distance(loc) > radius - 1) {
						// Restore the original block state
						if (originalBlocks.containsKey(loc)) {
							loc.getBlock().setType(originalBlocks.get(loc));
						}
					}
				}
			}
		}
	}

}
