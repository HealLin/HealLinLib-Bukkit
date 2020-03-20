package com.zeyilinxin.heallinlib.item.languageitemstack;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class EnItemStack extends LanguageItemStack{


    public EnItemStack(FileConfiguration config, String path) {
        super(config , path);
    }


    @Override
    public Material getType() {
        return Material.getMaterial(this.config.getString(this.path + "Id").toUpperCase());
    }

    @Override
    public short getDurability() {
        return (short) this.config.getInt(this.path + "Durability" , 0);
    }

    @Override
    public String getDisplayName() {
        return this.config.getString(this.path + "Name");
    }

    @Override
    public List<String> getLore() {
        return this.config.getStringList(this.path + "Lore");
    }
}
