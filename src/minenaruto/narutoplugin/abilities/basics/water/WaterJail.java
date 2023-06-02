package minenaruto.narutoplugin.abilities.basics.water;
import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.abilities.AbilityListener;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import minenaruto.narutoplugin.main.Main;
import org.bukkit.plugin.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.event.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import org.bukkit.event.block.*;
import org.bukkit.*;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.*;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.scheduler.*;
import org.bukkit.util.Vector;
import org.bukkit.entity.Entity;
public class WaterJail extends AbilitiesMain {
    private minenaruto.narutoplugin.iditems.Item item = new minenaruto.narutoplugin.iditems.Item(Material.DIAMOND_HOE, 64, "§7[§6Naruto§7] §bВодная тюрьма", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 50, 0, 0, 0, 0)) {
            runTaskAbility(player);
        }
    }

    @Override
    public void RightPlusShift(Player player, NarutoPlayer pl) {
        // TODO Auto-generated method stub
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 0, 0, 0, 0, 0)) {
            if(pl.IfHasJustuPointAndRemoveJustuPoint(5)) {
                //player.getInventory().addItem(Item.items.get(6).getItemStack());
            }
        }
    }

    public void runTaskAbility(Player player) {
        final LivingEntity entityTarget = rayTraceEntity(player, 30);
        if(entityTarget == null) {
            player.sendMessage("§7[§6Naruto§7] §f" + "Вы промахнулись и потратили чакру");
        }
        else {
            effect(player, entityTarget);
            final Location target = entityTarget.getLocation().clone();
            Vector vector = new Vector(0, 0, 0);
            if (player.getLocation().getWorld().equals(target.getWorld())) {
                vector = getDirection(player.getLocation(), getTargetedLocation(player, player.getLocation().distance(target), false, false, null));
            }
            vector.normalize();
            entityTarget.setVelocity(vector.multiply(5));
        }


    }

    @Override
    public Item getItem() {
        return item;
    }




    public static void effect(final LivingEntity p, LivingEntity target) {
        final BukkitRunnable runable = new BukkitRunnable() {
            int timer = 20 *  5;
            int timer5 = 20;

            public void run() {

                if (this.timer-- >= 0) {
                    if (this.timer5-- < 0) {

                        final Location location = getTargetedLocation(p, 3, false, false, null);

                        double distance = 0;
                        if (location.getWorld().equals(target.getWorld())) {
                            distance = location.distance(target.getLocation());
                        }
                        double dx, dy, dz;
                        dx = location.getX() - target.getLocation().getX();
                        dy = location.getY() - target.getLocation().getY();
                        dz = location.getZ() - target.getLocation().getZ();
                        Vector vector = new Vector(dx, dy, dz);
                        vector.normalize().multiply(.2);

                        if (distance < .3) {
                            vector = new Vector(0, 0, 0);
                        }
                        spawnSphere(target.getLocation());
                        target.setVelocity(vector);
                    }
                    else {
                        final Location location = getTargetedLocation( p, 3, false, false, null);
                        double distance = 0;
                        if (location.getWorld().equals(target.getWorld())) {
                            distance = location.distance(target.getLocation());
                        }
                        double dx, dy, dz;
                        dx = location.getX() - target.getLocation().getX();
                        dy = location.getY() - target.getLocation().getY();
                        dz = location.getZ() - target.getLocation().getZ();
                        Vector vector = new Vector(dx, dy, dz);
                        vector.normalize().multiply(.2);

                        if (distance < .3) {
                            vector = new Vector(0, 0, 0);
                        }
                        spawnSphere(target.getLocation());
                        target.setVelocity(vector);
                    }
                }
                else {
                    this.cancel();
                }
            }
        };
        runable.runTaskTimer((Plugin)Main.instance, 2L, 2L);
    }
    public static Vector getDirection(final Location location, final Location destination) {
        double x1, y1, z1;
        double x0, y0, z0;

        x1 = destination.getX();
        y1 = destination.getY();
        z1 = destination.getZ();

        x0 = location.getX();
        y0 = location.getY();
        z0 = location.getZ();

        return new Vector(x1 - x0, y1 - y0, z1 - z0);
    }
    public static boolean isTempBlock(final Block block) {
        return block != null ;
    }
    public static Location getTargetedLocation(final LivingEntity player, final double range, final boolean ignoreTempBlocks, final boolean checkDiagonals, final Material... nonOpaque2) {
        final Location origin = player.getEyeLocation();
        final Vector direction = origin.getDirection();

        final HashSet<Material> trans = new HashSet<Material>();
        trans.add(Material.AIR);


        if (nonOpaque2 != null) {
            for (final Material material : nonOpaque2) {
                trans.add(material);
            }
        }

        final Location location = origin.clone();
        final Vector vec = direction.normalize().multiply(0.2);

        for (double i = 0; i < range; i += 0.2) {
            location.add(vec);

            if (checkDiagonals && checkDiagonalWall(location, vec)) {
                location.subtract(vec);
                break;
            }

            final Block block = (Block) location.getBlock();

            if (trans.contains(((Block) block).getType())) {
                continue;
            } else if (ignoreTempBlocks && (isTempBlock(block))) {
                continue;
            } else {
                location.subtract(vec);
                break;
            }
        }

        return location;
    }
    public static boolean checkDiagonalWall(final Location location, final Vector direction) {
        final boolean[] xyzsolid = { false, false, false };
        for (int i = 0; i < 3; i++) {
            double value;
            if (i == 0) {
                value = direction.getX();
            } else if (i == 1) {
                value = direction.getY();
            } else {
                value = direction.getZ();
            }
            final BlockFace face = getBlockFaceFromValue(i, value);
            if (face == null) {
                continue;
            }
            xyzsolid[i] = location.getBlock().getRelative(face).getType().isSolid();
        }
        final boolean a = xyzsolid[0] && xyzsolid[2];
        final boolean b = xyzsolid[0] && xyzsolid[1];
        final boolean c = xyzsolid[1] && xyzsolid[2];
        return (a || b || c || (a && b));
    }
    public static BlockFace getBlockFaceFromValue(final int xyz, final double value) {
        switch (xyz) {
            case 0:
                if (value > 0) {
                    return BlockFace.EAST;
                } else if (value < 0) {
                    return BlockFace.WEST;
                } else {
                    return BlockFace.SELF;
                }
            case 1:
                if (value > 0) {
                    return BlockFace.UP;
                } else if (value < 0) {
                    return BlockFace.DOWN;
                } else {
                    return BlockFace.SELF;
                }
            case 2:
                if (value > 0) {
                    return BlockFace.SOUTH;
                } else if (value < 0) {
                    return BlockFace.NORTH;
                } else {
                    return BlockFace.SELF;
                }
            default:
                return null;
        }
    }
    public static void spawnSphere(Location loc) {
        for (double i = 0; i <= Math.PI; i += Math.PI / 20) {
            double radius = Math.sin(i);
            double y = Math.cos(i) + 1;
            for (double a = 0; a < Math.PI * 2; a+= Math.PI / 2) {
                double x = Math.cos(a) * radius;
                double z = Math.sin(a) * radius;
                loc.add(x, y, z);

                loc.getWorld().spawnParticle(Particle.WATER_BUBBLE, loc, 1, 61,61,255, 1);
                loc.subtract(x, y, z);

            }
        }
    }
}
