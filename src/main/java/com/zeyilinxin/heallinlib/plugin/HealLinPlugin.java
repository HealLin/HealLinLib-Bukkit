package com.zeyilinxin.heallinlib.plugin;

import com.heallin.api.bukkit.CoreBukkit;
import com.zeyilinxin.heallinlib.HealLinCatServer;
import com.zeyilinxin.heallinlib.command.CommandHelp;
import com.zeyilinxin.heallinlib.command.HealLinCommand;
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

    public abstract String getPluginName();

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
        HealLinCatServer.instance().ini(this);
    }

    @Override
    public FileConfiguration getConfig() {
        return this.getPluginConfig().getConfiguration();
    }

    @Override
    public void reloadConfig() {
        this.getPluginConfig().reload();
    }

    @Override
    public void saveConfig() {
        this.getPluginConfig().save();
    }

    public HealLinConfig getPluginConfig(){
        return this.getServerPlugin().healLinConfig;
    }

    public <T> void iniCmd(String main , T cmd , CommandHelp commandHelp , String title){
        HealLinCommand healLinCommand = new HealLinCommand(this , cmd , commandHelp , title);
        this.getCommand(main).setExecutor(healLinCommand);
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
        this.serverPlugin.getHealLinConfig().saveResource(path , replace);
    }

    @Deprecated
    public void registerPlayerConfig(){
    }

}
