package minenaruto.narutoplugin.models;

import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.entity.Player;

import java.util.List;

public class SusanoMadara extends ModelsMain {
    private Item item = new Item(293, 128, "§7[§6Naruto§7] §cМодель сусано Мадара", List.of("§7Кастомный предмет".split(";")));
    public SusanoMadara() {
        super();
        models.put("susano_madara", item);

    }


    @Override
    public Item getItem() {
        return item;
    }
}
