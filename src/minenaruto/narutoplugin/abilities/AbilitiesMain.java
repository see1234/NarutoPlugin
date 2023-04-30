package minenaruto.narutoplugin.abilities;

import java.util.*;

import minenaruto.narutoplugin.iditems.Item;
import minenaruto.narutoplugin.models.ModelsMain;
import minenaruto.narutoplugin.spawnmob.MobListener;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftEntity;
import org.bukkit.entity.*;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.BlockIterator;

import com.sk89q.worldguard.LocalPlayer;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.association.RegionAssociable;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
 

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.main.Main;

public abstract class AbilitiesMain implements Listener {
    public WeakHashMap<Player, ArmorStand> arm = new WeakHashMap<Player, ArmorStand>();
    public static WeakHashMap<Player, String> enabledController = new WeakHashMap<Player, String>();
    public static HashMap<Player, ArrayList<Block>> blocks = new HashMap<>();
	public abstract void RightClick(final Player player, NarutoPlayer pl);

	public abstract void RightPlusShift(final Player player, NarutoPlayer pl);
    public abstract Item getItem();

	public static HashMap<Entity, Player> getDamage = new HashMap<Entity, Player>();


    public HashMap<String, Item> getModels() {
        return ModelsMain.models;
    }

	public static HashMap<Player, Integer> scheduler = new HashMap<Player, Integer>();

	public static boolean hasPvpZone(final Entity entity) {
		if (Bukkit.getServer().getPluginManager().getPlugin("WorldGuard") != null) {
			RegionManager regionManager = WorldGuardPlugin.inst().getRegionManager(entity.getWorld());
			ApplicableRegionSet set = regionManager.getApplicableRegions(entity.getLocation());

			for (ProtectedRegion region : set) {
				if (region != null) {
					if (!set.allows(DefaultFlag.PVP)) {

						return false;
					}
				}
			}

		}
		return true;
	}
    public LivingEntity rayTraceEntity(final Player player, final int range) {
        final BlockIterator iterator = new BlockIterator(player.getWorld(), player.getEyeLocation().toVector(), player.getEyeLocation().getDirection(), 0.0, range);
        Chunk chunk = null;
        Entity[] entities = null;
        while (iterator.hasNext()) {
            final Location l = iterator.next().getLocation();
            if (chunk != l.getChunk()) {
                chunk = l.getChunk();
                entities = chunk.getEntities();
            }
            if (entities != null && entities.length > 0) {
                final Entity[] arr$ = entities;
                for (int len$ = entities.length, i$ = 0; i$ < len$; ++i$) {
                    final Entity entity = arr$[i$];
                    if (entity != player && entity instanceof LivingEntity && entity.getType() != EntityType.SQUID && l.getWorld() == entity.getLocation().getWorld() && l.distance(entity.getLocation()) < 1.5) {
                        if(!AbilityListener.hasPvpZone(entity)) {
                            return null;
                        }
                        return (LivingEntity)entity;
                    }
                }
            }
        }
        return null;
    }
    
    public LivingEntity rayTraceEntity(final CraftEntity bukkitEntity, final int range) {
        final BlockIterator iterator = new BlockIterator(bukkitEntity.getWorld(), ((LivingEntity)bukkitEntity).getEyeLocation().toVector(), ((LivingEntity)bukkitEntity).getEyeLocation().getDirection(), 0.0, range);
        Chunk chunk = null;
        Entity[] entities = null;
        while (iterator.hasNext()) {
            final Location l = iterator.next().getLocation();
            if (chunk != l.getChunk()) {
                chunk = l.getChunk();
                entities = chunk.getEntities();
            }
            if (entities != null && entities.length > 0) {
                final Entity[] arr$ = entities;
                for (int len$ = entities.length, i$ = 0; i$ < len$; ++i$) {
                    final Entity entity = arr$[i$];
                    if (entity != bukkitEntity && entity instanceof Player && entity.getType() != EntityType.SQUID && l.getWorld() == entity.getLocation().getWorld() && l.distance(entity.getLocation()) < 1.5) {
                        if(!AbilityListener.hasPvpZone(entity)) {
                            return null;
                        }
                        return (LivingEntity)entity;
                    }
                }
            }
        }
        return null;
    }
	public static void addDamageEntity(final Player player, final Entity entity, double damage) {
		if (entity.getName().startsWith("§7[§6Naruto§7]")) {
			if (!getDamage.containsValue(getDamage.get(player))) {
				getDamage.put(entity, player);
			} else if (getDamage.get(player) != player) {
				getDamage.remove(entity, getDamage.get(entity));
				getDamage.put(entity, player);
			}
			Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin) Main.getInstance(),
					() -> getDamage.remove(player, player), 600L);
		}
		AbilityListener.damageEntity(entity, player, damage);
	}


}
