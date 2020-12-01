package com.corelin.library.module;

import com.corelin.library.CoreLib;
import lombok.Getter;
import org.bukkit.Bukkit;

/**
 * @author 择忆霖心
 * @简述 TODO
 * @时间 2020/11/22 19:42
 * @版本 1.0
 */
public abstract class CoreLibModule {

    @Getter
    private CoreLib lib;
    private CoreModuleInfo info;

    public boolean enable;


    public void setCoreModuleInfo(CoreLib lib , CoreModuleInfo info){
        this.lib = lib;
        this.info = info;
        this.enable = true;
    }


    /**
     * 当模块被加载时使用这个
     * @param moduleInfo 模块信息
     */
    public abstract void onLoad(CoreModuleInfo moduleInfo);

    /**
     * 当模块被卸载时使用这个
     */
    public abstract void onRmove();


    public void info(Object o){
        Bukkit.getConsoleSender().sendMessage("§c[§f"+ this.info.getName() + "§c]§a模块信息输出:§6" + o);
    }

}
