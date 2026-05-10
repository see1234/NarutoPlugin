package minenaruto.narutoplugin.abilities.clan.fuma;

import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.util.Vector;
import java.util.*;

public abstract class FumaAbility extends AbilitiesMain {
    protected final String abilityName;

    public FumaAbility(String abilityName) {
        this.abilityName = abilityName;
    }

    @Override
    public Item getItem() {
        return new Item(Material.DIAMOND_HOE, 24,
            "§7[§6Naruto§7] §eFuma: " + abilityName,
            List.of("§7Использование:§f ПКМ;§7Смена способности:§f ПКМ+SHIFT".split(";")));
    }

    @Override
    public abstract void RightClick(Player player, NarutoPlayer pl);
}

