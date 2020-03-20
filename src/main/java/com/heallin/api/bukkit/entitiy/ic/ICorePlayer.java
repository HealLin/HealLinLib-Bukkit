package com.heallin.api.bukkit.entitiy.ic;

import com.heallin.api.bukkit.version.MinecraftBukkitApi;
import com.heallin.api.bukkit.entitiy.CoreInventory;
import com.heallin.api.bukkit.Core;
import com.heallin.api.bukkit.entitiy.CorePlayer;
import com.heallin.api.forge.entitiy.CoreForgePlayerMP;
import com.heallin.api.forge.entitiy.ic.ICoreForgePlayerMP;
import lombok.Getter;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_12_R1.util.CraftChatMessage;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ICorePlayer extends ICoreEntity implements CorePlayer {

    @Getter
    private int entityId;
    private Core core;
    @Getter
    private EntityPlayer entityPlayer;
    private CraftPlayer player;
    private PlayerConnection playerConnection;
    @Getter
    private MinecraftBukkitApi minecraftBukkitApi;
    @Getter
    private Location location;
    private ICoreInventory inventory;
    @Getter
    private ICoreForgePlayerMP playerMP;


    public ICorePlayer(Core core, Player player) {
        super((CraftEntity) player);
        this.core = core;
        this.minecraftBukkitApi = this.core.getMinecraftBukkitApi();
        this.player = (CraftPlayer) player;
        this.entityPlayer = this.player.getHandle();
        this.entityId = player.getEntityId();
        this.playerConnection = this.entityPlayer.playerConnection;
        this.location = player.getLocation();
        if (this.core.isForge()){
            this.playerMP = new ICoreForgePlayerMP(this);
        }
    }

    public void send(Packet<?> packet){
        this.playerConnection.sendPacket(packet);
    }

    @Override
    public void sendMessage(IChatBaseComponent... baseComponents) {
        for (IChatBaseComponent i : baseComponents){
            this.sendMessage(i);
        }
    }

    @Override
    public void sendMessage(String... messages) {
        for (String m : messages){
            this.sendMessage(m);
        }
    }


    public void sendMessage(IChatBaseComponent baseComponent){
        if (this.playerConnection != null){
            this.send(new PacketPlayOutChat(baseComponent));
        }
    }

    public void sendMessage(String message){
        if (this.playerConnection != null) {
            for (IChatBaseComponent i : CraftChatMessage.fromString(message)){
                this.send(new PacketPlayOutChat(i));
            }
        }
    }

    @Override
    public void addItemStack(ItemStack itemStack) {

    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public EntityPlayerMP getEntityPlayerMP() {
        return this.playerMP.getEntityPlayerMP();
    }

    @Override
    public CoreInventory getCoreInventory() {
        return this.inventory;
    }

    @Override
    public void openInventory(CoreInventory inventory) {

    }

    @Override
    public void openInventory(Inventory inventory) {

    }

    @Override
    public CoreForgePlayerMP getCoreForgePlayerMP() {
        return this.playerMP;
    }


}
