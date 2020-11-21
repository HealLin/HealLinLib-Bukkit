package com.corelin.library.plugin;

import com.corelin.library.CoreLib;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 择忆霖心
 * @简述 依赖于霖心前置插件的管理器
 * @时间 2020/11/22 20:26
 * @版本 1.0
 */
public class CoreLibPluginManager {

    private CoreLib lib;
    private Map<String , CoreLinPlugin> pluginMap = new HashMap<>();

    public CoreLibPluginManager(CoreLib lib) {
        this.lib = lib;
    }

    public void addPlugin(CoreLinPlugin plugin){
        this.pluginMap.put(plugin.getPluginName() , plugin);
    }

    public CoreLinPlugin getPluginName(String name){
        return this.pluginMap.get(name);
    }



    /**
     * 加载插件,只会返回继承于CoreLinPlugin的插件
     * @param file 文件路径
     * @return 返回的如果是Bukkit的普通插件那么为null
     */
    public CoreLinPlugin loadPlugin(File file){
        Plugin plugin;
        try {
            plugin = this.lib.getServer().getPluginManager().loadPlugin(file);
        } catch (InvalidPluginException e) {
            return null;
        } catch (InvalidDescriptionException e) {
            return null;
        }
        if (plugin instanceof CoreLinPlugin){
            return (CoreLinPlugin) plugin;
        }
        return null;
    }

    /**
     * 和正常的Bukkit卸载插件一样
     * @param plugin 直接传入Bukkit的插件主类即可
     */
    public void disablePlugin(Plugin plugin){

        this.lib.getServer().getPluginManager().disablePlugin(plugin);
    }
}
