package com.heallin.api.bukkit.listener;

import com.heallin.api.bukkit.Core;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class CoreServerListener implements Listener {

    private Core core;

    public CoreServerListener(Core core) {
        this.core =  core;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        this.core.getPlayerManager().onJoin(event.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        this.core.getPlayerManager().onQuit(event.getPlayer());
    }
}
