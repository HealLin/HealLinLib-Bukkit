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
    public void onStart() {
        this.info("开始加载....");
        healLinCatServer = this;
        coreBukkit = new CoreBukkit(this);
        this.healLinPluginManager = new HealLinPluginManager(this);
        this.info("插件管理器加载成功!");
        this.info("加载完成");
    }

    @Override
    public void onEnd() {
        this.healLinPluginManager.onDisable();
    }


    public void ini(ServerPlugin serverPlugin) {
        this.healLinPluginManager.add(serverPlugin.getHealLinPlugin().getPluginName() , serverPlugin.getHealLinPlugin());
    }

    public static HealLinCatServer instance(){
        return healLinCatServer;
    }

    @Override
    public String getPluginName() {
        return "择忆霖心前置插件";
    }
}
