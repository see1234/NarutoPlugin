package minenaruto.narutoplugin.models;

import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;

public class SusanoItachi extends ModelsMain {
    private Item item = new Item(Material.DIAMOND_HOE, 125, "§7[§6Naruto§7] §cМодель сусано Итачи", List.of("§7Кастомный предмет".split(";")));
    public SusanoItachi() {
        super();
        models.put("susano_itachi", item);

    }


    @Override
    public Item getItem() {
        return item;
    }
}
