package com.heallin.api.bukkit.event;

import com.heallin.api.bukkit.entitiy.CorePlayer;
import com.heallin.api.bukkit.manager.event.CoreEvent;
import lombok.Getter;

public class CorePlayerEvent extends CoreEvent {

    @Getter
    private CorePlayer player;

    public CorePlayerEvent(CorePlayer player){
        this.player = player;
    }

}
