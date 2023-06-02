package minenaruto.narutoplugin.dataplayer;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import minenaruto.narutoplugin.main.Main;

public class NarutoPlayer {

	public static HashMap<String, NarutoPlayer> players = new HashMap<String, NarutoPlayer>();
	private String name;
	private File filecfg;
	private FileConfiguration cfg;
	private HashMap<String, String> informations;

	public NarutoPlayer(String name) {
		this.name = name;
		this.filecfg = new File(Main.getInstance().getDataFolder(), "/players/" + name + ".yml");
		this.cfg = (FileConfiguration) YamlConfiguration.loadConfiguration(this.filecfg);

	}

	public static NarutoPlayer getNarutoPlayer(String name) {
		if (players.containsKey(name)) {
			return players.get(name);
		} else {
			return null;
		}
	}

	public void load() {
		informations = new HashMap<String, String>();
		if (existscfg()) {
			ArrayList<String> checkcfg = new ArrayList<String>();
			this.cfg.getValues(true).keySet().forEach(obj -> {
				informations.put(String.valueOf(obj), String.valueOf(this.cfg.getValues(true).get(obj)));
				checkcfg.add(String.valueOf(obj));

			});
			for (String reloadhash : Main.getCfg().getStringList("hash")) {
				if (!checkcfg.contains(reloadhash.split("=")[0])) {
					informations.put(reloadhash.split("=")[0], reloadhash.split("=")[1]);
					this.cfg.set(reloadhash.split("=")[0], reloadhash.split("=")[1]);
				} // Проверяет значения в кфг, добавит если нету
			}
			Main.saveCustomYml(this.cfg, this.filecfg);

		} else {
			for (String hashmap : Main.getCfg().getStringList("hash")) {
				informations.put(hashmap.split("=")[0], hashmap.split("=")[1]);
				this.cfg.set(hashmap.split("=")[0], hashmap.split("=")[1]);

			}
			Main.saveCustomYml(this.cfg, this.filecfg);

		}
		players.put(this.name, this);
	}

	public void unload() {
		if (players.containsKey(this.name)) {
			this.informations.keySet().forEach(key -> {
				this.cfg.set(key, this.informations.get(key));
			});
			Main.saveCustomYml(this.cfg, this.filecfg);
			players.remove(this.name);

		}
	}

	public boolean getBoolean(String param) {
		return Boolean.parseBoolean(this.informations.get(param));
	}

	public int getInt(String param) {
		return Integer.parseInt(this.informations.get(param));
	}

	public double getDouble(String param) {
		return Double.parseDouble(this.informations.get(param));
	}

	public String getString(String param) {
		return this.informations.get(param);
	}
	public boolean isObject(String param) {
		return this.informations.containsKey(param);
	}
	public boolean IfHasJustuPointAndRemoveJustuPoint(int justupoint) {
		int getPlayerJustuPoint = this.getInt("justupoint");
		if (getPlayerJustuPoint >= justupoint) {
			this.setInt("justupoint", getPlayerJustuPoint - justupoint);
			this.sendMessage("Вы потратили джутсупоинты, вам добавилась новая способность");
			return true;
		}
		this.sendMessage("У вас нет джутсупоинтов для прокачки способности");
		return false;
	}

	public String getNinjustu() {
		String[] formats = "".split("");
		String popa = "";
		int count = this.informations.get("ninjustu").length();
		while (count > 0) {
			popa += "";
			count -= 1;
		}
		return this.informations.get("ninjustu").replace("0", formats[0]).replace("1", formats[1])
				.replace("2", formats[2]).replace("3", formats[3]).replace("4", formats[4]).replace("5", formats[5])
				.replace("6", formats[6]).replace("7", formats[7]).replace("8", formats[8]).replace("9", formats[9])
				+ popa;
	}

	public String getTaijustu() {
		String[] formats = "".split("");
		String popa = "";
		int count = this.informations.get("taijustu").length();
		while (count > 0) {
			popa += "";
			count -= 1;
		}
		return this.informations.get("taijustu").replace("0", formats[0]).replace("1", formats[1])
				.replace("2", formats[2]).replace("3", formats[3]).replace("4", formats[4]).replace("5", formats[5])
				.replace("6", formats[6]).replace("7", formats[7]).replace("8", formats[8]).replace("9", formats[9])
				+ popa;
	}

	public String getGenjustu() {
		String[] formats = "".split("");
		String popa = "";
		int count = this.informations.get("genjustu").length();
		while (count > 0) {
			popa += "";
			count -= 1;
		}
		return this.informations.get("genjustu").replace("0", formats[0]).replace("1", formats[1])
				.replace("2", formats[2]).replace("3", formats[3]).replace("4", formats[4]).replace("5", formats[5])
				.replace("6", formats[6]).replace("7", formats[7]).replace("8", formats[8]).replace("9", formats[9])
				+ popa;
	}

