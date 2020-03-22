package com.heallin.api.bukkit;

import com.heallin.api.bukkit.entitiy.CorePlayer;
import com.zeyilinxin.heallinlib.plugin.HealLinPlugin;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CoreBukkit {

    private static Core core;

    public CoreBukkit(HealLinPlugin healLinPlugin){
        if (core == null){
            core = new Core(healLinPlugin);
        }
    }

    public static CorePlayer getCorePlayer(UUID uuid){
        return core.getCorePlayer(uuid);
    }

    public static CorePlayer getCorePlayer(String name){
        return core.getCorePlayer(name);
    }

    public static CorePlayer getCorePlayer(Player player){
        return core.getCorePlayer(player);
    }

    public static CorePlayer[] getOnlinePlayers(){
        return core.getOnlinePlayers();
    }

    public static CoreServer getServer(){
        return core;
    }





}
