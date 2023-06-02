package minenaruto.narutoplugin.models;

import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;

public class Shark extends ModelsMain {
    private Item item = new Item(Material.DIAMOND_HOE, 102, "§7[§6Naruto§7] §bАкула", List.of("§7Кастомный предмет".split(";")));
    public Shark() {
        super();
        models.put("shark", item);
    }

    @Override
    public Item getItem() {
        return item;
    }
}
