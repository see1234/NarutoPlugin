package minenaruto.narutoplugin.abilities.Kekkei_Genkai.boil;

import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import java.util.List;

public abstract class BoilAbility extends AbilitiesMain {
    protected final String abilityName;

    public BoilAbility(String abilityName) {
        this.abilityName = abilityName;
    }

    @Override
    public Item getItem() {
        return new Item(Material.DIAMOND_HOE, 55,
                "§7[§6Naruto§7] §dBoil: " + abilityName,
                List.of("§7Использование:§f ПКМ;§7Смена способности:§f ПКМ+SHIFT".split(";")));
    }


}