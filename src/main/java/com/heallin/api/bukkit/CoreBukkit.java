package com.heallin.api.bukkit;

import com.heallin.api.bukkit.entitiy.CorePlayer;
import com.heallin.api.bukkit.file.YamlManager;
import com.zeyilinxin.heallinlib.HealLinCatServer;
import com.zeyilinxin.heallinlib.plugin.HealLinPlugin;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.UUID;

public class CoreBukkit {

    private static Core core;
    private static HealLinCatServer catServer = HealLinCatServer.instance();

    public CoreBukkit(HealLinCatServer healLinPlugin){
        if (core == null){
            core = new Core(healLinPlugin);
        }
    }

    public static int getVersion(){
        return catServer.getVersion();
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

    public static YamlManager getYaml(File file){
        return YamlManager.getInstance(file);
    }








}
