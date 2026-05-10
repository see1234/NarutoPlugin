package minenaruto.narutoplugin.abilities.clan.kohaku;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.main.Main;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class GoldenSandBulletTechnique extends KohakuTechnique implements Listener {
    public GoldenSandBulletTechnique() {
        super("Золотая Песчаная Пуля");
        Main.getInstance().getServer().getPluginManager().registerEvents(this, Main.getInstance());
    }

    @Override
    public void RightClick(Player player, NarutoPlayer pl) {
        Snowball bullet = player.launchProjectile(Snowball.class);
        bullet.setVelocity(player.getLocation().getDirection().multiply(2));
        bullet.getWorld().spawnParticle(Particle.BLOCK_CRACK, bullet.getLocation(), 5, Material.GOLD_BLOCK.createBlockData());

        bullet.addScoreboardTag("kohaku_bullet");
    }

    @EventHandler
    public void onBulletHit(ProjectileHitEvent e) {
        if (e.getEntity().getScoreboardTags().contains("kohaku_bullet") && e.getHitEntity() instanceof LivingEntity) {
            ((LivingEntity) e.getHitEntity()).damage(6);
            e.getEntity().getWorld().spawnParticle(Particle.BLOCK_CRACK, e.getEntity().getLocation(), 20, Material.GOLD_BLOCK.createBlockData());
        }
    }
}
