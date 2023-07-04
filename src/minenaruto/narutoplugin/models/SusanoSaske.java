package minenaruto.narutoplugin.models;

import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;

public class SusanoSaske extends ModelsMain {
    private Item item = new Item(Material.DIAMOND_HOE, 126, "§7[§6Naruto§7] §cМодель сусано Саске", List.of("§7Кастомный предмет".split(";")));
    public SusanoSaske() {
        super();
        models.put("susano_saske", item);

    }


    @Override
    public Item getItem() {
        return item;
    }
}
