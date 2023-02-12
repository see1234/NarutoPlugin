package minenaruto.narutoplugin.main;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.NPC;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import minenaruto.narutoplugin.abilities.AbilityListener;
import minenaruto.narutoplugin.dataplayer.Chakra;
import minenaruto.narutoplugin.dataplayer.PlayerListener;
import minenaruto.narutoplugin.iditems.DatabaseManager;
import minenaruto.narutoplugin.iditems.Item;
import minenaruto.narutoplugin.iditems.NarutoItemsCommand;
import minenaruto.narutoplugin.skillmenu.SkillListener;
import minenaruto.narutoplugin.skillmenu.Skillmenu;
import minenaruto.narutoplugin.spawnmob.MobListener;
import minenaruto.narutoplugin.spawnmob.ShinobiMob;

public class Main extends JavaPlugin {

	public static Main instance;
	public static Main getInstance() { return instance; }
	public static FileConfiguration cfg;
	public static FileConfiguration getCfg() { return cfg; }
	private DatabaseManager databaseManager;
	public void onEnable() {
	    instance = this;
		cfg = this.getConfig();
		this.saveDefaultConfig();
		databaseManager = new DatabaseManager(this);
		registerEvents();
		registerCommands();
		 
 
		new Chakra().runTaskTimer((Plugin)this, 20L, 20L);
		Item.loadNarutoItems();
	}
	private void registerCommands() {
		new NarutoItemsCommand().register();
	}
	private void registerEvents() {
		Bukkit.getPluginManager().registerEvents((Listener)new MobListener(), (Plugin)this);
		Bukkit.getPluginManager().registerEvents((Listener)new PlayerListener(), (Plugin)this);
		Bukkit.getPluginManager().registerEvents((Listener)new AbilityListener(), (Plugin)this);
		Bukkit.getPluginManager().registerEvents((Listener)new Skillmenu(), (Plugin)this);
		Bukkit.getPluginManager().registerEvents((Listener)new SkillListener(), (Plugin)this);
	}
	public void onDisable() {
		for(net.citizensnpcs.api.npc.NPC npc : ShinobiMob.npcs) {
			if(npc.isSpawned())  {
			npc.despawn();
			}
			npc.destroy();
			   
		}
		Chakra.chakraBar.keySet().forEach(key -> {
			
			Chakra.chakraBar.get(key).removeAll();
		});
		Chakra.chakraBar.clear();

		ShinobiMob.npcs.clear();
		databaseManager.closeConnection();
	}
	
	public DatabaseManager getDatabaseManager() {
		return databaseManager;
	}
    public static void saveCustomYml(final FileConfiguration ymlConfig, final File ymlFile) {
        try {
            ymlConfig.save(ymlFile);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static <T extends Entity> Collection<T> getNearbyEntities(Location l, Class<T> type, int radius) {
        World w = l.getWorld();
        return w.getEntitiesByClass(type).stream().filter(e -> e.getLocation().distance(l) <= (double)radius).collect(Collectors.toSet());
    }
}
