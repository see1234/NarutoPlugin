package minenaruto.narutoplugin.models;

import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;

public class Susano extends ModelsMain {
    private Item item = new Item(Material.DIAMOND_HOE, 129, "§7[§6Naruto§7] §cМодель сусано", List.of("§7Кастомный предмет".split(";")));
    public Susano() {
        super();
        models.put("susano", item);

    }


    @Override
    public Item getItem() {
        return item;
    }
}
