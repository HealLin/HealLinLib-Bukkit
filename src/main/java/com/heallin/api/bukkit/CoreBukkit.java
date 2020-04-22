package com.heallin.api.bukkit;

import com.heallin.api.bukkit.entitiy.CorePlayer;
import com.zeyilinxin.heallinlib.HealLinCatServer;
import com.zeyilinxin.heallinlib.plugin.HealLinPlugin;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CoreBukkit {

    private static Core core;

    public CoreBukkit(HealLinCatServer healLinPlugin){
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

    public static boolean isOnline(CorePlayer corePlayer){
        return corePlayer.isOnline();
    }

    public static CraftServer getCraftServer(){
        return core.getCraftServer();
    }

    public static CorePlayer[] getWorldPlayers(String worldName){
        return core.getWorldPlayers(worldName);
    }






}
