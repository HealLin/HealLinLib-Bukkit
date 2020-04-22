package com.heallin.api.bukkit;

import com.heallin.api.bukkit.entitiy.CorePlayer;
import com.heallin.api.bukkit.manager.CoreEventManager;
import com.heallin.api.bukkit.version.MinecraftBukkitApi;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import java.util.UUID;

public interface CoreServer {

    /**
     * 注册事件
     * @param cls
     * @param <T>
     * @return
     */
    <T> T registerEvent(T cls);

    /**
     * 获取 CorePlayer
     * @param player Bukkit的Player
     * @return CorePlayer
     */
    CorePlayer getCorePlayer(Player player);

    /**
     * 获取CorePlayer
     * @param uuid
     * @return
     */
    CorePlayer getCorePlayer(UUID uuid);

    CorePlayer getCorePlayer(String name);

    /**
     * 获取所有在线玩家
     * @return
     */
    CorePlayer[] getOnlinePlayers();

    /**
     * 获取NMS
     * @return
     */
    MinecraftBukkitApi getMinecraftBukkitApi();

    /**
     * 获取这个世界的所有玩家
     * @param worldName 世界名城
     * @return 在这个世界所有玩家
     */
    CorePlayer[] getWorldPlayers(String worldName);

    /**
     * 获取事件管理器
     * @return
     */
    CoreEventManager getEventManager();

    void addCommand(String name , Command command);

}
