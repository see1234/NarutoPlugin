package minenaruto.narutoplugin.abilities.sharingan;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
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
	private Item item = new Item(293, 77, "§7[§6Naruto§7] §4Шаринган Итачи (Воронье Гендзюцу)", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));
	@Override
	public void RightClick(Player player, NarutoPlayer pl) {
		if (AbilityListener.checkChakraItem(player, getItem().getName(), 100, 0, 0, 0, 0)) {
			Location playerlocation = player.getLocation().clone();
		//	List<ArmorStand> arrayArmorStand = spawnArmorStand(playerlocation, 1);
				runTaskAbility(player);

		}
	}

	@Override
	public void RightPlusShift(Player player, NarutoPlayer pl) {
		// TODO Auto-generated method stub
		if (AbilityListener.checkChakraItem(player, getItem().getName(), 0, 0, 0, 0, 0)) {
			if(pl.IfHasJustuPointAndRemoveJustuPoint(5)) {
				//	player.getInventory().addItem(Item.items.get(7).getItemStack());
			}
		}
	}

	@Override
	public Item getItem() {
		return item;
	}

	public void runTaskAbility(Player player) {
		final LivingEntity entityTarget = rayTraceEntity(player, 16);
		if(entityTarget == null) {
		player.sendMessage("§7[§6Naruto§7] §f" + "Вы не скрылись в летущие мышки");	
		}
		else {
	     	if(entityTarget instanceof Player) {
		((Player) entityTarget).hidePlayer(player);
	     	}
		}
        ArrayList<Bat> batList = new ArrayList<Bat>();
        for (int i = 0; i < 20; ++i) {
            final Bat bat = (Bat)player.getWorld().spawnEntity(player.getEyeLocation(), EntityType.BAT);
            batList.add((Bat)bat);
        }
        for (final Entity aroundPlayer : player.getNearbyEntities(5.0, 5.0, 5.0)) {
            if (aroundPlayer instanceof Player) {
                ((Player)aroundPlayer).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 1));
            }
        }
  

        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.getInstance(), () -> {
            for(Bat bat : batList) {
           		if(entityTarget != null) {
            	if(entityTarget instanceof Player) {
      
                  ((Player) entityTarget).showPlayer(player);
            		}
            	}
                bat.remove();
     
            }
        }, 200L);
	}

 

 

}