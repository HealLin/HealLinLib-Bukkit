package com.zeyilinxin.heallinlib.item;

import com.zeyilinxin.heallinlib.enums.GetItemStackType;
import com.zeyilinxin.heallinlib.utils.nbt.NetItemStackUtils;
import net.minecraft.item.Item;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

public class ItemStackApi {

    public static ItemStack getItemStack(FileConfiguration config , String main , String name , GetItemStackType type , GetItemStack.RunLore runLore){
        try{
            return new GetItemStack(config , main , name , type).getItemStack(runLore);
        }catch (Exception e){
            return null;
        }
    }

    public static ItemStack netItemToItemStack(Item item){
        return NetItemStackUtils.netToOrg(new net.minecraft.item.ItemStack(item));
    }

   // public static ItemStack processItemStack(ItemStack itemStack){}
}
