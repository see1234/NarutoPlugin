package minenaruto.narutoplugin.abilities.byakugan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import minenaruto.narutoplugin.abilities.ClearInterface;
import net.minecraft.server.v1_16_R3.DataWatcher;
import net.minecraft.server.v1_16_R3.PacketPlayOutEntityMetadata;
import org.apache.commons.lang.reflect.FieldUtils;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.*;

import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.abilities.AbilityListener;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;

public class ByakuganSpector extends AbilitiesMain implements ClearInterface {
    private Item item = new Item(Material.DIAMOND_HOE, 67, "§7[§6Naruto§7] §5Риннеган Саске (Сохранение локаций)", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));
    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 100, 0, 0, 0, 0)) {
            //	List<ArmorStand> arrayArmorStand = spawnArmorStand(playerlocation, 1);
            runTaskAbility(pl, player);

        }
    }
    @SuppressWarnings("unchecked")
    public static void setGlowing(Entity glowingPlayer, Player sendPacketPlayer, boolean glow) {
        try {
            net.minecraft.server.v1_16_R3.Entity entityPlayer = ((CraftEntity) glowingPlayer).getHandle();

            DataWatcher dataWatcher = entityPlayer.getDataWatcher();

            entityPlayer.glowing = glow; // For the update method in EntityPlayer to prevent switching back.

            // The map that stores the DataWatcherItems is private within the DataWatcher Object.
            // We need to use Reflection to access it from Apache Commons and change it.
            Map<Integer, DataWatcher.Item<?>> map = (Map<Integer, DataWatcher.Item<?>>) FieldUtils.readDeclaredField(dataWatcher, "d", true);

            // Get the 0th index for the BitMask value. http://wiki.vg/Entities#Entity
            @SuppressWarnings("rawtypes")
            DataWatcher.Item item = map.get(0);
            byte initialBitMask = (Byte) item.b(); // Gets the initial bitmask/byte value so we don't overwrite anything.
            byte bitMaskIndex = (byte) 0x40; // The index as specified in wiki.vg/Entities
            if (glow) {
                item.a((byte) (initialBitMask | 1 << bitMaskIndex));
            } else {
                item.a((byte) (initialBitMask & ~(1 << bitMaskIndex))); // Inverts the specified bit from the index.
            }



            PacketPlayOutEntityMetadata metadataPacket = new PacketPlayOutEntityMetadata(glowingPlayer.getEntityId(), dataWatcher, true);

            ((CraftPlayer) sendPacketPlayer).getHandle().playerConnection.sendPacket(metadataPacket);
        } catch (IllegalAccessException e) { // Catch statement necessary for FieldUtils.readDeclaredField()
            e.printStackTrace();
        }
    }



    @Override
    public Item getItem() {
        return item;
    }
    public HashMap<String, List<Entity>> ents;
    public void runTaskAbility(NarutoPlayer np, Player player) {
        List<Entity> entities = new ArrayList<Entity>();
        for(Entity en : player.getNearbyEntities(50,50,50)) {
            if(en != player) {
                setGlowing(en, player, true);
                entities.add(en);
            }
        }
        ents.put(player.getName(), entities);
        for(Entity en : entities) {
            if(player == null) {
                break;
            }
            setGlowing(en, player, false);
        }

    }


    @Override
    public void clear() {
       for(Map.Entry<String, List<Entity>> map : ents.entrySet()) {
           String nick = map.getKey();
           for(Entity en : map.getValue()) {
               if(Bukkit.getPlayer(nick) == null) {
                   break;
               }
               setGlowing(en, Bukkit.getPlayer(nick), false);
           }
       }
       ents.clear();
    }
}