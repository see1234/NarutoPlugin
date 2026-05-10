package minenaruto.narutoplugin.abilities.basics;

import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.abilities.AbilityListener;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import minenaruto.narutoplugin.main.Main;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Tutorial extends AbilitiesMain {
    private Item item = new Item(Material.DIAMOND_HOE, 5, "§7[§6Naruto§7] §aТуториал", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 0, 0, 0, 0, 0)) {
            runTaskAbility(player);
        }
    }


    public void runTaskAbility(Player player) {
       int abilities = ThreadLocalRandom.current().nextInt(1,3);
       AbilitiesMain startWater = null;
       AbilitiesMain startFire = null;
       AbilitiesMain startEarth = null;
       AbilitiesMain startWind = null;
       AbilitiesMain startLigthning = null;
       for(AbilitiesMain abilitiesMain : Main.getInstance().getAbilities()) {
           if(abilitiesMain.getItem().getItemStack().getItemMeta().getDisplayName().contains("Водяной пузырь")) {
               if(startWater == null) {
                   startWater = abilitiesMain;
               }
           }
           if(abilitiesMain.getItem().getItemStack().getItemMeta().getDisplayName().contains("Огненный дракон")) {
               if(startFire == null) {
                   startFire = abilitiesMain;
               }
           }
           if(abilitiesMain.getItem().getItemStack().getItemMeta().getDisplayName().contains("Землетрясение")) {
               if(startEarth == null) {
                   startEarth = abilitiesMain;
               }
           }
           if(abilitiesMain.getItem().getItemStack().getItemMeta().getDisplayName().contains("Воздушный клон")) {
               if(startWind == null) {
                   startWind = abilitiesMain;
               }
           }
           if(abilitiesMain.getItem().getItemStack().getItemMeta().getDisplayName().contains("Молния-стрела")) {
               if(startLigthning == null) {
                   startLigthning = abilitiesMain;
               }
           }
       }
       List<AbilitiesMain> list = new ArrayList<AbilitiesMain>();
       list.add(startLigthning);
       list.add(startFire);
       list.add(startEarth);
       list.add(startWind);
       list.add(startWater);
        Stack<AbilitiesMain> selectedAbilities = new Stack<>();

        for (int i = 0; i < abilities; i++) {
            int randomIndex = ThreadLocalRandom.current().nextInt(list.size());
            selectedAbilities.add(list.get(randomIndex));
        }
        for(AbilitiesMain abilitiesMain : selectedAbilities) {
            player.getInventory().addItem(abilitiesMain.getItem().getItemStack());

        }
        player.getInventory().setItemInMainHand(null);
    }

    @Override
    public Item getItem() {
        return item;
    }



}