package minenaruto.narutoplugin.abilities.clan.hagoromo;

import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import java.util.*;

public abstract class HagoromoAbility extends AbilitiesMain {
    protected final String abilityName;

    public HagoromoAbility(String abilityName) {
        this.abilityName = abilityName;
    }

    @Override
    public Item getItem() {
        return new Item(Material.DIAMOND_HOE, 25,
            "§7[§6Naruto§7] §bHagoromo: " + abilityName,
            List.of("§7Использование:§f ПКМ;§7Смена способности:§f ПКМ+SHIFT".split(";")));
    }

    @Override
    public abstract void RightClick(Player player, NarutoPlayer pl);
}

