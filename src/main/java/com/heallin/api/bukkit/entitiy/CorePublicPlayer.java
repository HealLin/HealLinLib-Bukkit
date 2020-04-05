package com.heallin.api.bukkit.entitiy;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public interface CorePublicPlayer {

    void sendMessage(IChatBaseComponent... baseComponents);

    void sendMessage(String... messages);

    void addItemStack(ItemStack itemStack);

    int getEntityId();

    Player getPlayer();

    String getName();

    EntityPlayerMP getEntityPlayerMP();

    void sendAll(Object object);

    UUID getUUID();

    CoreInventory getInventory();

    boolean isOnline();

    void sendTitle(String title , String show);
}
