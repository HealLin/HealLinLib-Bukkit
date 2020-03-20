package com.zeyilinxin.heallinlib.player;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public interface PlayerData {


    YamlConfiguration getConfig();

    void save();

    Player getPlayer();



}
