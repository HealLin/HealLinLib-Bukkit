package com.zeyilinxin.heallinlib.item;

import com.zeyilinxin.heallinlib.enums.GetItemStackType;
import com.zeyilinxin.heallinlib.item.languageitemstack.EnItemStack;
import com.zeyilinxin.heallinlib.item.languageitemstack.LanguageItemStack;
import com.zeyilinxin.heallinlib.item.languageitemstack.ZhItemStack;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class GetItemStack {

    private FileConfiguration config;
    private String main;
    private String name;
    private LanguageItemStack languageItemStack;

    public GetItemStack(FileConfiguration config , String main , String name , GetItemStackType type){
        this.config = config;
        this.main = main;
        this.name = name;
        String path = this.getPath();
        switch (type){
            case ZH:{
                this.languageItemStack = new ZhItemStack(config , path);
                break;
            }
            default:{
                this.languageItemStack = new EnItemStack(config , path);
                break;

            }
        }
    }

    private String getPath(){
        return this.main + "." + this.name + ".";
    }

    public ItemStack getItemStack(RunLore runLore){
        ItemStack itemStack = new ItemStack(Material.AIR);
        itemStack.setType(this.languageItemStack.getType());
        itemStack.setDurability(this.languageItemStack.getDurability());
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(this.languageItemStack.getDisplayName());
        itemMeta.setLore(runLore.setLore(this.languageItemStack.getLore()));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public interface LanguageItem{

        Material getType() ;

        short getDurability();

        String getDisplayName();

        List<String> getLore();

    }



    public interface RunLore{

        default List<String> setLore(List<String> list){
            return list;
        }
    }


}
