package minenaruto.narutoplugin.models;

import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import org.bukkit.entity.Player;

import java.util.List;

public class FireBall extends ModelsMain {
    private Item item = new Item(293, 111, "§7[§6Naruto§7] §6Огненный шар", List.of("§7Кастомный предмет".split(";")));
    public FireBall() {
        super();
        models.put("fireball", item);
    }


    @Override
    public Item getItem() {
        return item;
    }
}
