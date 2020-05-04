package com.zeyilinxin.heallinlib;

import com.heallin.api.bukkit.CoreBukkit;
import com.heallin.api.forge.CoreForge;
import com.zeyilinxin.heallinlib.command.annotation.HCD;
import com.zeyilinxin.heallinlib.command.type.CommandType;
import com.zeyilinxin.heallinlib.plugin.HealLinPlugin;
import com.zeyilinxin.heallinlib.plugin.HealLinPluginManager;
import com.zeyilinxin.heallinlib.plugin.ServerPlugin;
import lombok.Getter;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import org.bukkit.command.CommandSender;

public class HealLinCatServer extends HealLinPlugin {


    private static HealLinCatServer healLinCatServer;
    private HealLinPluginManager healLinPluginManager;
    public CoreBukkit coreBukkit;
    @Getter
    public final int version = 5;

    @Override
    public void onStart() {
        this.info("开始加载....");
        healLinCatServer = this;
        coreBukkit = new CoreBukkit(this);
        this.healLinPluginManager = new HealLinPluginManager(this);
        this.iniCommand("heallinlib" , new LibCommand() , (src , s , c , args)->{

        } , "HealLinLib");
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

    class LibCommand{

        @HCD(
                cmd = {} ,
                length = 0 ,
                permission = "heallinlib.use" ,
                type = {CommandType.CorePlayer , CommandType.Sender} ,
                noPermission = "&c你没有权限来使用这个"
        )
        public void onInfo(CommandSender sender , String... args){
           // sender.sendMessage("/heallib ");
            sender.sendMessage("HealLinLib  V1.4122  作者:择忆霖心");
        }


    }
}
