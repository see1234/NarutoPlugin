package minenaruto.narutoplugin.models;

import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;

public class Stone extends ModelsMain {
    private Item item = new Item(Material.DIAMOND_HOE, 86, "§7[§6Naruto§7] §cМодель камней", List.of("§7Кастомный предмет".split(";")));
    public Stone() {
        super();
        models.put("stone", item);
    }


    @Override
    public Item getItem() {
        return item;
    }
}
