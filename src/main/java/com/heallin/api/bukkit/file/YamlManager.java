package com.heallin.api.bukkit.file;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * @描述: TODO
 * @作者: 择忆霖心
 * @author: 择忆霖心
 * @时间: 2020/5/4 20:47
 * @版本: 1.0
 */
public class YamlManager {

    private File file;
    private YamlConfiguration yamlConfiguration;


    private YamlManager(File file){
        this.file = file;
        this.yamlConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    public YamlConfiguration getYaml(){
        return this.yamlConfiguration;
    }

    public void save(){
        try {
            this.yamlConfiguration.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static YamlManager getInstance(File file){
        return new YamlManager(file);
    }
}
