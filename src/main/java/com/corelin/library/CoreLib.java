package com.corelin.library;


import com.corelin.library.api.CoreLibApi;
import com.corelin.library.api.event.CoreLibEvent;
import com.corelin.library.api.event.manager.CoreLibEventManager;
import com.corelin.library.module.CoreLibModuleManager;
import com.corelin.library.module.basis.CoreLibServer;
import com.corelin.library.module.basis.CoreLinBasis;
import com.corelin.library.plugin.CoreLibPluginManager;
import com.corelin.library.plugin.CoreLinPlugin;
import lombok.Getter;


/**
 * @author 择忆霖心
 * @简述 TODO
 * @时间 2020/11/22 19:35
 * @版本 1.0
 */
public class CoreLib extends CoreLinPlugin {


    private CoreLibApi api;


    /**
     * 事件管理器
     */
    @Getter
    private CoreLibEventManager eventManager;

    /**
     * 用来管理和负责Bukkit的插件管理沟通
     */
    @Getter
    private CoreLibPluginManager pluginManager;

    @Getter
    private CoreLibModuleManager moduleManager;

    private CoreLibServer libServer;

    /**
     * 模块的基础服务
     */
    @Getter
    private CoreLinBasis basis;

    @Override
    public void onLoad() {
        this.info("初始化基础服务....");
        this.api = new CoreLibApi(this);
        this.libServer = new CoreLibServer(this);
        this.basis = new CoreLinBasis(this);
        this.eventManager = new CoreLibEventManager(this);
        this.info("开始加载模块....服务器版本-" + this.getBasis().getVersion().getVersion());
        setEnabled(true);
        this.moduleManager = new CoreLibModuleManager(this);
        this.moduleManager.load();
        //检查基础内容
       /* if (!this.libServer.checking()){
            return;
        }*/
     //   this.version = new CoreLinVersion(this.getServerPlugin());
        this.pluginManager = new CoreLibPluginManager(this);

    }



    @Override
    public void onStart() {

       /* this.ini();
        this.healLinPluginManager = new HealLinPluginManager(this);
        this.iniCommand("heallinlib" , new HealLinCatServer.LibCommand() , (src , s , c , args)->{

        } , "HealLinLib");
        this.info("插件管理器加载成功!");
        this.info("加载完成");*/
    }

    @Override
    public void onEnd() {
        this.moduleManager.onEnd();
    }



    @Override
    public String getPluginName() {
        return "择忆霖心前置插件-CoreLib";
    }

  /*  class LibCommand{

        @HCD(
                cmd = {} ,
                length = 0 ,
                permission = "heallinlib.use" ,
                type = {CommandType.CorePlayer , CommandType.Sender} ,
                noPermission = "&c你没有权限来使用这个"
        )
        public void onInfo(CommandSender sender , String... args){
            // sender.sendMessage("/heallib ");
            sender.sendMessage("HealLinLib  V1.42  作者:择忆霖心");
        }


    }*/

}
