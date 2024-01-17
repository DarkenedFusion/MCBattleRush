
package mcbattlerush;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class ItemDictionary {

	public ItemStack airToken() {
		ItemStack token = new ItemStack(Material.LIGHT_GRAY_DYE);
		ItemMeta meta = token.getItemMeta();

		meta.setDisplayName(ChatColor.GRAY + "Air Class Token");
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.addItemFlags(ItemFlag.HIDE_DYE);

		token.setItemMeta(meta);
		return token;
	}

	public ItemStack fireToken() {
		ItemStack token = new ItemStack(Material.ORANGE_DYE);
		ItemMeta meta = token.getItemMeta();

		meta.setDisplayName(ChatColor.GRAY + "Fire Class Token");
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.addItemFlags(ItemFlag.HIDE_DYE);

		token.setItemMeta(meta);
		return token;
	}

	public ItemStack earthToken() {
		ItemStack token = new ItemStack(Material.BROWN_DYE);
		ItemMeta meta = token.getItemMeta();

		meta.setDisplayName(ChatColor.GRAY + "Earth Class Token");
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.addItemFlags(ItemFlag.HIDE_DYE);

		token.setItemMeta(meta);
		return token;
	}

	public ItemStack rocketeerToken() {
		ItemStack token = new ItemStack(Material.SPYGLASS);
		ItemMeta meta = token.getItemMeta();

		meta.setDisplayName(ChatColor.GREEN + "Rocketeer Class Token");
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.addItemFlags(ItemFlag.HIDE_DYE);

		token.setItemMeta(meta);
		return token;
	}

	public ItemStack thunderToken() {
		ItemStack token = new ItemStack(Material.YELLOW_DYE);
		ItemMeta meta = token.getItemMeta();

		meta.setDisplayName(ChatColor.BLUE + "Thunder Class Token");
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.addItemFlags(ItemFlag.HIDE_DYE);

		token.setItemMeta(meta);
		return token;
	}
	
	public ItemStack iceToken() {
		ItemStack token = new ItemStack(Material.LIGHT_BLUE_DYE);
		ItemMeta meta = token.getItemMeta();

		meta.setDisplayName(ChatColor.GREEN + "Ice Class Token");
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.addItemFlags(ItemFlag.HIDE_DYE);

		token.setItemMeta(meta);
		return token;
	}
	
	public ItemStack necromancerToken() {
		ItemStack token = new ItemStack(Material.BLACK_DYE);
		ItemMeta meta = token.getItemMeta();

		meta.setDisplayName(ChatColor.BLUE + "Necromancer Class Token");
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.addItemFlags(ItemFlag.HIDE_DYE);

		token.setItemMeta(meta);
		return token;
	}

	public ItemStack redHelmet() {
		ItemStack redHelmet = new ItemStack(Material.LEATHER_HELMET);
		LeatherArmorMeta meta = (LeatherArmorMeta) redHelmet.getItemMeta();

		meta.setColor(Color.RED);
		meta.setDisplayName(ChatColor.RED + "Red Team Helmet");
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.addItemFlags(ItemFlag.HIDE_DYE);

		redHelmet.setItemMeta(meta);
		return redHelmet;
	}

	public ItemStack redChestplate() {
		ItemStack redChestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta meta = (LeatherArmorMeta) redChestplate.getItemMeta();

		meta.setColor(Color.RED);
		meta.setDisplayName(ChatColor.RED + "Red Team Chestplate");
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.addItemFlags(ItemFlag.HIDE_DYE);

		redChestplate.setItemMeta(meta);
		return redChestplate;
	}

	public ItemStack redLeggings() {
		ItemStack redLeggings = new ItemStack(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta meta = (LeatherArmorMeta) redLeggings.getItemMeta();

		meta.setColor(Color.RED);
		meta.setDisplayName(ChatColor.RED + "Red Team Leggings");
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.addItemFlags(ItemFlag.HIDE_DYE);

		redLeggings.setItemMeta(meta);
		return redLeggings;
	}

	public ItemStack redBoots() {
		ItemStack redBoots = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta meta = (LeatherArmorMeta) redBoots.getItemMeta();

		meta.setColor(Color.RED);
		meta.setDisplayName(ChatColor.RED + "Red Team Boots");
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.addItemFlags(ItemFlag.HIDE_DYE);

		redBoots.setItemMeta(meta);
		return redBoots;
	}

	public ItemStack blueHelmet() {
		ItemStack blueHelmet = new ItemStack(Material.LEATHER_HELMET);
		LeatherArmorMeta meta = (LeatherArmorMeta) blueHelmet.getItemMeta();

		meta.setColor(Color.BLUE);
		meta.setDisplayName(ChatColor.BLUE + "Blue Team Helmet");
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.addItemFlags(ItemFlag.HIDE_DYE);

		blueHelmet.setItemMeta(meta);
		return blueHelmet;
	}

	public ItemStack blueChestplate() {
		ItemStack blueChestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta meta = (LeatherArmorMeta) blueChestplate.getItemMeta();

		meta.setColor(Color.BLUE);
		meta.setDisplayName(ChatColor.BLUE + "Blue Team Chestplate");
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.addItemFlags(ItemFlag.HIDE_DYE);

		blueChestplate.setItemMeta(meta);
		return blueChestplate;
	}

	public ItemStack blueLeggings() {
		ItemStack blueLeggings = new ItemStack(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta meta = (LeatherArmorMeta) blueLeggings.getItemMeta();

		meta.setColor(Color.BLUE);
		meta.setDisplayName(ChatColor.BLUE + "Blue Team Leggings");
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.addItemFlags(ItemFlag.HIDE_DYE);

		blueLeggings.setItemMeta(meta);
		return blueLeggings;
	}

	public ItemStack blueBoots() {
		ItemStack blueBoots = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta meta = (LeatherArmorMeta) blueBoots.getItemMeta();

		meta.setColor(Color.BLUE);
		meta.setDisplayName(ChatColor.BLUE + "Blue Team Boots");
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.addItemFlags(ItemFlag.HIDE_DYE);

		blueBoots.setItemMeta(meta);
		return blueBoots;
	}

	public ItemStack classStar() {
		ItemStack classStar = new ItemStack(Material.NETHER_STAR);
		ItemMeta meta = classStar.getItemMeta();

		classStar.addUnsafeEnchantment(Enchantment.DURABILITY, 1);

		meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Class Star");
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

		classStar.setItemMeta(meta);
		return classStar;
	}

	public ItemStack spyGlass1() {
		ItemStack spyGlass = new ItemStack(Material.SPYGLASS);
		ItemMeta meta = spyGlass.getItemMeta();
		AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "generic.generic.movement_speed", 0.05,
				AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.OFF_HAND);

		meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Spyglass");
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, modifier);

		spyGlass.setItemMeta(meta);
		return spyGlass;
	}
	public ItemStack spyGlass2() {
		ItemStack spyGlass = new ItemStack(Material.SPYGLASS);
		ItemMeta meta = spyGlass.getItemMeta();
		AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "generic.generic.movement_speed", 0.10,
				AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.OFF_HAND);

		meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Spyglass");
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, modifier);

		spyGlass.setItemMeta(meta);
		return spyGlass;
	}
	public ItemStack spyGlass3() {
		ItemStack spyGlass = new ItemStack(Material.SPYGLASS);
		ItemMeta meta = spyGlass.getItemMeta();
		AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "generic.generic.movement_speed", 0.15,
				AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.OFF_HAND);

		meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Spyglass");
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, modifier);

		spyGlass.setItemMeta(meta);
		return spyGlass;
	}
	public ItemStack spyGlass4() {
		ItemStack spyGlass = new ItemStack(Material.SPYGLASS);
		ItemMeta meta = spyGlass.getItemMeta();
		AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "generic.generic.movement_speed", 0.20,
				AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.OFF_HAND);

		meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Spyglass");
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, modifier);

		spyGlass.setItemMeta(meta);
		return spyGlass;
	}
	public ItemStack spyGlass5() {
		ItemStack spyGlass = new ItemStack(Material.SPYGLASS);
		ItemMeta meta = spyGlass.getItemMeta();
		AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "generic.generic.movement_speed", 0.25,
				AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.OFF_HAND);

		meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Spyglass");
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, modifier);

		spyGlass.setItemMeta(meta);
		return spyGlass;
	}
	
	public ItemStack spyGlass6() {
		ItemStack spyGlass = new ItemStack(Material.SPYGLASS);
		ItemMeta meta = spyGlass.getItemMeta();
		AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "generic.generic.movement_speed", 0.30,
				AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.OFF_HAND);

		meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Spyglass");
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, modifier);

		spyGlass.setItemMeta(meta);
		return spyGlass;
	}

	public ItemStack fireCore() {
		ItemStack fireCore = new ItemStack(Material.BLAZE_POWDER);
		ItemMeta meta = fireCore.getItemMeta();
		List<String> lore = new ArrayList<>();

		fireCore.addUnsafeEnchantment(Enchantment.DURABILITY, 1);

		lore.add("");
		lore.add(ChatColor.DARK_GRAY + "§m--------------------");
		lore.add(ChatColor.GOLD + "" + ChatColor.BOLD + "Left Click " + ChatColor.DARK_GRAY + "⇒ " + ChatColor.GOLD
				+ "Fire Fist");
		lore.add(ChatColor.GRAY + "Launches a flaming projectile at enemies.");
		lore.add("");
		lore.add(ChatColor.RED + "+3.0 " + ChatColor.GRAY + "Damage");
		lore.add(ChatColor.RED + "20 " + ChatColor.GRAY + "Cooldown");
		lore.add(ChatColor.DARK_GRAY + "§m--------------------");
		lore.add(ChatColor.GOLD + "" + ChatColor.BOLD + "Right Click " + ChatColor.DARK_GRAY + "⇒ " + ChatColor.GOLD
				+ "Flame Emperor");
		lore.add(ChatColor.GRAY + "Launches a huge flaming");
		lore.add(ChatColor.GRAY + "projectiles at enemies.");
		lore.add("");
		lore.add(ChatColor.RED + "+5.0 " + ChatColor.GRAY + "Damage");
		lore.add(ChatColor.RED + "35 " + ChatColor.GRAY + "Cooldown");
		lore.add(ChatColor.DARK_GRAY + "§m--------------------");

		meta.setDisplayName(ChatColor.GOLD + "Fire Core");
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

		meta.setLore(lore);
		fireCore.setItemMeta(meta);
		return fireCore;
	}

	public ItemStack basicSword() {
		ItemStack basicSword = new ItemStack(Material.WOODEN_SWORD);
		ItemMeta meta = basicSword.getItemMeta();
		List<String> lore = new ArrayList<>();

		AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "generic.attack_damage", 1.0,
				AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);

		lore.add("");
		lore.add(ChatColor.DARK_GRAY + "§m--------------------");
		lore.add(ChatColor.GOLD + "" + ChatColor.BOLD + "Left Click " + ChatColor.DARK_GRAY + "⇒ " + ChatColor.WHITE
				+ "Sword Swing");
		lore.add(ChatColor.GRAY + "A basic sword attack.");
		lore.add("");
		lore.add(ChatColor.RED + "+1.0 " + ChatColor.GRAY + "Damage");
		lore.add(ChatColor.DARK_GRAY + "§m--------------------");

		meta.setDisplayName(ChatColor.WHITE + "Basic Sword");
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier);

		meta.setLore(lore);
		basicSword.setItemMeta(meta);
		return basicSword;
	}

	public ItemStack airCore() {
		ItemStack airCore = new ItemStack(Material.LIGHT_GRAY_DYE);
		ItemMeta meta = airCore.getItemMeta();
		List<String> lore = new ArrayList<>();

		airCore.addUnsafeEnchantment(Enchantment.DURABILITY, 1);

		lore.add("");
		lore.add(ChatColor.DARK_GRAY + "§m--------------------");
		lore.add(ChatColor.GOLD + "" + ChatColor.BOLD + "Left Click " + ChatColor.DARK_GRAY + "⇒ " + ChatColor.GOLD
				+ "Air Dash");
		lore.add(ChatColor.GRAY + "Boosts player in the direction");
		lore.add(ChatColor.GRAY + "they are facing.");
		lore.add("");
		lore.add(ChatColor.RED + "15 " + ChatColor.GRAY + "Cooldown");
		lore.add(ChatColor.DARK_GRAY + "§m--------------------");
		lore.add(ChatColor.GOLD + "" + ChatColor.BOLD + "Right Click " + ChatColor.DARK_GRAY + "⇒ " + ChatColor.GOLD
				+ "Wind Vortex");
		lore.add(ChatColor.GRAY + "Creates a air vortex that");
		lore.add(ChatColor.GRAY + "launches enemies away from you.");
		lore.add("");
		lore.add(ChatColor.RED + "30 " + ChatColor.GRAY + "Cooldown");
		lore.add(ChatColor.DARK_GRAY + "§m--------------------");

		meta.setDisplayName(ChatColor.GOLD + "Air Core");
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

		meta.setLore(lore);
		airCore.setItemMeta(meta);
		return airCore;
	}

	public ItemStack earthCore() {
		ItemStack earthCore = new ItemStack(Material.BROWN_DYE);
		ItemMeta meta = earthCore.getItemMeta();
		List<String> lore = new ArrayList<>();

		earthCore.addUnsafeEnchantment(Enchantment.DURABILITY, 1);

		lore.add("");
		lore.add(ChatColor.DARK_GRAY + "§m--------------------");
		lore.add(ChatColor.GOLD + "" + ChatColor.BOLD + "Left Click " + ChatColor.DARK_GRAY + "⇒ " + ChatColor.GOLD
				+ "Rock Wall");
		lore.add(ChatColor.GRAY + "Transorm the ground into");
		lore.add(ChatColor.GRAY + "a 3x3 wall.");
		lore.add("");
		lore.add(ChatColor.RED + "25 " + ChatColor.GRAY + "Cooldown");
		lore.add(ChatColor.DARK_GRAY + "§m--------------------");
		lore.add(ChatColor.GOLD + "" + ChatColor.BOLD + "Right Click " + ChatColor.DARK_GRAY + "⇒ " + ChatColor.GOLD
				+ "Earth Dome");
		lore.add(ChatColor.GRAY + "Surround the user in a");
		lore.add(ChatColor.GRAY + "sphere of earth.");
		lore.add("");
		lore.add(ChatColor.RED + "40 " + ChatColor.GRAY + "Cooldown");
		lore.add(ChatColor.DARK_GRAY + "§m--------------------");

		meta.setDisplayName(ChatColor.GOLD + "Earth Core");
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

		meta.setLore(lore);
		earthCore.setItemMeta(meta);
		return earthCore;
	}

	public ItemStack rocketeerCore() {
		ItemStack rocketeerCore = new ItemStack(Material.FIREWORK_STAR);
		ItemMeta meta = rocketeerCore.getItemMeta();
		List<String> lore = new ArrayList<>();

		rocketeerCore.addUnsafeEnchantment(Enchantment.DURABILITY, 1);

		lore.add("");
		lore.add(ChatColor.DARK_GRAY + "§m--------------------");
		lore.add(ChatColor.GOLD + "" + ChatColor.BOLD + "Left Click " + ChatColor.DARK_GRAY + "⇒ " + ChatColor.GOLD
				+ "Spyglass");
		lore.add(ChatColor.GRAY + "Recieve a spyglass that gives");
		lore.add(ChatColor.GRAY + "+5% movement speed for 10 seconds");
		lore.add("");
		lore.add(ChatColor.RED + "25 " + ChatColor.GRAY + "Cooldown");
		lore.add(ChatColor.DARK_GRAY + "§m--------------------");
		lore.add(ChatColor.GOLD + "" + ChatColor.BOLD + "Right Click " + ChatColor.DARK_GRAY + "⇒ " + ChatColor.GOLD
				+ "Rocket Launcher");
		lore.add(ChatColor.GRAY + "Gain a triple shot rocket launcher");
		lore.add(ChatColor.GRAY + "that can explode enemies or launch yourself.");
		lore.add("");
		lore.add(ChatColor.RED + "+2.5 " + ChatColor.GRAY + "Damage");
		lore.add(ChatColor.RED + "30 " + ChatColor.GRAY + "Cooldown");
		lore.add(ChatColor.DARK_GRAY + "§m--------------------");

		meta.setDisplayName(ChatColor.GOLD + "Rocketeer Core");
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

		meta.setLore(lore);
		rocketeerCore.setItemMeta(meta);
		return rocketeerCore;
	}

	public ItemStack thunderCore() {
		ItemStack thunderCore = new ItemStack(Material.YELLOW_DYE);
		ItemMeta meta = thunderCore.getItemMeta();
		List<String> lore = new ArrayList<>();

		thunderCore.addUnsafeEnchantment(Enchantment.DURABILITY, 1);

		lore.add("");
		lore.add(ChatColor.DARK_GRAY + "§m--------------------");
		lore.add(ChatColor.GOLD + "" + ChatColor.BOLD + "Left Click " + ChatColor.DARK_GRAY + "⇒ " + ChatColor.GOLD
				+ "Lightning Blast");
		lore.add(ChatColor.GRAY + "A powerful AOE strike of lightning");
		lore.add(ChatColor.GRAY + "stuns enemies on hit.");
		lore.add("");
		lore.add(ChatColor.RED + "+5.5 " + ChatColor.GRAY + "Damage");
		lore.add(ChatColor.RED + "30 " + ChatColor.GRAY + "Cooldown");
		lore.add(ChatColor.DARK_GRAY + "§m--------------------");
		lore.add(ChatColor.GOLD + "" + ChatColor.BOLD + "Right Click " + ChatColor.DARK_GRAY + "⇒ " + ChatColor.GOLD
				+ "Thunder Lance");
		lore.add(ChatColor.GRAY + "A lightning beam that when it hits");
		lore.add(ChatColor.GRAY + "deals massive damage and stuns enemy.");
		lore.add("");
		lore.add(ChatColor.RED + "+7.5 " + ChatColor.GRAY + "Damage");
		lore.add(ChatColor.RED + "40 " + ChatColor.GRAY + "Cooldown");
		lore.add(ChatColor.DARK_GRAY + "§m--------------------");

		meta.setDisplayName(ChatColor.GOLD + "Thunder Core");
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

		meta.setLore(lore);
		thunderCore.setItemMeta(meta);
		return thunderCore;
	}

	public ItemStack iceCore() {
		ItemStack iceCore = new ItemStack(Material.LIGHT_BLUE_DYE);
		ItemMeta meta = iceCore.getItemMeta();
		List<String> lore = new ArrayList<>();

		iceCore.addUnsafeEnchantment(Enchantment.DURABILITY, 1);

		lore.add("");
		lore.add(ChatColor.DARK_GRAY + "§m--------------------");
		lore.add(ChatColor.GOLD + "" + ChatColor.BOLD + "Left Click " + ChatColor.DARK_GRAY + "⇒ " + ChatColor.GOLD
				+ "Ice Age");
		lore.add(ChatColor.GRAY + "Freeze enemies nearby");
		lore.add(ChatColor.GRAY + "and turns the floor to ice.");
		lore.add("");
		lore.add(ChatColor.RED + "+3.5 " + ChatColor.GRAY + "Damage");
		lore.add(ChatColor.RED + "30 " + ChatColor.GRAY + "Cooldown");
		lore.add(ChatColor.DARK_GRAY + "§m--------------------");
		lore.add(ChatColor.GOLD + "" + ChatColor.BOLD + "Right Click " + ChatColor.DARK_GRAY + "⇒ " + ChatColor.GOLD
				+ "Glacial Sphere");
		lore.add(ChatColor.GRAY + "Trap enemies in a sphere of ice.");
		lore.add("");
		lore.add(ChatColor.RED + "25 " + ChatColor.GRAY + "Cooldown");
		lore.add(ChatColor.DARK_GRAY + "§m--------------------");

		meta.setDisplayName(ChatColor.GOLD + "Ice Core");
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

		meta.setLore(lore);
		iceCore.setItemMeta(meta);
		return iceCore;
	}
	
	public ItemStack necromancerCore() {
		ItemStack necroCore = new ItemStack(Material.BLACK_DYE);
		ItemMeta meta = necroCore.getItemMeta();
		List<String> lore = new ArrayList<>();

		necroCore.addUnsafeEnchantment(Enchantment.DURABILITY, 1);

		lore.add("");
		lore.add(ChatColor.DARK_GRAY + "§m--------------------");
		lore.add(ChatColor.GOLD + "" + ChatColor.BOLD + "Left Click " + ChatColor.DARK_GRAY + "⇒ " + ChatColor.GOLD
				+ "Raising Death");
		lore.add(ChatColor.GRAY + "Summons a 3x3 group of evoker fangs");
		lore.add(ChatColor.GRAY + "that slows and fatigues enemies.");
		lore.add("");
		lore.add(ChatColor.RED + "+6.0 " + ChatColor.GRAY + "Damage");
		lore.add(ChatColor.RED + "35 " + ChatColor.GRAY + "Cooldown");
		lore.add(ChatColor.DARK_GRAY + "§m--------------------");
		lore.add(ChatColor.GOLD + "" + ChatColor.BOLD + "Right Click " + ChatColor.DARK_GRAY + "⇒ " + ChatColor.GOLD
				+ "Attuned Souls");
		lore.add(ChatColor.GRAY + "30 second time period to kill players");
		lore.add(ChatColor.GRAY + "where every kill heals 2 hearts.");
		lore.add("");
		lore.add(ChatColor.RED + "60 " + ChatColor.GRAY + "Cooldown");
		lore.add(ChatColor.DARK_GRAY + "§m--------------------");

		meta.setDisplayName(ChatColor.GOLD + "Necromancer Core");
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

		meta.setLore(lore);
		necroCore.setItemMeta(meta);
		return necroCore;
	}
}
