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
					NarutoPlayer narutoPlayer = NarutoPlayer.getNarutoPlayer(player.getName());
				//	BossBar chakraBossBar = chakraBar.get(player.getName());

				//	double chakraPercentage = narutoPlayer.getDouble("chakra") / ((narutoPlayer.getDouble("ninjustu") * 10) + 100);
				//	chakraBossBar.setProgress(chakraPercentage * 1.0);

					int maxChakra = (narutoPlayer.getInt("ninjustu") * 10) + 100;
					int adjustedChakra = Math.min(maxChakra, narutoPlayer.getInt("chakra") + 1);

					if (adjustedChakra <= maxChakra) {
						narutoPlayer.setInt("chakra", adjustedChakra);
					}

					double defaultSpeed = 0.2;
					float adjustedWalkSpeed = (float) (defaultSpeed + (narutoPlayer.getInt("speed") * 0.002));
					player.setWalkSpeed(adjustedWalkSpeed);
				}
			} else {
				NarutoPlayer narutoPlayer = NarutoPlayer.getNarutoPlayer(player.getName());

				if (narutoPlayer != null) {
				//	BossBar chakraBossBar = Bukkit.createBossBar("§aЧакра - §dменю прокачки нажмите §cF", BarColor.BLUE, BarStyle.SEGMENTED_20, new BarFlag[0]);
			//		chakraBossBar.addPlayer(player);
				//	chakraBar.put(player.getName(), chakraBossBar);

					//double chakraPercentage = narutoPlayer.getInt("chakra") / ((narutoPlayer.getInt("ninjustu") * 10) + 100);
					//chakraBossBar.setProgress(chakraPercentage);
				}
			}
		}
	}

}
