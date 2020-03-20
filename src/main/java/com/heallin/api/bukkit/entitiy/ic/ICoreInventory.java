package com.heallin.api.bukkit.entitiy.ic;

import com.heallin.api.bukkit.entitiy.CoreInventory;
import com.heallin.api.bukkit.entitiy.CorePlayer;

public class ICoreInventory implements CoreInventory {

    private CorePlayer player;

    public ICoreInventory(CorePlayer player){
        this.player = player;
    }



    @Override
    public int getSize() {
        return 0;
    }
}
