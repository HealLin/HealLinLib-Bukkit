package com.corelin.library.plugin;

import com.corelin.library.CoreLib;
import com.corelin.library.api.config.CoreLinConfig;
import com.corelin.library.plugin.log.Log;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.util.Vector;

import java.util.Iterator;
import java.util.Optional;

public class ServerPlugin {

    protected CoreLib coreLib;
   // protected CraftServer craftServer;
    protected SimplePluginManager pluginManager;
    protected CoreLinConfig coreLinConfig;
    @Getter
    protected Log log;
    @Getter
    private CoreLinPlugin plugin;


    public ServerPlugin(CoreLinPlugin plugin, CoreLib coreLib) {
        this.plugin = plugin;
        this.coreLib = coreLib;
    }

    public boolean ini(){
        //this.craftServer = (CraftServer) Bukkit.getServer();
        //this.pluginManager = (SimplePluginManager) this.craftServer.getPluginManager();
        this.coreLinConfig = new CoreLinConfig(coreLib , plugin);
        log = new Log(this , coreLinConfig.getDirectoryFile());
        if (plugin.isConfig()){
            coreLinConfig.ini();
        }
        return true;
    }


/*
    public Optional<Player> getPlayer(String playerName){
        Optional<Player> optionalPlayer = Optional.of(craftServer.getHandle().getPlayer(playerName).getBukkitEntity());
        return optionalPlayer;
    }*/



    public Entity getCursorTarget(Player p, double range){
        Block block;
        Entity target;
        Iterator<Entity> entities;
        Location loc = p.getEyeLocation();
        Vector vec = loc.getDirection().multiply(0.15);
        while((range-=0.1)>0 && ((block = loc.getWorld().getBlockAt(loc)).isLiquid() || block.isEmpty())){
            entities = loc.getWorld().getNearbyEntities(loc.add(vec), 0.001, 0.001, 0.001).iterator();
            while(entities.hasNext()){
                if((target = entities.next()) != p){
                    return target;
                }
            }
        }
        return null;
    }


    public CoreLinConfig getCoreLinConfig(){
        return coreLinConfig;
    }


   /* public CraftServer getCraftServer() {
        return craftServer;
    }*/
}
