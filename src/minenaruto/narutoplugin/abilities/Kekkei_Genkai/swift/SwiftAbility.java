package minenaruto.narutoplugin.abilities.Kekkei_Genkai.swift;

import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.*;

public abstract class SwiftAbility extends AbilitiesMain {
    protected final String abilityName;

    public SwiftAbility(String abilityName) {
        this.abilityName = abilityName;
    }

    @Override
    public Item getItem() {
        return new Item(Material.DIAMOND_HOE, 70,
            "§7[§6Naruto§7] §bSwift: " + abilityName,
            List.of("§7Использование:§f ПКМ;§7Смена способности:§f ПКМ+SHIFT".split(";")));
    }
}

