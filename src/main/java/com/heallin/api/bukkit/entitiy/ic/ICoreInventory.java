package com.heallin.api.bukkit.entitiy.ic;

import com.heallin.api.bukkit.entitiy.CoreInventory;
import com.heallin.api.bukkit.entitiy.CorePlayer;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftInventoryPlayer;
import org.bukkit.inventory.Inventory;

public class ICoreInventory implements CoreInventory {

    private CorePlayer player;
    private CraftInventoryPlayer inventoryPlayer;

    public ICoreInventory(CorePlayer player , CraftInventoryPlayer inventoryPlayer){
        this.player = player;
    }



    @Override
    public int getSize() {
        return 0;
    }
}
