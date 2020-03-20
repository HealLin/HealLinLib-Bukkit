package com.zeyilinxin.heallinlib.forge.player;

import com.zeyilinxin.heallinlib.forge.HealLinForge;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ForgeEntityPlayer {

    private EntityPlayerMP entityPlayerMP;

    public ForgeEntityPlayer(Player player){
        this(HealLinForge.getEntityPlayerMP(player));
    }

    public ForgeEntityPlayer(EntityPlayerMP entityPlayerMP){
        this.entityPlayerMP = entityPlayerMP;
    }

    public World getWorld(){
        return entityPlayerMP.func_130014_f_();
    }

    public UUID getUUID(){
        return this.entityPlayerMP.func_110124_au();
    }

    public WorldServer getWorldServer(){
        return (WorldServer) this.getWorld();
    }

    public Player getBukkit(){
        return Bukkit.getPlayer(this.getUUID());
    }

    public EntityPlayerMP getEntityPlayerMP() {
        return entityPlayerMP;
    }
}
