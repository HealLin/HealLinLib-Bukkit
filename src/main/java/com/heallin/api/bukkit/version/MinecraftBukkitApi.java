package com.heallin.api.bukkit.version;

import com.heallin.api.bukkit.entitiy.CorePlayer;
import net.minecraft.server.v1_12_R1.EntityArmorStand;
import net.minecraft.server.v1_12_R1.Packet;
import org.bukkit.entity.Player;

public interface MinecraftBukkitApi {

    void sendAll(Packet<?> packet);

    void send(CorePlayer player, Packet<?> packet);

    void send(Player player , Packet<?> packet);

    Object usePacketPlayOutEntityDestroy(int... id);

    Object usePacketPlayOutEntityMetadata();

    EntityArmorStand getEntityArmorStand();

}
