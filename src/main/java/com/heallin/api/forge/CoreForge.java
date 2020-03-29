package com.heallin.api.forge;

import com.heallin.api.bukkit.Core;
import com.heallin.api.bukkit.entitiy.CorePlayer;
import com.heallin.api.forge.entitiy.CoreForgePlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import org.bukkit.command.Command;

import java.util.UUID;

public class CoreForge{

    private static Core core;

    public CoreForge(Core core){
        CoreForge.core = core;
    }

    public static CorePlayer getCorePlayer(EntityPlayer player){
        return core.getCorePlayer(player.getUniqueID());
    }

    @Deprecated
    public static CorePlayer getCorePlayer(UUID uuid){
        return core.getCorePlayer(uuid);
    }

    @Deprecated
    public static CorePlayer getCorePlayer(String name){
        return core.getCorePlayer(name);
    }

    public static boolean isOnline(CoreForgePlayerMP playerMP){
        return playerMP.isOnline();
    }

}
