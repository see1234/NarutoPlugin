package minenaruto.narutoplugin.abilities.clan.inuzuki;
import minenaruto.narutoplugin.abilities.AbilitiesMain;
import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.iditems.Item;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import java.util.*;
public class BeastMimicryTechnique extends InuzukaTechnique {
    public BeastMimicryTechnique() { super("Звериная Мимикрия"); }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 300, 2));
        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 300, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 300, 1));
        
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_WOLF_HOWL, 1, 1);
        player.getWorld().spawnParticle(Particle.VILLAGER_ANGRY, player.getLocation(), 20);
    }
}