package minenaruto.narutoplugin.abilities.sage;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.abilities.AbilityListener;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import minenaruto.narutoplugin.main.Main;

public class EarthWall extends AbilitiesMain {
    private Item item = new Item(Material.DIAMOND_HOE, 80, "§7[§6Naruto§7] §6Sage (Earth Wall)", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));

    // Хранилище для блоков стены
    private final List<BlockDataSnapshot> wallBlocks = new ArrayList<>();

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        if (AbilityListener.checkChakraItem(player, getItem().getName(), 30, 0, 0, 0, 0)) {
            useEarthWall(player);
        }
    }

    public void useEarthWall(Player player) {
        Location location = player.getLocation().add(player.getLocation().getDirection().multiply(3));

        // Очищаем старые блоки, если они есть
        removeWall();

        // Создаем новую стену
        for (int x = -2; x <= 2; x++) {
            for (int y = 0; y <= 3; y++) {
                Location blockLoc = location.clone().add(x, y, 0);
                Block block = blockLoc.getBlock();

                // Сохраняем информацию о блоке (тип и данные)
                wallBlocks.add(new BlockDataSnapshot(block));

                // Создаем стену из земли
                block.setType(Material.DIRT);
            }
        }

        // Удаляем стену через 5 секунд
        Bukkit.getScheduler().runTaskLater(Main.getInstance(), this::removeWall, 100L);
    }

    // Метод для удаления стены
    private void removeWall() {
        for (BlockDataSnapshot blockData : wallBlocks) {
            Block block = blockData.location.getBlock();

            // Проверяем, что блок находится в том же месте, что и раньше
            if (block.getLocation().equals(blockData.location)) {
                // Восстанавливаем тип и данные блока
                block.setBlockData(blockData.blockData);
            }
        }
        wallBlocks.clear(); // Очищаем список блоков
    }

    @Override
    public Item getItem() {
        return item;
    }

    // Вспомогательный класс для хранения информации о блоке
    private static class BlockDataSnapshot {
        public final Location location;
        public final org.bukkit.block.data.BlockData blockData;

        public BlockDataSnapshot(Block block) {
            this.location = block.getLocation();
            this.blockData = block.getBlockData().clone(); // Важно клонировать!
        }
    }
}
