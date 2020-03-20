package com.zeyilinxin.heallinlib;

import com.heallin.api.bukkit.CoreBukkit;
import com.heallin.api.forge.CoreForge;
import com.zeyilinxin.heallinlib.plugin.HealLinPlugin;
import com.zeyilinxin.heallinlib.plugin.HealLinPluginManager;
import com.zeyilinxin.heallinlib.plugin.ServerPlugin;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class HealLinCatServer extends HealLinPlugin {


    private static HealLinCatServer healLinCatServer;
    private HealLinPluginManager healLinPluginManager;
    public CoreBukkit coreBukkit;

    @Override
    public void onEnable() {
        healLinCatServer = this;
        coreBukkit = new CoreBukkit(this);
        this.healLinPluginManager = new HealLinPluginManager(this);
        this.info("加载完成");
    }

    @Override
    public void onDisable() {
        this.healLinPluginManager.onDisable();
    }

    public <T> T ini(HealLinPlugin javaPlugin) {
        ServerPlugin serverPlugin = new ServerPlugin(javaPlugin , this);
        javaPlugin.setServerPlugin(serverPlugin);
        this.healLinPluginManager.add(javaPlugin.getPluginName() , javaPlugin);
        return (T) serverPlugin;
    }

    public static HealLinCatServer instance(){
        return healLinCatServer;
    }

    @Override
    public String getPluginName() {
        return "择忆霖心前置插件";
    }
}
