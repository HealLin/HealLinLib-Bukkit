package com.zeyilinxin.heallinlib.api;

import com.heallin.api.bukkit.CoreBukkit;
import com.zeyilinxin.heallinlib.command.CommandHelp;
import com.zeyilinxin.heallinlib.command.CoreCommand;

public class Api {

    public static <T> T registeredCommand(String name ,String pluginName ,  T cmd , CommandHelp help , String title){
        CoreCommand<T> coreCommand = new CoreCommand(name  , cmd , help , title);
        CoreBukkit.getServer().addCommand(pluginName, coreCommand);
        return cmd;
    }



}