	public String getKenjustu() {
		String[] formats = "".split("");
		String popa = "";
		int count = this.informations.get("kenjustu").length();
		while (count > 0) {
			popa += "";
			count -= 1;
		}
		return this.informations.get("kenjustu").replace("0", formats[0]).replace("1", formats[1])
				.replace("2", formats[2]).replace("3", formats[3]).replace("4", formats[4]).replace("5", formats[5])
				.replace("6", formats[6]).replace("7", formats[7]).replace("8", formats[8]).replace("9", formats[9])
				+ popa;
	}

	public String getShurikenjustu() {
		String[] formats = "".split("");
		String popa = "";
		int count = this.informations.get("shurikenjustu").length();
		while (count > 0) {
			popa += "";
			count -= 1;
		}
		return this.informations.get("shurikenjustu").replace("0", formats[0]).replace("1", formats[1])
				.replace("2", formats[2]).replace("3", formats[3]).replace("4", formats[4]).replace("5", formats[5])
				.replace("6", formats[6]).replace("7", formats[7]).replace("8", formats[8]).replace("9", formats[9])
				+ popa;
	}

	public String getSummoning() {
		String[] formats = "".split("");
		String popa = "";
		int count = this.informations.get("summoning").length();
		while (count > 0) {
			popa += "";
			count -= 1;
		}
		return this.informations.get("summoning").replace("0", formats[0]).replace("1", formats[1])
				.replace("2", formats[2]).replace("3", formats[3]).replace("4", formats[4]).replace("5", formats[5])
				.replace("6", formats[6]).replace("7", formats[7]).replace("8", formats[8]).replace("9", formats[9])
				+ popa;
	}

	public String getKinjustu() {
		String[] formats = "".split("");
		String popa = "";
		int count = this.informations.get("kinjustu").length();
		while (count > 0) {
			popa += "";
			count -= 1;
		}
		return this.informations.get("kinjustu").replace("0", formats[0]).replace("1", formats[1])
				.replace("2", formats[2]).replace("3", formats[3]).replace("4", formats[4]).replace("5", formats[5])
				.replace("6", formats[6]).replace("7", formats[7]).replace("8", formats[8]).replace("9", formats[9])
				+ popa;
	}

	public String getSenjustu() {
		String[] formats = "".split("");
		String popa = "";
		int count = this.informations.get("senjustu").length();
		while (count > 0) {
			popa += "";
			count -= 1;
		}
		return this.informations.get("senjustu").replace("0", formats[0]).replace("1", formats[1])
				.replace("2", formats[2]).replace("3", formats[3]).replace("4", formats[4]).replace("5", formats[5])
				.replace("6", formats[6]).replace("7", formats[7]).replace("8", formats[8]).replace("9", formats[9])
				+ popa;
	}

	public String getMedical() {
		String[] formats = "".split("");
		String popa = "";
		int count = this.informations.get("medical").length();
		while (count > 0) {
			popa += "";
			count -= 1;
		}
		return this.informations.get("medical").replace("0", formats[0]).replace("1", formats[1])
				.replace("2", formats[2]).replace("3", formats[3]).replace("4", formats[4]).replace("5", formats[5])
				.replace("6", formats[6]).replace("7", formats[7]).replace("8", formats[8]).replace("9", formats[9])
				+ popa;
	}

	public String getSpeed() {
		String[] formats = "".split("");
		String popa = "";
		int count = this.informations.get("speed").length();
		while (count > 0) {
			popa += "";
			count -= 1;
		}
		return this.informations.get("speed").replace("0", formats[0]).replace("1", formats[1]).replace("2", formats[2])
				.replace("3", formats[3]).replace("4", formats[4]).replace("5", formats[5]).replace("6", formats[6])
				.replace("7", formats[7]).replace("8", formats[8]).replace("9", formats[9]) + popa;
	}

	public String getLine1(String ex) {
		String[] formats = "".split("");
		String popa = "";
		int count = ex.length();
		while (count > 0) {
			popa += "";
			count -= 1;
		}
		return ex.replace("0", formats[0]).replace("1", formats[1]).replace("2", formats[2]).replace("3", formats[3])
				.replace("4", formats[4]).replace("5", formats[5]).replace("6", formats[6]).replace("7", formats[7])
				.replace("8", formats[8]).replace("9", formats[9]).replace("a", formats[10]).replace("b", formats[11])
				.replace("c", formats[12]).replace("d", formats[13]).replace("e", formats[14]).replace("f", formats[15])
				.replace("g", formats[16]).replace("h", formats[17]).replace("i", formats[18]).replace("j", formats[19])
				.replace("k", formats[20]).replace("l", formats[21]).replace("m", formats[22]).replace("n", formats[23])
				.replace("o", formats[24]).replace("p", formats[25]).replace("q", formats[26]).replace("r", formats[27])
				.replace("s", formats[28]).replace("t", formats[29]).replace("u", formats[30]).replace("v", formats[31])
				.replace("w", formats[32]).replace("x", formats[33]).replace("y", formats[34]).replace("z", formats[35])
				.replace(":", formats[36]) + popa;

	}

