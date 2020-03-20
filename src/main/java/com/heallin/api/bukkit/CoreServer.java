package com.heallin.api.bukkit;

import com.heallin.api.bukkit.entitiy.CorePlayer;
import com.heallin.api.bukkit.manager.CoreEventManager;
import com.heallin.api.bukkit.version.MinecraftBukkitApi;
import org.bukkit.entity.Player;

import java.util.UUID;

public interface CoreServer {

    <T> T registerEvent(T cls);

    CorePlayer getCorePlayer(Player player);

    CorePlayer getCorePlayer(UUID uuid);

    CorePlayer getCorePlayer(String name);

    CorePlayer[] getOnlinePlayers();

    MinecraftBukkitApi getMinecraftBukkitApi();

    CoreEventManager getEventManager();

}
