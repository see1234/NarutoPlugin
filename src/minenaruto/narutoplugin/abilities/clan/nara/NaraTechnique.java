package minenaruto.narutoplugin.abilities.clan.nara;

import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import minenaruto.narutoplugin.main.Main;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import java.util.*;

public abstract class NaraTechnique extends AbilitiesMain {
    protected final String techniqueName;

    public NaraTechnique(String techniqueName) {
        this.techniqueName = techniqueName;
    }

    @Override
    public Item getItem() {
        return new Item(Material.DIAMOND_HOE, 37,
            "§7[§6Naruto§7] §8Nara: " + techniqueName,
                List.of("§7Использование:§f ПКМ;§7Смена способности:§f ПКМ+SHIFT".split(";")));
    }

}
