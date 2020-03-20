package com.zeyilinxin.heallinlib.enums;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;

public class  GameVersion {


    public static String getGameVersion(){
        String version = ((CraftServer)Bukkit.getServer()).getServer().getVersion();
        if (get(version , "1.12")){
            return "1.12";
        }
        return "";

    }

    static boolean get(String version , String game){
        if (version.indexOf(game) != -1){
            return true;
        }
        return false;
    }

}
