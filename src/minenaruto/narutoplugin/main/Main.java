package minenaruto.narutoplugin.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.abilities.basics.ShadowClon;
import minenaruto.narutoplugin.abilities.basics.earth.StoneArmor;
import minenaruto.narutoplugin.abilities.basics.earth.StoneBullets;
import minenaruto.narutoplugin.abilities.basics.earth.StoneHand;
import minenaruto.narutoplugin.abilities.basics.fire.FireElement;
import minenaruto.narutoplugin.abilities.basics.fire.FireElement2;
import minenaruto.narutoplugin.abilities.basics.fire.FireElement3;
import minenaruto.narutoplugin.abilities.basics.fire.FireElement4;
import minenaruto.narutoplugin.abilities.basics.lightning.LightningArmor;
import minenaruto.narutoplugin.abilities.basics.lightning.LightningShield;
import minenaruto.narutoplugin.abilities.models.FireBall;
import minenaruto.narutoplugin.abilities.models.Stone;
import minenaruto.narutoplugin.abilities.reningan.ReninganFly;
import minenaruto.narutoplugin.abilities.reningan.ReninganSaskeTeleport;
import minenaruto.narutoplugin.abilities.sharingan.Sharingan;
import minenaruto.narutoplugin.abilities.sharingan.SharinganItachi;
import minenaruto.narutoplugin.abilities.sharingan.SharinganItachiAmateracy;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import minenaruto.narutoplugin.abilities.AbilityListener;
import minenaruto.narutoplugin.dataplayer.Chakra;
import minenaruto.narutoplugin.dataplayer.PlayerListener;
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
	private ArrayList<AbilitiesMain> abilities = new ArrayList<AbilitiesMain>();
	public static FileConfiguration getCfg() { return cfg; }
	public void onEnable() {
	    instance = this;
		cfg = this.getConfig();
		this.saveDefaultConfig();
		registerEvents();
		registerCommands();
		registerModels();
		registerAbilities();
		new Chakra().runTaskTimer((Plugin)this, 20L, 20L);
	}

	public ArrayList<AbilitiesMain> getAbilities() {
		return abilities;
	}

	private void registerAbilities() {
		abilities.add(new SharinganItachi());
		abilities.add(new SharinganItachiAmateracy());
		abilities.add(new ShadowClon());
		abilities.add(new FireElement());
		abilities.add(new FireElement2());
		abilities.add(new FireElement3());
		abilities.add(new FireElement4());
		abilities.add(new LightningArmor());
		abilities.add(new LightningShield());
		abilities.add(new StoneBullets());
		abilities.add(new StoneArmor());
		abilities.add(new StoneHand());
		abilities.add(new Sharingan());
		abilities.add(new ReninganFly());
		abilities.add(new ReninganSaskeTeleport());
	}
	private void registerModels() {
		abilities.add(new Stone());
		abilities.add(new FireBall());

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
