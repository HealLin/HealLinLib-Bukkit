package com.heallin.api.forge.entitiy;

import com.heallin.api.bukkit.entitiy.CorePlayer;
import com.heallin.api.bukkit.entitiy.CorePublicPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public interface CoreForgePlayerMP extends CorePublicPlayer {


    World getWorld();

    WorldServer getWorldServer();

}
