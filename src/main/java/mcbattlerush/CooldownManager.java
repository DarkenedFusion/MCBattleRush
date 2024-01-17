package mcbattlerush;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.scheduler.BukkitRunnable;

public class CooldownManager {
	
	private static final LinkedHashMap<UUID, LinkedHashMap<CustomEffects, CooldownInfo>> cooldowns = new LinkedHashMap<>();

    public enum CustomEffects {
        FIREFIST, FLAMEEMPEROR, AIRDASH, WINDVORTEX, ROCKWALL, EARTHDOME, SPYGLASS, ROCKETLAUNCHER, 
        LIGHTNINGBLAST, THUNDERLANCE, ICEAGE, GLACIALPATH, RAISINGDEATH, ATTUNEDSOULS
    }

    public static class CooldownInfo {
        private final long StartTime;
        private final long Cooldown;

        public CooldownInfo(long cooldown) {
            this.StartTime = System.currentTimeMillis();
            this.Cooldown = cooldown;
        }

        public long getStartTime() {
            return StartTime;
        }

        public long getCooldown() {
            return Cooldown;
        }
    }

    static {
    	new BukkitRunnable() {
    	    public void run() {
    	        LinkedHashMap<UUID, LinkedHashMap<CustomEffects, CooldownInfo>> cooldownList = new LinkedHashMap<>(cooldowns);

    	        for (UUID playerId : new HashSet<>(cooldownList.keySet())) {
    	            LinkedHashMap<CustomEffects, CooldownInfo> cooldownInfoList = cooldownList.get(playerId);

    	            for (CustomEffects effect : new HashSet<>(cooldownInfoList.keySet())) {
    	                CooldownInfo cooldownInfo = cooldownInfoList.get(effect);

    	                long time = System.currentTimeMillis() - cooldownInfo.getStartTime();

    	                if (time  >= (1000 * cooldownInfo.getCooldown()))
    	                    removeCooldown(playerId, effect);
    	            }
    	        }
    	    }
    	}.runTaskTimerAsynchronously(Main.getInstance(), 20L, 20L);

    }

    public static void setCooldown(UUID playerId, CustomEffects effect, long cooldown) {
        LinkedHashMap<CustomEffects, CooldownInfo> cooldownList = cooldowns.getOrDefault(playerId, new LinkedHashMap<CustomEffects, CooldownInfo>());
        cooldownList.put(effect, new CooldownInfo(cooldown));
        cooldowns.put(playerId, cooldownList);
    }

    public static boolean hasCooldown(UUID playerId, CustomEffects effect) {
        if (!cooldowns.containsKey(playerId))
            return false;
        return cooldowns.get(playerId).containsKey(effect);
    }

    public static int getCooldown(UUID playerId, CustomEffects effect) {
        if (!cooldowns.containsKey(playerId))
            return 0;

        if (!cooldowns.get(playerId).containsKey(effect))
            return 0;

        long elapsed = (System.currentTimeMillis() - cooldowns.get(playerId).get(effect).getStartTime()) / 1000;
        long cooldown = cooldowns.get(playerId).get(effect).getCooldown();
        return Math.toIntExact(cooldown - elapsed);
    }

    public static void removeCooldown(UUID playerId, CustomEffects effect) {
        LinkedHashMap<CustomEffects, CooldownInfo> cooldownList = cooldowns.getOrDefault(playerId, new LinkedHashMap<CustomEffects, CooldownInfo>());

        cooldownList.remove(effect);
        cooldowns.remove(playerId);
        if (cooldownList.size() > 0)
            cooldowns.put(playerId, cooldownList);
    }
    
    public static List<CustomEffects> getSkills(UUID playerId) {
        if (!cooldowns.containsKey(playerId))
            return new ArrayList<>(); // return an empty list if the player has no skills

        // Get the skills (enums) that the player has
        List<CustomEffects> skills = new ArrayList<>(cooldowns.get(playerId).keySet());
        return skills;
    }
}
