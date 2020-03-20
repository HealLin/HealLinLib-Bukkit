package com.heallin.api.bukkit.entitiy;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface CorePublicPlayer {

    void sendMessage(IChatBaseComponent... baseComponents);

    void sendMessage(String... messages);

    void addItemStack(ItemStack itemStack);

    int getEntityId();

    Player getPlayer();

    String getName();

    EntityPlayerMP getEntityPlayerMP();
}
