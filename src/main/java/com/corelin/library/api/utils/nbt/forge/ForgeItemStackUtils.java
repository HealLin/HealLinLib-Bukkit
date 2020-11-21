package com.corelin.library.api.utils.nbt.forge;

import net.minecraft.nbt.NBTTagCompound;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

/**
 * @author 择忆霖心
 * @简述 ItemStack类的使用
 * @时间 2020/11/22 21:51
 * @版本 1.0
 */
public class ForgeItemStackUtils {

    /**
     * 可以将Forge的ItemStack转换成Bukkit的ItemStack
     * @param itemStack Forge的ItemStack
     * @return Bukkit的ItemStack
     */
    public static ItemStack netToOrg(net.minecraft.item.ItemStack itemStack){
        return CraftItemStack.asBukkitCopy(new net.minecraft.server.v1_12_R1.ItemStack(
                ForgeNBTUtils.forgeToBukkitNBT(itemStack.writeToNBT(new NBTTagCompound()))));
    }
}
