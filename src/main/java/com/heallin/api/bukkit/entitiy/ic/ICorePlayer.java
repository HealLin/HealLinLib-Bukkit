package com.heallin.api.bukkit.entitiy.ic;

import com.heallin.api.bukkit.Core;
import com.heallin.api.bukkit.CoreBukkit;
import com.heallin.api.bukkit.entitiy.CoreInventory;
import com.heallin.api.bukkit.entitiy.CorePlayer;
import com.heallin.api.bukkit.version.MinecraftBukkitApi;
import com.heallin.api.forge.entitiy.CoreForgePlayerMP;
import com.heallin.api.forge.entitiy.ic.ICoreForgePlayerMP;
import io.netty.channel.Channel;
import lombok.Getter;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftInventoryPlayer;
import org.bukkit.craftbukkit.v1_12_R1.util.CraftChatMessage;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.Permission;

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
    private World world;


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
        this.world = this.entityPlayer.world;
        this.inventory = new ICoreInventory(this , (CraftInventoryPlayer)player.getInventory());
    }

    @Override
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

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public EntityPlayerMP getEntityPlayerMP() {
        return this.playerMP.getEntityPlayerMP();
    }

    @Override
    public org.bukkit.World getWorld() {
        return this.world.getWorld();
    }

    @Override
    public boolean hasPermission(String... args) {
        for (String p : args){
            if (!this.player.hasPermission(p)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean hasPermission(Permission... permissions) {
        for (Permission p : permissions){
            if (!this.player.hasPermission(p)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean noPermission(String... args) {
        for (String p : args){
            if (this.player.hasPermission(p)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean noPermission(Permission... permissions) {
        for (Permission p : permissions){
            if (this.player.hasPermission(p)){
                return false;
            }
        }
        return true;
    }

    @Override
    public NetworkManager getNetworkManager() {
        return this.playerConnection.networkManager;
    }

    @Override
    public Channel getChannel() {
        return this.getNetworkManager().channel;
    }

    @Override
    public boolean isOnline() {
        return this.player.isOnline();
    }

    @Override
    public void sendTitle(String title, String show) {
        this.player.sendTitle(title , show);
    }


    @Override
    public void sendAll(Object packet) {
        for (CorePlayer p : CoreBukkit.getOnlinePlayers()){
            p.send((Packet<?>) packet);
        }
    }

    @Override
    public CoreInventory getInventory() {
        return null;
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
        this.player.openInventory(inventory);
    }



    @Override
    public CoreForgePlayerMP getCoreForgePlayerMP() {
        return this.playerMP;
    }

    @Override
    public String locationToString() {
        return this.location.toString();
    }



}
