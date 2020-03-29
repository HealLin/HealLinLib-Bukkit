package com.heallin.api.bukkit;

import com.heallin.api.bukkit.entitiy.CorePlayer;
import com.heallin.api.bukkit.listener.CoreServerListener;
import com.heallin.api.bukkit.manager.CoreEventManager;
import com.heallin.api.bukkit.manager.CorePlayerManager;
import com.heallin.api.bukkit.manager.EventManager;
import com.heallin.api.bukkit.version.MinecraftBukkit;
import com.heallin.api.bukkit.version.MinecraftBukkitApi;
import com.heallin.api.forge.CoreForge;
import com.zeyilinxin.heallinlib.HealLinCatServer;
import com.zeyilinxin.heallinlib.plugin.HealLinPlugin;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
import org.bukkit.entity.Player;

import java.util.UUID;

public final class Core implements CoreServer{


    static Core core;
    private HealLinCatServer healLinPlugin;
    @Getter
    private CraftServer craftServer;
    private CoreServerListener listener;
    @Getter
    private CorePlayerManager playerManager;
    private EventManager eventManager;
    private CoreForge coreForge;
    @Getter
    private boolean isForge = false;





    Core(HealLinCatServer healLinPlugin){
        this.healLinPlugin = healLinPlugin;
        this.eventManager = new EventManager(this);
        healLinPlugin.info("初始化事件管理器成功!");
        this.craftServer = (CraftServer) Bukkit.getServer();
        this.listener = new CoreServerListener(this);
        this.craftServer.getPluginManager().registerEvents(this.listener , this.healLinPlugin);
        this.playerManager = new CorePlayerManager(this);
        try {
            if (Class.forName("net.minecraftforge.fml.common.FMLCommonHandler") != null){
                healLinPlugin.info("检测到Forge，已经和Forge连接");
                this.isForge = true;
                this.coreForge = new CoreForge(this);
                return;
            }
            healLinPlugin.info("没有检测到Forge，定义为纯净服");
        } catch (ClassNotFoundException e) {
            coreForge = null;
            e.printStackTrace();
        }
    }


    @Override
    public <T> T registerEvent(T cls) {
        return null;
    }

    @Override
    public CorePlayer getCorePlayer(Player player) {
        return this.playerManager.getCorePlayer(player);
    }

    @Override
    public CorePlayer getCorePlayer(UUID uuid) {
        return this.playerManager.getCorePlayer(uuid);
    }

    @Override
    public CorePlayer getCorePlayer(String name) {
        return this.playerManager.getCorePlayer(name);
    }


    @Override
    public CorePlayer[] getOnlinePlayers() {
        return this.playerManager.getOnlinePlayers();
    }

    @Override
    public MinecraftBukkitApi getMinecraftBukkitApi() {
        return MinecraftBukkit.getApi();
    }

    @Override
    public CoreEventManager getEventManager() {
        return this.eventManager;
    }

    @Override
    public void addCommand(String name, Command command) {
        this.getCraftServer().getCommandMap().register(name , command);
    }
}
