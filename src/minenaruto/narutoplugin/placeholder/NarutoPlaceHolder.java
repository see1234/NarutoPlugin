package minenaruto.narutoplugin.placeholder;

import java.util.ArrayList;

import minenaruto.narutoplugin.dataplayer.NarutoPlayer;
import minenaruto.narutoplugin.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;

/**
 * This class will be registered through the register-method in the
 * plugins onEnable-method.
 */
public class NarutoPlaceHolder extends PlaceholderExpansion {

    private Main plugin;

    public NarutoPlaceHolder(Main plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean persist(){
        return true;
    }

    @Override
    public boolean canRegister(){
        return true;
    }

    @Override
    public String getAuthor(){
        return plugin.getDescription().getAuthors().toString();
    }

    @Override
    public String getIdentifier(){
        return "naruto";
    }

    @Override
    public String getVersion(){
        return plugin.getDescription().getVersion();
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier){
        if(player == null){
            return "";
        }
        NarutoPlayer np = NarutoPlayer.getNarutoPlayer(player.getName());
        if(np == null) {
            return "null";
        }
        if(identifier.contains("getMaxChakra")) {
            return "" + ((np.getInt("ninjustu") * 10) + 100);
        }
        if(identifier.contains("getMaxChakra")) {
            return "" + ((np.getInt("ninjustu") * 10) + 100);
        }
        return np.getString(identifier);
    }
}