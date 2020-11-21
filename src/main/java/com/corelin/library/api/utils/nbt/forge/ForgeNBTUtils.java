package com.corelin.library.api.utils.nbt.forge;

import com.corelin.library.api.utils.nbt.JsonToBukkitNBT;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author 择忆霖心
 * @简述 TODO
 * @时间 2020/11/22 21:53
 * @版本 1.0
 */
public class ForgeNBTUtils {

    /**
     * 将Bukkit的NBT转换成Forge的NBT
     * @param nbtTagCompound
     * @return
     */
    public static NBTTagCompound bukkitNBTToForge(net.minecraft.server.v1_12_R1.NBTTagCompound nbtTagCompound){
        NBTTagCompound nbt = new NBTTagCompound();
        try {
            nbt = JsonToNBT.getTagFromJson(nbtTagCompound.toString());
        } catch (NBTException e) {
            e.printStackTrace();
        }
        return (nbt == null) ? new NBTTagCompound() :  nbt;
    }

    /**
     * 将Forge的NBT转换成Bukkit的NBT
     * @param nbtTagCompound
     * @return
     */
    public static net.minecraft.server.v1_12_R1.NBTTagCompound forgeToBukkitNBT(NBTTagCompound nbtTagCompound){
        net.minecraft.server.v1_12_R1.NBTTagCompound nbt = null;
        try {
            nbt = JsonToBukkitNBT.getTagFromJson(nbtTagCompound.toString());
        } catch (com.corelin.library.exception.NBTException e) {
            e.printStackTrace();
        }
        return (nbt == null) ? new net.minecraft.server.v1_12_R1.NBTTagCompound() :  nbt;

    }

}
