package com.zeyilinxin.heallinlib.plugin;

import com.heallin.api.bukkit.CoreBukkit;
import com.zeyilinxin.heallinlib.HealLinCatServer;
import com.zeyilinxin.heallinlib.command.CommandHelp;
import com.zeyilinxin.heallinlib.command.CoreCommand;
import com.zeyilinxin.heallinlib.command.HealLinCommand;
import com.zeyilinxin.heallinlib.config.CoreLinConfig;
import com.zeyilinxin.heallinlib.config.HealLinConfig;
import com.zeyilinxin.heallinlib.storage.HealLinStorage;
import com.zeyilinxin.heallinlib.storage.Storage;
import net.minecraft.entity.player.EntityPlayerMP;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class HealLinPlugin extends JavaPlugin {

    private ServerPlugin serverPlugin;

    public HealLinPlugin(){

    }

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

    @Deprecated
    public boolean isForge(){
        return false;
    }

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

    public void ini(){
        ServerPlugin serverPlugin = new ServerPlugin(this , HealLinCatServer.instance());
        this.setServerPlugin(serverPlugin);
        HealLinCatServer.instance().ini(serverPlugin);
        if (serverPlugin.ini() == false){
            throw new NullPointerException("初始化异常");
        }
        return ;
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

    @Deprecated
    public HealLinConfig getPluginConfig(){
        return this.getServerPlugin().healLinConfig;
    }

    public CoreLinConfig getCoreLinConfig(){
        return this.getServerPlugin().coreLinConfig;
    }

    public <T> void iniCmd(String main , T cmd , CommandHelp commandHelp , String title){
        HealLinCommand healLinCommand = new HealLinCommand(this , cmd , commandHelp , title);
        this.getCommand(main).setExecutor(healLinCommand);
    }

    public <T> T iniCommand(String name , T cmd , CommandHelp help , String title){
        CoreCommand<T> coreCommand = new CoreCommand(name , this , cmd , help , title);
        CoreBukkit.getServer().addCommand(this.getName() , coreCommand);
        return cmd;
    }

    @Deprecated
    public EntityPlayerMP getEntityPlayerMP(Player player){
        return CoreBukkit.getCorePlayer(player).getEntityPlayerMP();
    }

    public void registerEvents(Listener listener){
        Bukkit.getPluginManager().registerEvents(listener , this);
    }

    public Storage getStorage(String url , String[] args){
        return HealLinStorage.getStorage(this , url , args);
    }

    protected void copyFile(String path , boolean replace){
        this.serverPlugin.getCoreLinConfig().saveResource(path , replace);
    }

    @Deprecated
    public void registerPlayerConfig(){
    }

    public abstract String getPluginName();

}
