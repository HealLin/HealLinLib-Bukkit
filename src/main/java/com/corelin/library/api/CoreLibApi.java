package com.corelin.library.api;

import com.corelin.library.CoreLib;
import com.corelin.library.api.event.CoreLibEvent;
import com.corelin.library.api.event.manager.CoreLibEventManager;
import com.corelin.library.plugin.CoreLibPluginManager;
import com.corelin.library.plugin.CoreLinPlugin;
import lombok.Getter;

/**
 * @author 择忆霖心
 * @简述 TODO
 * @时间 2020/11/22 20:22
 * @版本 1.0
 */
public class CoreLibApi {

    private static CoreLibApi api;

    @Getter
    private CoreLib lib;

    /**
     * 插件的管理器
     */
    private CoreLibPluginManager pluginManager;

    /**
     * 事件管理器
     */
    private CoreLibEventManager eventManager;

    public CoreLibApi(CoreLib lib) {
        CoreLibApi.api = this;
        this.lib = lib;
        this.pluginManager = lib.getPluginManager();
        this.eventManager = lib.getEventManager();
    }

    public void registerEvent(Object o){
        this.eventManager.registerEvent(o);
    }

    public void postEvent(CoreLibEvent event){
        this.eventManager.postEvent(event);
    }


    /**
     * 通过插件名称查找插件
     * @param pluginName 插件对外的名称
     * @return 返回的可能为空
     */
    public CoreLinPlugin getPluginForName(String pluginName){
        return this.pluginManager.getPluginName(pluginName);
    }




    public static CoreLibApi getInstance(){
        return CoreLibApi.api;
    }

}
