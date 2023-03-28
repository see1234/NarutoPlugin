package minenaruto.narutoplugin.spawnmob;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.ItemStack;

import net.citizensnpcs.api.event.NPCDeathEvent;
import net.citizensnpcs.api.npc.NPC;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.main.Main;

public class MobListener implements Listener {
	@EventHandler
	public void npcDeath(final NPCDeathEvent e) {
		final NPC npc = e.getNPC();
		final Player p = e.getEvent().getEntity().getKiller();
		if (npc.getEntity() != null) {

			if (npc.getName().startsWith("§7[NPC]")) {
				for (final ItemStack item : ((Player) npc.getEntity()).getInventory().getContents()) {
					if (item != null) {
						item.setType(Material.AIR);
						((Player) npc.getEntity()).getInventory().setHelmet(new ItemStack(Material.AIR));
						((Player) npc.getEntity()).getInventory().setChestplate(new ItemStack(Material.AIR));
						((Player) npc.getEntity()).getInventory().setLeggings(new ItemStack(Material.AIR));
						((Player) npc.getEntity()).getInventory().setBoots(new ItemStack(Material.AIR));
						((Player) npc.getEntity()).getInventory().setItemInMainHand(new ItemStack(Material.AIR));

					}
				}
			}
			if (npc.getName().startsWith("§7[NPC]")) {
				if (p != null) {
					NarutoPlayer player = NarutoPlayer.getNarutoPlayer(p.getName());
					player.addExp(3);
				}
				npc.destroy();
			}

		}
	}

 

	@EventHandler
	public void onSpawn(EntitySpawnEvent event) {
		if (event.getEntity() != null && event.getEntity().getType() == EntityType.ZOMBIE
				&& event.getEntity().isDead() == false) {
			if (new Random().nextInt(100) < 1) {
				ShinobiMob.spawnEntity("§7[NPC] Шиноби", 100, 10, event.getEntity().getLocation(), "9234");
				event.getEntity().remove();
				return;
			}

		}
	}

}
