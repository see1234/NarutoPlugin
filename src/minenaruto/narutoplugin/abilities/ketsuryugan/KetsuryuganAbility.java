package minenaruto.narutoplugin.abilities.ketsuryugan;

import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.abilities.AbilityListener;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import java.util.List;

public class KetsuryuganAbility extends AbilitiesMain {
    protected Item item = new Item(Material.DIAMOND_HOE, 75, "§7[§6Naruto§7] §cКэтсюрюган",
            List.of("§7Использование:§f ПКМ;§7Смена способности:§f ПКМ+SHIFT".split(";")));

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        return;
    }

    @Override
    public Item getItem() {
        return item;
    }

    protected boolean checkChakra(Player player, int amount) {
        return AbilityListener.checkChakraItem(player, getItem().getName(), amount, 0, 0, 0, 0);
    }
}