package com.heallin.api.bukkit.version.R12;

import com.heallin.api.bukkit.CoreBukkit;
import com.heallin.api.bukkit.entitiy.CorePlayer;
import com.heallin.api.bukkit.version.MinecraftBukkitApi;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class R12 implements MinecraftBukkitApi {


    @Override
    public void sendAll(Packet<?> packet){
        for (CorePlayer p : CoreBukkit.getOnlinePlayers()){
            p.send(packet);
        }
    }

    @Override
    public void send(CorePlayer player, Packet<?> packet) {
        player.send(packet);
    }

    @Override
    public void send(Player player , Packet<?> packet){
        CoreBukkit.getCorePlayer(player).send(packet);
    }

    @Override
    public Object usePacketPlayOutEntityDestroy(int... id) {
        return new PacketPlayOutEntityDestroy(id);
    }

    @Override
    public EntityArmorStand getEntityArmorStand(){
        return new EntityArmorStand(((CraftWorld)Bukkit.getWorlds().get(0)).getHandle());
    }

    @Override
    public Object usePacketPlayOutEntityMetadata(){
        return new PacketPlayOutEntityMetadata();
    }

    private CraftPlayer getCraftPlayer(Player player){
        return (CraftPlayer) player;
    }

    private EntityPlayer getEntityPlayer(Player player){
        return this.getCraftPlayer(player).getHandle();
    }


}
