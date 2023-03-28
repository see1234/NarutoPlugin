package minenaruto.narutoplugin.abilities.reningan;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.abilities.AbilityListener;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import minenaruto.narutoplugin.main.Main;

public class ReninganSaskeTeleport extends AbilitiesMain {
    private Item item = new Item(293, 72, "§7[§6Naruto§7] §5Риннеган Саске (Замещение)", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));
    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 100, 0, 0, 0, 0)) {
            //	List<ArmorStand> arrayArmorStand = spawnArmorStand(playerlocation, 1);
            runTaskAbility(player);

        }
    }

    @Override
    public void RightPlusShift(Player player, NarutoPlayer pl) {
        // TODO Auto-generated method stub
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 0, 0, 0, 0, 0)) {
            if(pl.IfHasJustuPointAndRemoveJustuPoint(5)) {
                //player.getInventory().addItem(Item.items.get(16).getItemStack());
            }
        }
    }

    @Override
    public Item getItem() {
        return item;
    }

    public void runTaskAbility(Player player) {
        final LivingEntity entityTarget = rayTraceEntity(player, 30);
        if(entityTarget == null) {
            player.sendMessage("§7[§6Naruto§7] §f" + "Вы промахнулись и потратили чакру");
        }
        else {
            Location clone = entityTarget.getLocation().clone();
            entityTarget.teleport(clone);
            player.teleport(entityTarget);
        }



    }





}