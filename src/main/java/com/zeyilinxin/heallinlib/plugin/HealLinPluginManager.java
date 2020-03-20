package com.zeyilinxin.heallinlib.plugin;

import com.zeyilinxin.heallinlib.HealLinCatServer;
import java.util.HashMap;
import java.util.Map;

public class HealLinPluginManager {


    private HealLinCatServer server;
    private Map<String , HealLinPlugin> pluginMap = new HashMap<>();


    public HealLinPluginManager(HealLinCatServer server){
        this.server = server;
    }

    public void add(String name , HealLinPlugin plugin){
        this.pluginMap.put(name , plugin);
    }

    public void onDisable(){
        this.pluginMap.clear();
    }


}
