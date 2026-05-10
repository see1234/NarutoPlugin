package minenaruto.narutoplugin.abilities.sharingan;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import minenaruto.narutoplugin.utils.Task;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;

import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.abilities.AbilityListener;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import minenaruto.narutoplugin.main.Main;

public class SharinganItachi extends AbilitiesMain {
	private Item item = new Item(Material.DIAMOND_HOE, 81, "§7[§6Naruto§7] §4Шаринган Итачи (Воронье Гендзюцу)", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));
	@Override
	public void RightClick(Player player, NarutoPlayer pl) {
		if (AbilityListener.checkChakraItem(player, getItem().getName(), 100, 0, 0, 0, 0)) {
			Location playerlocation = player.getLocation().clone();
		//	List<ArmorStand> arrayArmorStand = spawnArmorStand(playerlocation, 1);
				runTaskAbility(player);

		}
	}


	@Override
	public Item getItem() {
		return item;
	}

	public void runTaskAbility(Player player) {
		ArrayList<Player> playersHidenplayer = new ArrayList<Player>();
        ArrayList<Bat> batList = new ArrayList<Bat>();
        for (int i = 0; i < 20; ++i) {
            final Bat bat = (Bat)player.getWorld().spawnEntity(player.getEyeLocation(), EntityType.BAT);
            batList.add((Bat)bat);
        }
		player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,100,0));
        for (final Entity aroundPlayer : player.getNearbyEntities(16.0, 16.0, 16.0)) {
			if (aroundPlayer instanceof Player) {
				((Player) aroundPlayer).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 1));
				playersHidenplayer.add(((Player) aroundPlayer));
				((Player) aroundPlayer).hidePlayer(player);
			} else if (aroundPlayer instanceof Monster) {


				new Task(Main.getInstance(), "combatlogf_" + player.getName(), 20, 0, 500) {
					@Override
					public void onTick() {
						if (this.getPeriods() <= 20) {
							if(((Monster) aroundPlayer).getTarget().equals(player)) {
								((Monster) aroundPlayer).setTarget(null);
							}
						}
					}
				};
			}
		}
  

        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.getInstance(), () -> {
			for(Player player1 : playersHidenplayer) {
				player1.showPlayer(player);
			}
            for(Bat bat : batList) {

                bat.remove();

            }
        }, 100L);
	}

 

 

}