	public String getLine2(String ex) {
		String[] formats = "".split("");
		String popa = "";
		int count = ex.length();
		while (count > 0) {
			popa += "";
			count -= 1;
		}
		return ex.replace("0", formats[0]).replace("1", formats[1]).replace("2", formats[2]).replace("3", formats[3])
				.replace("4", formats[4]).replace("5", formats[5]).replace("6", formats[6]).replace("7", formats[7])
				.replace("8", formats[8]).replace("9", formats[9]).replace("a", formats[10]).replace("b", formats[11])
				.replace("c", formats[12]).replace("d", formats[13]).replace("e", formats[14]).replace("f", formats[15])
				.replace("g", formats[16]).replace("h", formats[17]).replace("i", formats[18]).replace("j", formats[19])
				.replace("k", formats[20]).replace("l", formats[21]).replace("m", formats[22]).replace("n", formats[23])
				.replace("o", formats[24]).replace("p", formats[25]).replace("q", formats[26]).replace("r", formats[27])
				.replace("s", formats[28]).replace("t", formats[29]).replace("u", formats[30]).replace("v", formats[31])
				.replace("w", formats[32]).replace("x", formats[33]).replace("y", formats[34]).replace("z", formats[35])
				.replace(":", formats[36]) + popa;

	}

	public String getLine3(String ex) {
		String[] formats = "".split("");
		String popa = "";
		int count = ex.length();
		while (count > 0) {
			popa += "";
			count -= 1;
		}
		return ex.replace("0", formats[0]).replace("1", formats[1]).replace("2", formats[2]).replace("3", formats[3])
				.replace("4", formats[4]).replace("5", formats[5]).replace("6", formats[6]).replace("7", formats[7])
				.replace("8", formats[8]).replace("9", formats[9]).replace("a", formats[10]).replace("b", formats[11])
				.replace("c", formats[12]).replace("d", formats[13]).replace("e", formats[14]).replace("f", formats[15])
				.replace("g", formats[16]).replace("h", formats[17]).replace("i", formats[18]).replace("j", formats[19])
				.replace("k", formats[20]).replace("l", formats[21]).replace("m", formats[22]).replace("n", formats[23])
				.replace("o", formats[24]).replace("p", formats[25]).replace("q", formats[26]).replace("r", formats[27])
				.replace("s", formats[28]).replace("t", formats[29]).replace("u", formats[30]).replace("v", formats[31])
				.replace("w", formats[32]).replace("x", formats[33]).replace("y", formats[34]).replace("z", formats[35])
				.replace(":", formats[36]) + popa;

	}

	public void setBoolean(String param, boolean param2) {
		this.informations.put(param, String.valueOf(param2));
	}

	public void setInt(String param, int param2) {
		this.informations.put(param, String.valueOf(param2));
	}

	public void setDouble(String param, double param2) {
		this.informations.put(param, String.valueOf(param2));
	}

	public void addExp(int param2) {
		int exp = (getInt("exp") + param2);
		int level = getInt("level");
		int q = Main.getInstance().getConfig().getInt("q");

		while (level > 0) {
			q *= Main.getInstance().getConfig().getInt("q");
			level -= 1;

		}

		while (exp >= Main.getInstance().getConfig().getInt("b1") * q) {
			exp -= Main.getInstance().getConfig().getInt("b1") * q;

			lvlup();
		}

		param2 = exp;
		this.informations.put("exp", String.valueOf(param2));
		sendMessage("Вы получили 3 опыта за убийство шиноби");
	}

	public void sendMessage(String msg) {
		Bukkit.getPlayer(this.name).sendMessage("§7[§6Naruto§7] §f" + msg.replace("&", "§"));
	}

	public void lvlup() {
		Bukkit.getPlayer(this.name).sendMessage("§7[§6Naruto§7] §fВы получили §63 джутсупоинта §fи §b3 скиллпоинта");
		setInt("skillpoint", getInt("skillpoint") + 3);
		setInt("justupoint", getInt("justupoint") + 3);
		setInt("level", getInt("level") + 1);
	}

	public void setString(String param, String param2) {
		this.informations.put(param, String.valueOf(param2));
	}

	public boolean existscfg() {
		if (this.filecfg.exists()) {
			return true;
		} else {
			return false;
		}
	}

}
