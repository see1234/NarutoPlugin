package minenaruto.narutoplugin.abilities.clan.ryu;

import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.*;

public abstract class RyuTechnique extends AbilitiesMain {
    protected final String techniqueName;

    public RyuTechnique(String techniqueName) {
        this.techniqueName = techniqueName;
    }

    @Override
    public Item getItem() {
        return new Item(Material.DIAMOND_HOE, 39,
            "§7[§6Naruto§7] §cРю: " + techniqueName,
            List.of("§7Активация:§f ПКМ;§7Смена техники:§f ПКМ+SHIFT".split(";")));
    }
}
