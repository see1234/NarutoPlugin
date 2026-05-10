package minenaruto.narutoplugin.abilities.ketsuryugan;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import minenaruto.narutoplugin.main.Main;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import java.util.*;

public class BloodControlAbility extends KetsuryuganAbility {
    public BloodControlAbility() {
        item = new Item(Material.DIAMOND_HOE, 75, "§7[§6Naruto§7] §cКэтсюрюган Контроль Крови",
                List.of("§7Использование:§f ПКМ;§7Смена способности:§f ПКМ+SHIFT".split(";")));
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (checkChakra(player, 30)) {
            for (Entity entity : player.getNearbyEntities(10, 10, 10)) {
                if (entity instanceof LivingEntity && entity != player) {
                    controlBlood(player, (LivingEntity) entity);
                }
            }
            player.sendMessage("§cКэтсюрюган: §fКонтроль крови активирован!");
            player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_HURT, 1, 0.5f);
        }
    }

    private void controlBlood(Player player, LivingEntity target) {
        drawBloodLine(target.getEyeLocation(), player.getLocation(), player);

        target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20 * 5, 2));
        target.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 20 * 3, 1));

        new BukkitRunnable() {
            int ticks = 0;

            @Override
            public void run() {
                if (ticks++ >= 20 * 5 || target.isDead()) {
                    cancel();
                    return;
                }

                target.getWorld().spawnParticle(Particle.DAMAGE_INDICATOR,
                        target.getLocation().add(0, 1, 0), 5, 0.5, 0.5, 0.5, 0);
            }
        }.runTaskTimer(Main.getInstance(), 0, 5);
    }

    private void drawBloodLine(Location from, Location to, Player player) {
        World world = from.getWorld();
        double distance = from.distance(to);
        Vector direction = to.toVector().subtract(from.toVector()).normalize();

        new BukkitRunnable() {
            double current = 0;

            @Override
            public void run() {
                if (current >= distance) {
                    cancel();
                    return;
                }

                Vector increment = direction.clone().multiply(current);
                Location point = from.clone().add(increment);

                world.spawnParticle(Particle.REDSTONE, point, 1,
                        new Particle.DustOptions(Color.RED, 1));

                current += 0.3;
            }
        }.runTaskTimer(Main.getInstance(), 0, 1);
    }
}