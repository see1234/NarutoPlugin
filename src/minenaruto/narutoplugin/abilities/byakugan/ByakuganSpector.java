package minenaruto.narutoplugin.abilities.byakugan;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.abilities.AbilityListener;
import minenaruto.narutoplugin.abilities.ClearInterface;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class ByakuganSpector extends AbilitiesMain implements ClearInterface {
    private final Item item = new Item(Material.DIAMOND_HOE, 74, "§7[§6Naruto§7] §fБьякуган (Наблюдатель)", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));
    private final Map<String, List<Entity>> glowingEntities = new HashMap<>();
    private final ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 100, 0, 0, 0, 0)) {
            runTaskAbility(pl, player);
        }
    }

    @Override
    public Item getItem() {
        return item;
    }

    /**
     * Подсвечивает всех мобов в радиусе 30 блоков для конкретного игрока.
     *
     * @param np     Игрок, использующий способность.
     * @param player Игрок, для которого применяется эффект.
     */
    public void runTaskAbility(NarutoPlayer np, Player player) {
        List<Entity> entities = new ArrayList<>();

        // Получаем все сущности в радиусе 30 блоков
        for (Entity entity : player.getNearbyEntities(30, 30, 30)) {
            // Проверяем, что сущность является мобом (LivingEntity)
            if (entity instanceof org.bukkit.entity.LivingEntity && !entity.equals(player)) {
                setGlowing(entity, player, true); // Подсвечиваем сущность
                entities.add(entity); // Добавляем сущность в список
            }
        }

        // Сохраняем список подсвеченных сущностей
        glowingEntities.put(player.getName(), entities);

        // Запускаем таймер для очистки эффекта через 10 секунд (200 тиков)
        Bukkit.getScheduler().runTaskLater((JavaPlugin) Bukkit.getPluginManager().getPlugin("NarutoPlugin"), () -> {
            clearGlowing(player);
        }, 200L);
    }

    /**
     * Устанавливает или убирает эффект свечения для сущности.
     *
     * @param entity Сущность, для которой применяется эффект.
     * @param player Игрок, который видит эффект.
     * @param glow   true - добавить эффект, false - убрать.
     */
    public void setGlowing(Entity entity, Player player, boolean glow) {
        // Создаем пакет для обновления метаданных сущности
        PacketContainer packet = protocolManager.createPacket(com.comphenix.protocol.PacketType.Play.Server.ENTITY_METADATA);
        packet.getIntegers().write(0, entity.getEntityId()); // ID сущности

        // Создаем DataWatcher для установки флага glowing
        WrappedDataWatcher dataWatcher = new WrappedDataWatcher();
        WrappedDataWatcher.Serializer serializer = WrappedDataWatcher.Registry.get(Byte.class);
        byte flags = 0;
        if (glow) {
            flags |= 0x40; // Устанавливаем флаг glowing (6-й бит)
        }
        dataWatcher.setObject(0, serializer, flags); // Обновляем флаги

        packet.getWatchableCollectionModifier().write(0, dataWatcher.getWatchableObjects());

        // Отправляем пакет игроку
        try {
            protocolManager.sendServerPacket(player, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Очищает эффект свечения для всех сущностей, подсвеченных игроком.
     *
     * @param player Игрок, для которого очищается эффект.
     */
    public void clearGlowing(Player player) {
        List<Entity> entities = glowingEntities.get(player.getName());
        if (entities != null) {
            for (Entity entity : entities) {
                setGlowing(entity, player, false); // Убираем эффект свечения
            }
            glowingEntities.remove(player.getName()); // Очищаем список
        }
    }

    @Override
    public void clear() {
        for (Map.Entry<String, List<Entity>> entry : glowingEntities.entrySet()) {
            Player player = Bukkit.getPlayer(entry.getKey());
            if (player != null) {
                for (Entity entity : entry.getValue()) {
                    setGlowing(entity, player, false); // Убираем эффект свечения
                }
            }
        }
        glowingEntities.clear(); // Очищаем карту
    }
}