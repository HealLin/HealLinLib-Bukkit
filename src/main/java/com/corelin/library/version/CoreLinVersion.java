package com.corelin.library.version;


import com.corelin.library.plugin.ServerPlugin;
import org.bukkit.Bukkit;

/**
 * @author 择忆霖心
 * @简述 TODO
 * @时间 2020/11/22 20:10
 * @版本 1.0
 */
public class CoreLinVersion {



    public CoreLinVersion() {

    }

    /**
     * 使用这个来设置关于服务器版本的
     * @return
     */
    public String getVersion(){
        String version = Bukkit.getVersion();
        return version.substring(version.indexOf("MC: ") + 4 , version.length() -1);
    }
}
