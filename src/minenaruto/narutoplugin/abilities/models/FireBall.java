package minenaruto.narutoplugin.abilities.models;

import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import org.bukkit.entity.Player;

import java.util.List;

public class FireBall extends AbilitiesMain {
    private Item item = new Item(293, 111, "§7[§6Naruto§7] §6Огненный шар", List.of("§7Кастомный предмет".split(";")));
    public FireBall() {
        super();
        models.put("fireball", item);
    }
    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        //..
    }

    @Override
    public void RightPlusShift(Player player, NarutoPlayer pl) {
//..
    }

    @Override
    public Item getItem() {
        return item;
    }
}
