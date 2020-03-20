package com.heallin.api.forge.entitiy.ic;

import com.heallin.api.bukkit.entitiy.CoreInventory;
import com.heallin.api.bukkit.entitiy.ic.ICorePlayer;
import com.heallin.api.forge.entitiy.CoreForgePlayerMP;
import lombok.Getter;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.v1_12_R1.EntityPlayer;
import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.Packet;
import net.minecraftforge.fml.common.FMLCommonHandler;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ICoreForgePlayerMP implements CoreForgePlayerMP {

    private EntityPlayerMP playerMP = null;
    private ICorePlayer corePlayer;

    public ICoreForgePlayerMP(ICorePlayer corePlayer){
        this.corePlayer = corePlayer;
        this.playerMP = FMLCommonHandler.instance().getMinecraftServerInstance().func_184103_al().func_177451_a(corePlayer.getUUID());
    }

    /*@Override
    public void send(Packet<?> packet) {
       // this.playerMP
    }*/

    @Override
    public void sendMessage(IChatBaseComponent... baseComponents) {

    }

    @Override
    public void sendMessage(String... messages) {

    }

    @Override
    public void addItemStack(ItemStack itemStack) {

    }

    @Override
    public int getEntityId() {
        return 0;
    }


    @Override
    public Player getPlayer() {
        return this.corePlayer.getPlayer();
    }

    @Override
    public String getName() {
        return this.playerMP.func_70005_c_();
    }

    @Override
    public EntityPlayerMP getEntityPlayerMP() {
        return this.playerMP;
    }

    /*@Override
    public CoreInventory getCoreInventory() {
        return null;
    }

    @Override
    public void openInventory(CoreInventory inventory) {

    }

    @Override
    public void openInventory(Inventory inventory) {

    }

    @Override
    public EntityPlayer getEntityPlayer() {
        return null;
    }

    @Override
    public CoreForgePlayerMP getCoreForgePlayerMP() {
        return this;
    }*/

}
