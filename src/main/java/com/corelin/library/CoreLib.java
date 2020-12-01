package com.corelin.library;


import com.corelin.library.api.CoreLibApi;
import com.corelin.library.api.event.manager.CoreLibEventManager;

import com.corelin.library.module.CoreLibModuleManager;
import com.corelin.library.module.basis.CoreLibServer;
import com.corelin.library.module.basis.CoreLinBasis;
import com.corelin.library.plugin.CoreLibPluginManager;
import com.corelin.library.plugin.CoreLinPlugin;
import com.corelin.library.system.CoreLibSecurityManager;
import com.corelin.library.system.CoreLinSystem;
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

    @Getter
    private CoreLinSystem linSystem;

    public CoreLib(){
        this.info("开始加载-初始化基本服务....");
        this.linSystem = new CoreLinSystem(this);
        try{
            System.setSecurityManager(new CoreLibSecurityManager(this));
            this.info("注册安全器成功！");
        }catch (SecurityException e){
            this.info("CoreLib无法替换安全管理器，请检测是否有其他插件注册");
        }
    /*    PipedInputStream pipedInputStream = new PipedInputStream();
        PipedOutputStream pipedOutputStream = new PipedOutputStream();
        try {
            pipedOutputStream.connect(pipedInputStream);
        }
        catch(IOException e) {
            System.err.println("连接失败");
            System.exit(1);
        }
        PrintStream ps = new PrintStream(pipedOutputStream);
        System.setOut(ps);
        System.setErr(ps);
        ps.println("a");*/

    }

    @Override
    public void onLoad() {
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
