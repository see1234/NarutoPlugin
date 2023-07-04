package minenaruto.narutoplugin.spawnmob;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import net.citizensnpcs.trait.SkinTrait;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.mcmonkey.sentinel.SentinelTrait;
import org.mcmonkey.sentinel.targeting.SentinelTargetLabel;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.Trait;
import net.citizensnpcs.api.trait.trait.Equipment;

import minenaruto.narutoplugin.main.Main;

public class ShinobiMob {
	public static ArrayList<NPC> npcs = new ArrayList<NPC>();
	public static World w;
	public static World cw;
	public static Location[] forestLoc;
	public static Location[] caveLoc;

	public static Location getLoc(final double x, final double y, final double z) {
		return new Location(w, x, y, z);
	}

	public static Location getCLoc(final double x, final double y, final double z) {
		return new Location(cw, x, y, z);
	}

	public static void spawnEntity(final String name, final double health, final double damage, final Location location,
			final String s) {
		NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, name);
	 	((SkinTrait) npc.getTrait((Class) SkinTrait.class)).setSkinName(s);
		// ((Equipment)npc.getTrait((Class)Equipment.class)).set(Equipment.EquipmentSlot.HAND,
		// (ItemStack)CreateCustomItems.Swords.get("?7[?4UC?7] ?b\u041c\u0435\u0447
		// \u043d\u043f\u0441"));
		final SentinelTrait st = (SentinelTrait) npc.getTrait((Class) SentinelTrait.class);
		new SentinelTargetLabel("players").addToList(st.allTargets);
		st.setHealth(health);
		npc.spawn(location);
		st.spawnPoint = location;
		st.range = 15.0;
		st.damage = damage;
		st.speed = 1.5;
		st.setGuarding((UUID) null);

		npc.addTrait((Trait) st);
		npcs.add(npc);
		new BukkitRunnable() {

			@Override
			public void run() {
				if (npc != null) {

					npc.destroy();
					npcs.remove(npc);
					cancel();
					return;

				}

			}
		}.runTaskTimer(Main.getInstance(), 1000, 1000);
	}
}
