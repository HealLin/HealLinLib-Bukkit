package com.heallin.api.bukkit.version;

import com.heallin.api.bukkit.version.R12.R12;
import com.zeyilinxin.heallinlib.enums.GameVersion;

public class MinecraftBukkit {

    public static MinecraftBukkitApi getApi(){
        String version = GameVersion.getGameVersion();
        switch (version){
            case "1.12":{
                return new R12();
            }
            default:{
                return null;
            }
        }
    }
}
