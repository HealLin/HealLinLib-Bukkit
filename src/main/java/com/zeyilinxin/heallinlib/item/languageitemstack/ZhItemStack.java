package com.zeyilinxin.heallinlib.item.languageitemstack;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class ZhItemStack  extends LanguageItemStack{



    public ZhItemStack(FileConfiguration config, String path) {
        super(config, path);
    }

    @Override
    public Material getType() {
        return null;
    }

    @Override
    public short getDurability() {
        return 0;
    }

    @Override
    public String getDisplayName() {
        return null;
    }

    @Override
    public List<String> getLore() {
        return null;
    }
}
