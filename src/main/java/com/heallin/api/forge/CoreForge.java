package com.heallin.api.forge;

import com.heallin.api.bukkit.Core;
import com.heallin.api.bukkit.entitiy.CorePlayer;
import net.minecraft.entity.player.EntityPlayerMP;

import java.util.UUID;

public class CoreForge{

    private static Core core;

    public CoreForge(Core core){
        CoreForge.core = core;
    }

    public static CorePlayer getCorePlayer(EntityPlayerMP playerMP){
        return core.getCorePlayer(playerMP.func_110124_au());
    }

    @Deprecated
    public static CorePlayer getCorePlayer(UUID uuid){
        return core.getCorePlayer(uuid);
    }

    @Deprecated
    public static CorePlayer getCorePlayer(String name){
        return core.getCorePlayer(name);
    }
}
