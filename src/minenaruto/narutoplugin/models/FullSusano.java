package minenaruto.narutoplugin.models;

import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;

public class FullSusano extends ModelsMain {
    private Item item = new Item(Material.DIAMOND_HOE, 133, "§7[§6Naruto§7] §4Сусано полное", List.of("§7Кастомный предмет".split(";")));
    public FullSusano() {
        super();
        models.put("full_susano", item);
    }


    @Override
    public Item getItem() {
        return item;
    }
}
