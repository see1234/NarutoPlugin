package minenaruto.narutoplugin.abilities.Kekkei_Genkai.swift;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

// Способность 3: Agility (Ловкость)
public class Agility extends SwiftAbility {
    public Agility() {
        super("Ловкость");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 200, 2));
    }
}
