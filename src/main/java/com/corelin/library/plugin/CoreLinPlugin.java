package com.corelin.library.plugin;

import com.corelin.library.api.CoreLibApi;
import com.corelin.library.api.config.CoreLinConfig;
import com.corelin.library.plugin.interfaces.CommandHelp;
import com.corelin.library.plugin.interfaces.CoreLibCommand;
import com.corelin.library.plugin.interfaces.PermissionHandle;
import com.corelin.library.plugin.log.Debug;
import com.corelin.library.plugin.log.Log;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author 择忆霖心
 * @简述 TODO
 * @时间 2020/11/22 19:36
 * @版本 1.0
 */
public abstract class CoreLinPlugin extends JavaPlugin {

    private ServerPlugin serverPlugin;
    private CoreLibApi api;

    private CoreLibCommand libCommand;


    @Override
    public void onDisable() {
        this.onEnd();
    }

    @Override
    public void onEnable() {
        this.onStart();
    }

    public abstract void onStart();

    public abstract void onEnd();


    public boolean isConfig(){
        return false;
    }

    public void setServerPlugin(ServerPlugin serverPlugin) {
        this.serverPlugin = serverPlugin;
    }

    public ServerPlugin getServerPlugin() {
        return serverPlugin;
    }

    public void info(Object o){
        Bukkit.getConsoleSender().sendMessage("§c[§a"+ this.getPluginName() + "§c]§b信息输出:§6" + o);

    }

    public void initialization(){
        this.api = CoreLibApi.getInstance();
        ServerPlugin serverPlugin = new ServerPlugin(this , api.getLib());
        this.setServerPlugin(serverPlugin);
        this.api.getLib().getPluginManager().addPlugin(this);
        this.libCommand = this.api.getLib().getBasis().getLibCommand();
        if (serverPlugin.ini() == false){
            throw new RuntimeException("初始化异常");
        }
    }

    @Override
    public FileConfiguration getConfig() {
        return this.getCoreLinConfig().getConfiguration();
    }

    @Override
    public void reloadConfig() {
        this.getCoreLinConfig().reload();
    }

    @Override
    public void saveConfig() {
        this.getCoreLinConfig().save();
    }

    public CoreLinConfig getCoreLinConfig(){
        return this.getServerPlugin().coreLinConfig;
    }

    public <T extends CommandExecutor> void iniCmd(String main , T cmd , CommandHelp commandHelp , String title){
        this.getCommand(main).setExecutor(this.libCommand.setCommand(main , cmd , commandHelp , title));
    }


    public <T> T iniCommand(String name , T cmd , PermissionHandle handle , CommandHelp help , String title){
        return this.libCommand.addCommand(name , cmd , handle , help , title);
    }

    public void registerEvents(Listener listener){
        Bukkit.getPluginManager().registerEvents(listener , this);
    }

    public Log getLog(){
        return this.serverPlugin.getLog();
    }

    public Debug getDeBug(){
        return this.getLog().getDebug();
    }


    protected void copyFile(String path , boolean replace){
        this.serverPlugin.getCoreLinConfig().saveResource(path , replace);
    }


    /**
     * 获取插件的名称
     * @return
     */
    public abstract String getPluginName();
}
