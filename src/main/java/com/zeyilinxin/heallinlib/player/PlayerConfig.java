package com.zeyilinxin.heallinlib.player;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 玩家配置文件类
 */
final class PlayerConfig implements PlayerData {

    protected YamlConfiguration yamlConfiguration;
    protected File file;
    private BasicInformation basicInformation;
    private Player player;


    PlayerConfig(File dataFolder , Player player){
        this.file = new File(dataFolder + File.separator + player.getUniqueId().toString() + ".yml");
        if (!file.exists()){
            try {
                this.file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.yamlConfiguration = YamlConfiguration.loadConfiguration(file);
            this.basicInformation= new BasicInformation(this.yamlConfiguration);
            this.save();
            return;
        }
        this.yamlConfiguration = YamlConfiguration.loadConfiguration(this.file);
    }



    public BasicInformation getBasicInformation() {
        return basicInformation;
    }

    @Override
    public void save(){
        try {
            this.yamlConfiguration.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Player getPlayer() {
        return this.player;
    }


    @Override
    public YamlConfiguration getConfig() {
        return this.yamlConfiguration;
    }

    class BasicInformation{

        private YamlConfiguration yamlConfiguration;

        public BasicInformation(YamlConfiguration yamlConfiguration){
            this.yamlConfiguration = yamlConfiguration;
            this.yamlConfiguration.set("BasicInformation.Name" , player.getName());
            this.yamlConfiguration.set("BasicInformation.UUID" , player.getUniqueId().toString());
            this.yamlConfiguration.set("BasicInformation.Time" , System.currentTimeMillis());;
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
            this.yamlConfiguration.set("BasicInformation.EveryDay" ,df.format(new Date()));
        }

        public void updateEveryDay(){
            this.yamlConfiguration.set("BasicInformation.EveryDay" ,this.getNowDay());
            save();
        }

        public boolean isToDay(){
            int oldTime = this.yamlConfiguration.getInt("BasicInformation.EveryDay" , 0);
            long now = this.getNowDay();
            if (oldTime < now){
                return true;
            }
            return false;
        }

        public int getNowDay(){
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
            return Integer.valueOf(df.format(new Date()));

        }


    }





}
