package minenaruto.narutoplugin.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.abilities.Kekkei_Genkai.log.LogHandsOfWood;
import minenaruto.narutoplugin.abilities.Kekkei_Genkai.log.LogSharpNeedles;
import minenaruto.narutoplugin.abilities.Kekkei_Genkai.log.LogShield;
import minenaruto.narutoplugin.abilities.basics.ChakraController;
import minenaruto.narutoplugin.abilities.basics.ReplacementTechnique;
import minenaruto.narutoplugin.abilities.basics.ShadowClon;
import minenaruto.narutoplugin.abilities.basics.earth.StoneArmor;
import minenaruto.narutoplugin.abilities.basics.earth.StoneBroke;
import minenaruto.narutoplugin.abilities.basics.earth.StoneBullets;
import minenaruto.narutoplugin.abilities.basics.earth.StoneHand;
import minenaruto.narutoplugin.abilities.basics.fire.*;
import minenaruto.narutoplugin.abilities.basics.lightning.*;
import minenaruto.narutoplugin.abilities.basics.water.WaterHands;
import minenaruto.narutoplugin.abilities.basics.water.WaterJail;
import minenaruto.narutoplugin.abilities.basics.water.WaterPunch;
import minenaruto.narutoplugin.abilities.basics.water.WaterShark;
import minenaruto.narutoplugin.abilities.basics.wind.*;
import minenaruto.narutoplugin.abilities.reningan.RenninganSaskeTeleportHome;
import minenaruto.narutoplugin.models.*;
import minenaruto.narutoplugin.abilities.reningan.RenninganBlackHole;
import minenaruto.narutoplugin.abilities.reningan.RenninganFly;
import minenaruto.narutoplugin.abilities.reningan.RenninganSaskeTeleport;
import minenaruto.narutoplugin.abilities.sharingan.*;
import minenaruto.narutoplugin.swords.*;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import minenaruto.narutoplugin.abilities.AbilityListener;
import minenaruto.narutoplugin.dataplayer.Chakra;
import minenaruto.narutoplugin.dataplayer.PlayerListener;
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
	private ArrayList<ModelsMain> models = new ArrayList<ModelsMain>();
	private ArrayList<SwordMain> swords = new ArrayList<SwordMain>();
	public static FileConfiguration getCfg() { return cfg; }
	public void onEnable() {
	    instance = this;
		cfg = this.getConfig();
		this.saveDefaultConfig();
		registerEvents();
		registerCommands();
		registerModels();
		registerAbilities();
		registerSwords();

		new Chakra().runTaskTimer((Plugin)this, 20L, 20L);
	}

	public ArrayList<AbilitiesMain> getAbilities() {
		return abilities;
	}
	public ArrayList<SwordMain> getSwords() {
		return swords;
	}

	private void registerAbilities() {
		//Глаза:
		//  Шаринган:
		abilities.add(new Sharingan());
		abilities.add(new SharinganObito());
		abilities.add(new SharinganItachi());
		abilities.add(new SharinganItachiAmateracy());
		abilities.add(new SharinganSaskeAmateracy());
		abilities.add(new SharinganSusano());
		abilities.add(new SharinganSusanoItachi());
		abilities.add(new SharinganSusanoMadara());
		abilities.add(new SharinganSusanoSaske());
		//  Риненган:
		abilities.add(new RenninganFly());
		abilities.add(new RenninganBlackHole());
		abilities.add(new RenninganSaskeTeleportHome());
		abilities.add(new RenninganSaskeTeleport());
		//Стихии:
		// Огонь:
		abilities.add(new FireCircleOfFire());
		abilities.add(new FireFlameCannon());
		abilities.add(new FireJetpack());
		abilities.add(new FireMassFieryDestruction());
		abilities.add(new FirePhoenixFlower());
		abilities.add(new FireShieldOfFire());
		abilities.add(new FireSimpleFlame());
		// Молния:
		abilities.add(new LightningPower());
		abilities.add(new LightningArmor());
		abilities.add(new LightningBullets());
		abilities.add(new LightningShield());
		// Земля:
		abilities.add(new StoneBullets());
		abilities.add(new StoneArmor());
		abilities.add(new StoneHand());
		abilities.add(new StoneBroke());
		// Вода:
		abilities.add(new WaterJail());
		abilities.add(new WaterShark());
		abilities.add(new WaterPunch());
		abilities.add(new WaterHands());
        // Ветер:
		abilities.add(new WindPush());
		abilities.add(new WindFly());
		abilities.add(new WindBoom());
		abilities.add(new WindRun());
		abilities.add(new WindPunch());
		abilities.add(new WindPunchDouble());
		//ККГ:
		//  Дерево:
		abilities.add(new LogShield());
		abilities.add(new LogSharpNeedles());
		abilities.add(new LogHandsOfWood());
		//Прочие возможности:
		abilities.add(new ChakraController());
		abilities.add(new ReplacementTechnique());
		abilities.add(new ShadowClon());
	}
	private void registerModels() {
		models.add(new Stone());
		models.add(new FireBall());
		models.add(new Shark());
		models.add(new Susano());
		models.add(new SusanoItachi());
		models.add(new SusanoMadara());
		models.add(new SusanoSaske());
	}
	private void registerSwords() {
		swords.add(new Decapitator());
		swords.add(new Kabytovari());
		swords.add(new Nyibari());
		swords.add(new Posox_mydresa());
		swords.add(new Shubiki_mech());
		swords.add(new Samehada());
		swords.add(new Weer());
		swords.add(new HidanScythe());
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

		Chakra.chakraBar.keySet().forEach(key -> {
			
			Chakra.chakraBar.get(key).removeAll();
		});
		Chakra.chakraBar.clear();
		for(NPC npc : ShinobiMob.npcs) {
			npc.destroy();
		}
		ShinobiMob.npcs.clear();
		for (ArrayList<Block> b : AbilitiesMain.blocks.values()) {
			for (Block block : b) {
				block.setType(Material.AIR);
			}
		}
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
