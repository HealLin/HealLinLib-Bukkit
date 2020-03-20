package com.heallin.api.bukkit.entitiy;

import com.heallin.api.forge.entitiy.CoreForgePlayerMP;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.v1_12_R1.EntityPlayer;
import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.Packet;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public interface CorePlayer extends CorePublicPlayer{

    void send(Packet<?> packet);





    CoreInventory getCoreInventory();

    void openInventory(CoreInventory inventory);

    void openInventory(Inventory inventory);

    EntityPlayer getEntityPlayer();

    CoreForgePlayerMP getCoreForgePlayerMP();








}
