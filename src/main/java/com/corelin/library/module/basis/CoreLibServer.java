package com.corelin.library.module.basis;

import com.corelin.library.CoreLib;
import com.corelin.library.plugin.interfaces.CoreLibCommand;
import com.corelin.library.version.CoreLinVersion;
import org.bukkit.Bukkit;

/**
 * @author 择忆霖心
 * @简述 TODO
 * @时间 2020/11/23 0:29
 * @版本 1.0
 */
public class CoreLibServer {

    private static CoreLib lib;
    private CoreLib coreLib;

    public CoreLibServer(CoreLib lib){
        CoreLibServer.lib = lib;
        this.coreLib = lib;
    }

    public static void initialization(CoreLibCommand libCommand){
        if (lib.getBasis().libCommand != null){
            throw new RuntimeException("前置插件无法重复替换版本检测器");
        }else{
            //lib.getBasis().version = version;
            lib.getBasis().libCommand = libCommand;
        }
    }


    public boolean checking(){
        if (this.coreLib.getBasis().getVersion() == null){
            this.coreLib.info("缺少主要依赖，无法继续运行，将会关闭前置插件");
            Bukkit.getPluginManager().disablePlugin(this.coreLib);
            return false;
        }
        return true;
    }
}
