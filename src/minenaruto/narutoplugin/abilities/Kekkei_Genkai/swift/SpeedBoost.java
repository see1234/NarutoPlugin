package minenaruto.narutoplugin.abilities.Kekkei_Genkai.swift;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

// Способность 1: Speed Boost (Ускорение)
public class SpeedBoost extends SwiftAbility {
    public SpeedBoost() {
        super("Ускорение");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 2));
    }
}
