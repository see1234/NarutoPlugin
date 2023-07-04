package minenaruto.narutoplugin.abilities.basics;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import net.citizensnpcs.trait.SkinTrait;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.mcmonkey.sentinel.SentinelTrait;
import org.mcmonkey.sentinel.targeting.SentinelTargetLabel;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.Trait;
import net.citizensnpcs.api.trait.trait.Equipment;

import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.abilities.AbilityListener;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import minenaruto.narutoplugin.main.Main;

public class ShadowClon extends AbilitiesMain {
   private  Item item = new Item(Material.DIAMOND_HOE, 8, "§7[§6Naruto§7] §7Теневое клонирование", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));
	@Override
	public void RightClick(Player player, NarutoPlayer pl) {
		if(AbilityListener.checkChakraItem(player, getItem().getName(), 30, 0, 0, 30, 0)) {
            NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, player.getName());
            SentinelTrait st = (SentinelTrait)npc.getTrait(SentinelTrait.class);
            Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.getInstance(), () -> {
                ((SkinTrait)npc.getTrait(SkinTrait.class)).setSkinName(player.getName());
                ((Equipment)npc.getTrait(Equipment.class)).set(Equipment.EquipmentSlot.HELMET, player.getInventory().getHelmet());
                ((Equipment)npc.getTrait(Equipment.class)).set(Equipment.EquipmentSlot.HAND, player.getInventory().getItemInMainHand());
                ((Equipment)npc.getTrait(Equipment.class)).set(Equipment.EquipmentSlot.CHESTPLATE, player.getInventory().getChestplate());
                ((Equipment)npc.getTrait(Equipment.class)).set(Equipment.EquipmentSlot.BOOTS, player.getInventory().getBoots());
                ((Equipment)npc.getTrait(Equipment.class)).set(Equipment.EquipmentSlot.LEGGINGS, player.getInventory().getLeggings());
                st.setHealth(3.0);
                npc.spawn(player.getLocation().add(1.0, 0.0, 1.0));
                player.getWorld().spawnParticle(Particle.CLOUD, player.getLocation().add(1.0, 0.0, 1.0), 1, 0.0, 0.0, 0.0);
                st.damage = 2.0;
                new SentinelTargetLabel("players").addToList(st.allTargets);
                new SentinelTargetLabel("monsters").addToList(st.allTargets);
                st.speed = 1.0;
                st.range = 16.0;
                st.setGuarding(player.getUniqueId());
                npc.addTrait((Trait)st);
            }, 20L);
 
    		Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {

				@Override
				public void run() {
				     npc.destroy();
					
				}
            
 
 
            }, 400L);
    		Bukkit.getScheduler().runTaskTimerAsynchronously(Main.getInstance(), new BukkitRunnable() {
				
				 

				@Override
				public void run() {
					if(npc == null) {
						cancel();
					}
					((Equipment)npc.getTrait(Equipment.class)).set(Equipment.EquipmentSlot.HAND, player.getInventory().getItemInMainHand());
					
				}
            
 
 
            }, 1L, 1L); 
		}
	}
    @Override
    public Item getItem() {

        return item;
    }


 

}
