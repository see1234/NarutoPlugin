package minenaruto.narutoplugin.models;

import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;

public class ShieldBlue extends ModelsMain {
    private Item item = new Item(Material.DIAMOND_HOE, 87, "§7[§6Naruto§7] §cМодель для багугана", List.of("§7Кастомный предмет".split(";")));
    public ShieldBlue() {
        super();
        models.put("shieldblue", item);
    }


    @Override
    public Item getItem() {
        return item;
    }
}
