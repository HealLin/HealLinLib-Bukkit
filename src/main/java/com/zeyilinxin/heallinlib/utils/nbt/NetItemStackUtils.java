package com.zeyilinxin.heallinlib.utils.nbt;

import com.pixelmonmod.pixelmon.items.ItemPixelmonSprite;
import net.minecraft.nbt.NBTTagCompound;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class NetItemStackUtils {

    public static ItemStack netToOrg(net.minecraft.item.ItemStack itemStack){
        return CraftItemStack.asBukkitCopy(new net.minecraft.server.v1_12_R1.ItemStack(
                NetAndOrgNBTUtils.netToOrg(itemStack.writeToNBT(new NBTTagCompound()))));
    }
}
