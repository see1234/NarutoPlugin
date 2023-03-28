package minenaruto.narutoplugin.dataplayer;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class Chakra extends BukkitRunnable {

	public static HashMap<String, BossBar> chakraBar = new HashMap<String, BossBar>();

	@Override
	public void run() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			if (chakraBar.containsKey(player.getName())) {
				if (player.isOnline()) {
					NarutoPlayer np = NarutoPlayer.getNarutoPlayer(player.getName());
					BossBar bossBar = chakraBar.get(player.getName());

					bossBar.setProgress(((np.getDouble("chakra") / ((np.getDouble("ninjustu") * 10) + 100))) * 1.0);

					int a = (((np.getInt("ninjustu") * 10) + 100) <= (np.getInt("chakra") + 1))
							? ((np.getInt("ninjustu") * 10) + 100)
							: (np.getInt("chakra") + 1);
					if (a <= ((np.getInt("ninjustu") * 10) + 100)) {
						np.setInt("chakra", a);
					}
					double defaultspeed = 0.2;

					player.setWalkSpeed((float) (defaultspeed + (np.getInt("speed") * 0.002)));
				}
			} else {
				NarutoPlayer np = NarutoPlayer.getNarutoPlayer(player.getName());
				if (np != null) {
					BossBar bossBar = Bukkit.createBossBar("§aЧакра - §dменю прокачки нажмите §cF", BarColor.BLUE,
							BarStyle.SEGMENTED_20, new BarFlag[0]);
					bossBar.addPlayer(player);
					chakraBar.put(player.getName(), bossBar);
					bossBar.setProgress((np.getInt("chakra") / ((np.getInt("ninjustu") * 10) + 100)));
				}
			}

		}

	}

}
