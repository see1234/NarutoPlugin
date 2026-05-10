package minenaruto.narutoplugin.abilities.Kekkei_Genkai.medical;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

// Способность 4: Heal Aura (Аура Исцеления)
public class HealAura extends MedicalAbility {
    public HealAura() {
        super("Аура Исцеления");
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        for (Entity entity : player.getNearbyEntities(5, 5, 5)) {
            if (entity instanceof LivingEntity) {
                ((LivingEntity) entity).setHealth(((LivingEntity) entity).getHealth() + 2); // Восстановление здоровья
            }
        }
    }
}
