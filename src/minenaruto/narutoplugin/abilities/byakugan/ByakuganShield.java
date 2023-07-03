package minenaruto.narutoplugin.abilities.byakugan;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.abilities.AbilityListener;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import minenaruto.narutoplugin.main.Main;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.BlockIterator;

public class ByakuganShield extends AbilitiesMain {
    private Item item = new Item(Material.DIAMOND_HOE, 67, "§7[§6Naruto§7] §5Риннеган Саске (Сохранение локаций)", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));
    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 100, 0, 0, 0, 0)) {
            //	List<ArmorStand> arrayArmorStand = spawnArmorStand(playerlocation, 1);
            runTaskAbility(pl, player);

        }
    }



    @Override
    public Item getItem() {
        return item;
    }

    public void runTaskAbility(NarutoPlayer np, Player player) {
        final ArmorStand stand = (ArmorStand)player.getWorld().spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);
        stand.setVisible(false);
        stand.setMarker(false);
        stand.getEquipment().setItemInMainHand(getModels().get("shieldblue").getItemStack());
        BukkitTask task = (new BukkitRunnable() {
            public void run() {
                stand.setRotation(stand.getLocation().getYaw() + 22.5F, 0.0F);
                for (Entity entity : player.getNearbyEntities(5.0D, 5.0D, 5.0D)) {
                    if (entity instanceof LivingEntity) {
                        if (entity instanceof Player) {
                            if (entity != player && hasPvpZone(entity)) {
                                entity.setVelocity(((LivingEntity)entity).getEyeLocation().getDirection().multiply(-1.5D));
                                addDamageEntity(player, (Player)entity, 5);
                            }
                            continue;
                        }
                        if (entity != stand) {
                            ((LivingEntity)entity).damage(5.0D);
                            entity.setVelocity(((LivingEntity)entity).getEyeLocation().getDirection().multiply(-1.5D));
                        }
                    }
                }
            }
        }).runTaskTimer((Plugin)Main.getInstance(), 0L, 0L);
        Bukkit.getScheduler()

                .scheduleSyncDelayedTask((Plugin)Main.getInstance(), () -> {
                    stand.remove();
                    Bukkit.getScheduler().cancelTask(task.getTaskId());
                },100L);



    }





}