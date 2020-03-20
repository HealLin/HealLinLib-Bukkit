package com.zeyilinxin.heallinlib.player;

import com.zeyilinxin.heallinlib.plugin.HealLinPlugin;
import org.bukkit.entity.Player;

import java.io.File;

public class PlayerConfigManager {

    private HealLinPlugin plugin;
    private File dataFolder;

    public PlayerConfigManager(HealLinPlugin plugin){
        this.plugin = plugin;
        dataFolder = new File(plugin.getServerPlugin().getHealLinConfig().getDirectoryFile() + File.separator + "玩家数据");
        if (!dataFolder.exists()){
            dataFolder.mkdirs();
        }
    }

    PlayerData getPlayerConfig(Player player){
        return new PlayerConfig(this.dataFolder , player);
    }

    public HealLinPlugin getPlugin() {
        return plugin;
    }
}
