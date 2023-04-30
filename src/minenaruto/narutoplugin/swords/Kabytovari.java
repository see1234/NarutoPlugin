package minenaruto.narutoplugin.swords;

import minenaruto.narutoplugin.iditems.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.List;

public class Kabytovari extends SwordMain implements Listener {
    private minenaruto.narutoplugin.iditems.Item item = new minenaruto.narutoplugin.iditems.Item(293, 64, "§7[§6Naruto§7] §bАкула-Ракета", List.of("§7Использование:§f ПКМ;§7Получение новой способки:§f ПКМ+ШИФТ".split(";")));


    @EventHandler
    public void onDamage() {

    }
    @Override
    public Item getItem() {
        return item;
    }
}
