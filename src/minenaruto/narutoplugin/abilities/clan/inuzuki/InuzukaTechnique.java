package minenaruto.narutoplugin.abilities.clan.inuzuki;

import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import java.util.*;

public abstract class InuzukaTechnique extends AbilitiesMain {
    protected final String techniqueName;

    public InuzukaTechnique(String techniqueName) {
        this.techniqueName = techniqueName;
    }

    @Override
    public Item getItem() {
        return new Item(Material.DIAMOND_HOE, 30,
            "§7[§6Naruto§7] §eИнузука: " + techniqueName,
            List.of("§7Активация:§f ПКМ;§7Смена техники:§f ПКМ+SHIFT".split(";")));
    }
}