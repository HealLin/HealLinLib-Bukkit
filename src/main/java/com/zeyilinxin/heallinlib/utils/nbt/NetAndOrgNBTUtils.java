package com.zeyilinxin.heallinlib.utils.nbt;

import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;

public final class NetAndOrgNBTUtils {

    public static NBTTagCompound orgToNet(net.minecraft.server.v1_12_R1.NBTTagCompound nbtTagCompound){
        NBTTagCompound nbt = new NBTTagCompound();
        try {
            nbt = JsonToNBT.getTagFromJson(nbtTagCompound.toString());
        } catch (NBTException e) {
            e.printStackTrace();
        }
        return (nbt == null) ? new NBTTagCompound() :  nbt;
    }

    public static net.minecraft.server.v1_12_R1.NBTTagCompound netToOrg(NBTTagCompound nbtTagCompound){
        net.minecraft.server.v1_12_R1.NBTTagCompound nbt = new net.minecraft.server.v1_12_R1.NBTTagCompound();
        try {
            nbt = OrgJsonToNBT.getTagFromJson(nbtTagCompound.toString());
        } catch (com.zeyilinxin.heallinlib.api.utils.NBTException e) {
            e.printStackTrace();
        }
        return (nbt == null) ? new net.minecraft.server.v1_12_R1.NBTTagCompound() :  nbt;

    }

}
