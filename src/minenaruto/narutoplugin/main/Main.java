package minenaruto.narutoplugin.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.abilities.Kekkei_Genkai.blaze.*;
import minenaruto.narutoplugin.abilities.Kekkei_Genkai.boil.*;
import minenaruto.narutoplugin.abilities.Kekkei_Genkai.crystal.*;
import minenaruto.narutoplugin.abilities.Kekkei_Genkai.dark.*;
import minenaruto.narutoplugin.abilities.Kekkei_Genkai.explosion.*;
import minenaruto.narutoplugin.abilities.Kekkei_Genkai.ice.IceField;
import minenaruto.narutoplugin.abilities.Kekkei_Genkai.ice.IceHands;
import minenaruto.narutoplugin.abilities.Kekkei_Genkai.ice.IceWalls;
import minenaruto.narutoplugin.abilities.Kekkei_Genkai.lava.*;
import minenaruto.narutoplugin.abilities.Kekkei_Genkai.log.LogHandsOfWood;
import minenaruto.narutoplugin.abilities.Kekkei_Genkai.log.LogSharpNeedles;
import minenaruto.narutoplugin.abilities.Kekkei_Genkai.log.LogShield;
import minenaruto.narutoplugin.abilities.Kekkei_Genkai.magnet.*;
import minenaruto.narutoplugin.abilities.Kekkei_Genkai.medical.*;
import minenaruto.narutoplugin.abilities.Kekkei_Genkai.sand.DisintegrationBeam;
import minenaruto.narutoplugin.abilities.Kekkei_Genkai.sand.DustBlast;
import minenaruto.narutoplugin.abilities.Kekkei_Genkai.sand.DustShield;
import minenaruto.narutoplugin.abilities.Kekkei_Genkai.sand.GravityCrush;
import minenaruto.narutoplugin.abilities.Kekkei_Genkai.scorch.*;
import minenaruto.narutoplugin.abilities.Kekkei_Genkai.storm.RainStorm;
import minenaruto.narutoplugin.abilities.Kekkei_Genkai.storm.StormSurge;
import minenaruto.narutoplugin.abilities.Kekkei_Genkai.storm.TempestBarrier;
import minenaruto.narutoplugin.abilities.Kekkei_Genkai.storm.ThunderShock;
import minenaruto.narutoplugin.abilities.Kekkei_Genkai.swift.Blink;
import minenaruto.narutoplugin.abilities.Kekkei_Genkai.swift.Evasion;
import minenaruto.narutoplugin.abilities.Kekkei_Genkai.swift.WindDash;
import minenaruto.narutoplugin.abilities.basics.ChakraController;
import minenaruto.narutoplugin.abilities.basics.ReplacementTechnique;
import minenaruto.narutoplugin.abilities.basics.ShadowClon;
import minenaruto.narutoplugin.abilities.basics.Tutorial;
import minenaruto.narutoplugin.abilities.basics.earth.*;
import minenaruto.narutoplugin.abilities.basics.fire.*;
import minenaruto.narutoplugin.abilities.basics.lightning.*;
import minenaruto.narutoplugin.abilities.basics.water.*;
import minenaruto.narutoplugin.abilities.basics.wind.*;
import minenaruto.narutoplugin.abilities.byakugan.*;
import minenaruto.narutoplugin.abilities.clan.aburame.*;
import minenaruto.narutoplugin.abilities.clan.akimichi.*;
import minenaruto.narutoplugin.abilities.clan.fuma.FumaShurikenThrow;
import minenaruto.narutoplugin.abilities.clan.hagoromo.*;
import minenaruto.narutoplugin.abilities.clan.hatake.*;
import minenaruto.narutoplugin.abilities.clan.hozuki.*;
import minenaruto.narutoplugin.abilities.clan.iburi.*;
import minenaruto.narutoplugin.abilities.clan.inuzuki.*;
import minenaruto.narutoplugin.abilities.clan.kohaku.*;
import minenaruto.narutoplugin.abilities.clan.nara.*;
import minenaruto.narutoplugin.abilities.clan.otsutsuki.*;
import minenaruto.narutoplugin.abilities.clan.ryu.*;
import minenaruto.narutoplugin.abilities.clan.sarutobi.*;
import minenaruto.narutoplugin.abilities.clan.shirogane.*;
import minenaruto.narutoplugin.abilities.clan.uchiha.Amaterasu;
import minenaruto.narutoplugin.abilities.clan.uchiha.FireballJutsu;
import minenaruto.narutoplugin.abilities.clan.uzumaki.*;
import minenaruto.narutoplugin.abilities.clan.uzushio.*;
import minenaruto.narutoplugin.abilities.clan.wasabi.WasabiReflex;
import minenaruto.narutoplugin.abilities.clan.wasabi.WasabiSpeed;
import minenaruto.narutoplugin.abilities.clan.wasabi.WasabiStrike;
import minenaruto.narutoplugin.abilities.clan.yotsuki.LightningStrike;
import minenaruto.narutoplugin.abilities.clan.yotsuki.SpeedBoost;
import minenaruto.narutoplugin.abilities.ketsuryugan.*;
import minenaruto.narutoplugin.abilities.reningan.*;
import minenaruto.narutoplugin.abilities.sage.*;
import minenaruto.narutoplugin.abilities.tenseigan.*;
import minenaruto.narutoplugin.models.*;
import minenaruto.narutoplugin.abilities.sharingan.*;
import minenaruto.narutoplugin.placeholder.NarutoPlaceHolder;
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
import org.bukkit.entity.Frog;
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
	private NarutoPlaceHolder nph;
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
		registerPlaceHolder();
		new Chakra().runTaskTimer((Plugin)this, 20L, 20L);
	}

	public ArrayList<AbilitiesMain> getAbilities() {
		return abilities;
	}
	public ArrayList<SwordMain> getSwords() {
		return swords;
	}
	public ArrayList<ModelsMain> getModels() {
		return models;
	}
	private void registerAbilities() {
		abilities.add(new Tutorial());
		//Глаза:
		//  Тейсейган:
		abilities.add(new TenseiganAwakening());
		abilities.add(new TenseiganChakraAbsorption());
		abilities.add(new TenseiganGoldenWheel());
		abilities.add(new TenseiganOrbs());
		abilities.add(new TenseiganSphereIstina());
		//  Кэйсюрган:
		abilities.add(new BloodCloneAbility());
		abilities.add(new BloodControlAbility());
		abilities.add(new BloodDragonAbility());
		abilities.add(new BloodMistAbility());
		abilities.add(new BloodSightAbility());
		//  Sage-Mode:
		abilities.add(new EarthWall());
		abilities.add(new FrogKata());
		abilities.add(new GiantRassengan());
		abilities.add(new Healing());
		abilities.add(new MassiveRassenganBarrage());
		abilities.add(new SenpoGoemon());
		abilities.add(new Sensing());
		abilities.add(new SuperStrength());
		abilities.add(new UltimateRasenshuriken());
		abilities.add(new WaterDragonBullet());
		//  Бьякуган:
		abilities.add(new ByakuganShield());
		//abilities.add(new ByakuganSpector());
		abilities.add(new ByakuganVision());
		abilities.add(new ChakraExplosion());
		abilities.add(new ChakraStrike());
		abilities.add(new RotationShield());
		abilities.add(new PalmStrike());
		//  Шаринган:
		abilities.add(new CopyTechnique());
		abilities.add(new Izanagi());
		abilities.add(new Izanami());
		abilities.add(new KageBunshin());
		abilities.add(new Kamui());
		abilities.add(new Sharingan());
		abilities.add(new SharinganObito());
		abilities.add(new SharinganItachi());
		abilities.add(new SharinganItachiAmateracy());
		abilities.add(new SharinganSaskeAmateracy());
		abilities.add(new SharinganSusano());
		abilities.add(new SharinganSusanoItachi());
		abilities.add(new SharinganSusanoMadara());
		abilities.add(new SharinganSusanoFull());
		abilities.add(new SharinganSusanoSaske());
		//  Риненган:
		abilities.add(new BanshoTenin());
		abilities.add(new DevaPath());
		abilities.add(new ShinraTensei());
		abilities.add(new TengaiShinsei());
		abilities.add(new RenninganFly());
		abilities.add(new RenninganBlackHole());
		abilities.add(new RenninganSaskeTeleportHome());
		abilities.add(new RenninganSaskeTeleport());
		//Стихии:
		// Огонь:
		abilities.add(new FireDragonJutsu());
		abilities.add(new FireExplosionJutsu());
		abilities.add(new FireMeteorJutsu());
		abilities.add(new FireRainJutsu());
		abilities.add(new FireSmallball());
		abilities.add(new FireVortexJutsu());
		abilities.add(new FireCircleOfFire());
		abilities.add(new FireFlameCannon());
		abilities.add(new FireJetpack());
		abilities.add(new FireMassFieryDestruction());
		abilities.add(new FirePhoenixFlower());
		abilities.add(new FireShieldOfFire());
		abilities.add(new FireSimpleFlame());
		// Молния:
		abilities.add(new LightningArrow());
		abilities.add(new LightningBlade());
		abilities.add(new LightningClones());
		abilities.add(new LightningDragon());
		abilities.add(new LightningSphere());
		abilities.add(new LightningSword());
		abilities.add(new LightningPower());
		abilities.add(new LightningArmor());
		abilities.add(new LightningBullets());
		abilities.add(new LightningShield());
		// Земля:
		abilities.add(new Earthquake());
		abilities.add(new StoneClone());
		abilities.add(new StoneSphere());
		abilities.add(new StoneBullets());
		abilities.add(new StoneArmor());
		abilities.add(new StoneHand());
		abilities.add(new StoneBroke());
		// Вода:
		abilities.add(new WaterBubble());
		abilities.add(new WaterCatapult());
		abilities.add(new WaterClone());
		abilities.add(new WaterDragon());
		abilities.add(new WaterPrison());
		abilities.add(new WaterShuriken());
		abilities.add(new WaterWhip());
		abilities.add(new WaterJail());
		abilities.add(new WaterShark());
		abilities.add(new WaterPunch());
		abilities.add(new WaterHands());
        // Ветер:
		abilities.add(new WindCloneJutsu());
		abilities.add(new WindExplosionJutsu());
		abilities.add(new WindHurricaneJutsu());
		abilities.add(new WindMistJutsu());
		abilities.add(new WindPush());
		abilities.add(new WindStreamJutsu());
		abilities.add(new WindStrikeJutsu());
		abilities.add(new WindTornadoJutsu());
		abilities.add(new WindVortexJutsu());
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
		//  Лед:
		abilities.add(new IceField());
		abilities.add(new IceHands());
		abilities.add(new IceWalls());
		//  Блейз:
		abilities.add(new BurningAshAbility());
		abilities.add(new FlamePrisonAbility());
		abilities.add(new InfernoTrapAbility());
		abilities.add(new PhoenixCloakAbility());
		abilities.add(new ScorchingWaveAbility());
		//  Боил:
		abilities.add(new AcidicSteamBombAbility());
		abilities.add(new BoilingJetFistAbility());
		abilities.add(new ScaldingGeyserAbility());
		abilities.add(new SkilledMistAbility());
		abilities.add(new SteamBarrierAbility());
		//  Кристалл:
		abilities.add(new CrystalArmor());
		abilities.add(new CrystalClone());
		abilities.add(new CrystalPrison());
		abilities.add(new CrystalShards());
		abilities.add(new CrystalSpear());
		//  Темнота:
		abilities.add(new AbyssalChains());
		abilities.add(new DarkPulse());
		abilities.add(new PhantomStep());
		abilities.add(new ShadowGrip());
		abilities.add(new VoidShield());
		//  Взрыв:
		abilities.add(new BlastWave());
		abilities.add(new BombClone());
		abilities.add(new DetonatingKunai());
		abilities.add(new ExplosivePalm());
		abilities.add(new MegaExplosion());
		//  Лава:
		abilities.add(new BurningChains());
		abilities.add(new LavaArmor());
		abilities.add(new LavaBurst());
		abilities.add(new LavaWave());
		abilities.add(new MagmaPool());
		//  Магнит:
		abilities.add(new IronSandArmor());
		abilities.add(new IronSandSpike());
		abilities.add(new MagneticField());
		abilities.add(new MagneticRepel());
		abilities.add(new MagneticPull());
		//  Медицина:
		abilities.add(new Heal());
		abilities.add(new HealAura());
		abilities.add(new PoisonCure());
		abilities.add(new Regeneration());
		abilities.add(new Revive());
		//  Песок:
		abilities.add(new DustBlast());
		abilities.add(new DisintegrationBeam());
		abilities.add(new DustBlast());
		abilities.add(new DustShield());
		abilities.add(new GravityCrush());
		//  Scorch:
		abilities.add(new BlazingSphere());
		abilities.add(new DryingFlame());
		abilities.add(new HeatWave());
		abilities.add(new InfernoBurst());
		abilities.add(new ScorchedEarth());
		//  Шторм:
		abilities.add(new LightningSword());
		abilities.add(new RainStorm());
		abilities.add(new StormSurge());
		abilities.add(new TempestBarrier());
		abilities.add(new ThunderShock());
		//  Swift:
		abilities.add(new Blink());
		abilities.add(new Evasion());
		abilities.add(new minenaruto.narutoplugin.abilities.Kekkei_Genkai.swift.SpeedBoost());
		abilities.add(new WindDash());
		//Кланы:
		//  Aburame:
		abilities.add(new BugClone());
		abilities.add(new BugShield());
		abilities.add(new BugSwarm());
		abilities.add(new InsectCamouflage());
		abilities.add(new ParasiticDrain());
		//  Shirogane:
		abilities.add(new BladeTrap());
		abilities.add(new IronSandStrike());
		abilities.add(new PoisonGas());
		abilities.add(new PuppetArmy());
		abilities.add(new PuppetControl());
		//  Uchiha:
		abilities.add(new Amaterasu());
		abilities.add(new FireballJutsu());
		abilities.add(new minenaruto.narutoplugin.abilities.clan.uchiha.Sharingan());
		//  Uzumaki:
		abilities.add(new AdamantineChains());
		abilities.add(new ChakraResilience());
		abilities.add(new LifeForceEnhancement());
		abilities.add(new SealingBarrier());
		abilities.add(new UzumakiRejuvenation());
		//  Uzushio:
		abilities.add(new AquaShield());
		abilities.add(new OceanicSurge());
		abilities.add(new UzushioRegeneration());
		abilities.add(new WaterWhirl());
		abilities.add(new WhirlpoolPrison());
		//  Wasabi:
		abilities.add(new WasabiReflex());
		abilities.add(new WasabiSpeed());
		abilities.add(new WasabiStrike());
		//  Yotsuki:
		abilities.add(new LightningStrike());
		abilities.add(new SpeedBoost());
		//  Akimichi:
		abilities.add(new AkimichiCaloricBurn());
		abilities.add(new AkimichiExpansion());
		abilities.add(new AkimichiHumanBoulder());
		abilities.add(new AkimichiMeatTank());
		abilities.add(new AkimichiSuperPunch());
		//  Hagoromo:
		abilities.add(new HagoromoHeavenlyHand());
		abilities.add(new HagoromoSixPaths());
		abilities.add(new HagoromoTruthSeeker());
		abilities.add(new HagoromoYinYang());
		abilities.add(new HagoromoChibakuTensei());
		//  Fuma:
		abilities.add(new FumaShurikenThrow());
		//  Hatake:
		abilities.add(new HatakeChidori());
		abilities.add(new HatakeLightningBlade());
		abilities.add(new HatakeRaikiri());
		abilities.add(new HatakeShadowClone());
		abilities.add(new HatakeSharpenInstinct());
		//  Hozuki:
		abilities.add(new HozukiHydration());
		abilities.add(new HozukiLiquidBody());
		abilities.add(new HozukiTidalWave());
		abilities.add(new HozukiWaterGun());
		abilities.add(new HozukiWaterWhip());
		//  Iburi:
		abilities.add(new IburiSmokeClone());
		abilities.add(new IburiSmokeDash());
		abilities.add(new IburiSmokeForm());
		abilities.add(new IburiSmokeScreen());
		abilities.add(new IburiSuffocate());
		//  Inuzuki:
		abilities.add(new BeastMimicryTechnique());
		abilities.add(new FangOverFangTechnique());
		abilities.add(new HowlingWolfTechnique());
		abilities.add(new TrackingFangTechnique());
		abilities.add(new TwinFangTechnique());
		//  Kohaku:
		abilities.add(new DesertPrisonTechnique());
		abilities.add(new GoldenSandArmorTechnique());
		abilities.add(new GoldenSandBulletTechnique());
		abilities.add(new GoldenSandWaveTechnique());
		abilities.add(new SandCloneTechnique());
		//  Nara:
		abilities.add(new ShadowCloneTechnique());
		abilities.add(new ShadowGraspTechnique());
		abilities.add(new ShadowImitationTechnique());
		abilities.add(new ShadowSewingTechnique());
		abilities.add(new ShadowStrangleTechnique());
		//  Otsutsuki:
		abilities.add(new AmenotejikaraTechnique());
		abilities.add(new ChibakuTenseiTechnique());
		abilities.add(new DivineTreeTechnique());
		abilities.add(new RinneganTechnique());
		abilities.add(new TSBTechnique());
		//  ryu:
		abilities.add(new DragonClawTechnique());
		abilities.add(new DragonFlameTechnique());
		abilities.add(new DragonFlightTechnique());
		abilities.add(new DragonRoarTechnique());
		abilities.add(new DragonScaleArmorTechnique());
		//  Sarutobi:
		abilities.add(new FireballTechnique());
		abilities.add(new GreatFireballTechnique());
		abilities.add(new FireCloneTechnique());
		abilities.add(new FireVortexTechnique());
		abilities.add(new FireWallTechnique());
		//Прочие возможности:
		abilities.add(new ChakraController());
		abilities.add(new ReplacementTechnique());
		abilities.add(new ShadowClon());
	}
	private void registerModels() {
		models.add(new Stone());
		models.add(new FireBall());
		models.add(new Shark());
		models.add(new FullSusano());
		models.add(new Susano());
		models.add(new SusanoItachi());
		models.add(new SusanoMadara());
		models.add(new SusanoSaske());
		models.add(new ShieldBlue());
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

	public void registerPlaceHolder() {
		nph = new NarutoPlaceHolder(this);
		nph.register();
	}
	public void unregisterPlaceHolder() {
		 nph.unregister();
	}
	public void onDisable() {
		unregisterPlaceHolder();
		clearChakraBar();
		deleteCitizensEntity();
		clearBlocksForAbilities();
	}
	public void clearChakraBar() {

		Chakra.chakraBar.keySet().forEach(key -> {

			Chakra.chakraBar.get(key).removeAll();
		});
		Chakra.chakraBar.clear();
	}
	public boolean isCitizensEnable() {
		if(this.getServer().getPluginManager().getPlugin("Citizens") == null) {
			return false;
		}
		return true;
	}
	public void deleteCitizensEntity() {
		if(isCitizensEnable()) {
			for (NPC npc : ShinobiMob.npcs) {
				npc.destroy();
			}
			ShinobiMob.npcs.clear();
		}
	}
	public void clearBlocksForAbilities() {
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
