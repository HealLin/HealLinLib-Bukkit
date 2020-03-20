package com.zeyilinxin.heallinlib.item.languageitemstack;

import com.zeyilinxin.heallinlib.item.GetItemStack;
import org.bukkit.configuration.file.FileConfiguration;

public abstract class LanguageItemStack implements GetItemStack.LanguageItem {

    protected FileConfiguration config;
    protected String path;


    public LanguageItemStack(FileConfiguration config, String path) {
        this.config = config;
        this.path = path;
    }
}
