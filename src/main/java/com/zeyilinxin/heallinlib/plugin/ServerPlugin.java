package com.zeyilinxin.heallinlib.plugin;

import com.zeyilinxin.heallinlib.HealLinCatServer;
import com.zeyilinxin.heallinlib.api.log.Log;
import com.zeyilinxin.heallinlib.config.HealLinConfig;
import com.zeyilinxin.heallinlib.forge.HealLinForge;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.util.Vector;

import java.util.Iterator;
import java.util.Optional;

public class ServerPlugin {

    protected HealLinCatServer server;
    protected HealLinForge healLinForge;
    protected CraftServer craftServer;
    protected MinecraftServer minecraftServer;
    protected SimplePluginManager pluginManager;
    protected HealLinConfig healLinConfig;
    protected Log log;
    private HealLinPlugin healLinPlugin;


    public ServerPlugin(HealLinPlugin javaPlugin, HealLinCatServer server) {
        this.healLinPlugin = javaPlugin;
        this.server = server;
        this.craftServer = (CraftServer) Bukkit.getServer();
        this.minecraftServer = this.craftServer.getServer();
        this.pluginManager = (SimplePluginManager) this.craftServer.getPluginManager();
        this.healLinConfig = new HealLinConfig(server , javaPlugin);
        log = new Log(this , healLinConfig.getDirectoryFile());
        if (javaPlugin.isForge()){
            this.healLinForge = new HealLinForge(server);
        }
        if (javaPlugin.isConfig()){
            healLinConfig.ini();
        }
    }

    public HealLinForge getHealLinForge() {
        return healLinForge;
    }

    public HealLinPlugin getHealLinPlugin() {
        return healLinPlugin;
    }

    public Player getPlayer(EntityPlayerMP playerMP){
        return Bukkit.getPlayer(playerMP.func_110124_au());
    }

    public Optional<Player> getPlayer(String playerName){
        Optional<Player> optionalPlayer = Optional.of(craftServer.getHandle().getPlayer(playerName).getBukkitEntity());
        return optionalPlayer;
    }

    public PlayerConnection getPlayerConnection(Player player){
        return this.minecraftServer.getPlayerList().a(player.getUniqueId()).playerConnection;
    }

    public EntityPlayer getEntityPlayer(Player player){
        return this.minecraftServer.getPlayerList().a(player.getUniqueId());
    }

    public void send(Player player , IChatBaseComponent chatBaseComponent) {
        this.sendPacket(player , new PacketPlayOutChat(chatBaseComponent , ChatMessageType.CHAT));
    }

    public void sendPacket(Player player , Packet packet){
        PlayerConnection playerConnection = this.getPlayerConnection(player);
        playerConnection.sendPacket(packet);
    }


    public MinecraftServer getMinecraftServer() {
        return minecraftServer;
    }

    public org.bukkit.entity.Entity getCursorTarget(Player p, double range){
        Block block;
        org.bukkit.entity.Entity target;
        Iterator<Entity> entities;
        Location loc = p.getEyeLocation();
        Vector vec = loc.getDirection().multiply(0.15);
        while((range-=0.1)>0 && ((block = loc.getWorld().getBlockAt(loc)).isLiquid() || block.isEmpty())){
            entities = loc.getWorld().getNearbyEntities(loc.add(vec), 0.001, 0.001, 0.001).iterator();
            while(entities.hasNext()){
                if((target = entities.next()) != p){
                    return target;
                }
            }
        }
        return null;
    }



    public HealLinConfig getHealLinConfig() {
        return healLinConfig;
    }

    public void sendActionBar(Player player , String message){
        ChatComponentText chatBaseComponents = new ChatComponentText(message);
        PacketPlayOutChat playOutChat = new PacketPlayOutChat(chatBaseComponents , ChatMessageType.GAME_INFO);
        this.sendPacket(player , playOutChat);
    }

    public CraftServer getCraftServer() {
        return craftServer;
    }
}
