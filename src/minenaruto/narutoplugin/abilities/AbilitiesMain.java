package minenaruto.narutoplugin.abilities;

import java.util.*;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import minenaruto.narutoplugin.iditems.Item;
import minenaruto.narutoplugin.models.ModelsMain;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.block.Block;

import org.bukkit.craftbukkit.v1_16_R3.entity.CraftEntity;
import org.bukkit.entity.*;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.BlockIterator;

import com.sk89q.worldguard.LocalPlayer;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.flags.StateFlag;


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


    public static boolean hasPvpZone(Entity player) {
        if(player instanceof Player) {

            com.sk89q.worldedit.util.Location loc = BukkitAdapter.adapt(player.getLocation());
            RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
            RegionQuery query = container.createQuery();

            ApplicableRegionSet ars = query.getApplicableRegions(loc);
            for (ProtectedRegion rg : ars.getRegions()) {
                StateFlag.State pvpState = rg.getFlag(Flags.PVP);

                if (pvpState != null && pvpState == StateFlag.State.DENY) {
                    return false;
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
