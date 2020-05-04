package com.heallin.api.bukkit.manager;

import com.heallin.api.bukkit.manager.event.CoreEvent;

public interface CoreEventManager {


    /**
     * 提交事件
     * @param event
     */
    void post(CoreEvent event);

    


}
