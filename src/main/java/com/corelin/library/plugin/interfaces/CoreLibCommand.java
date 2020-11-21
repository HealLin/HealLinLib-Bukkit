package com.corelin.library.plugin.interfaces;

import org.bukkit.command.CommandExecutor;

/**
 * @author 择忆霖心
 * @简述 TODO
 * @时间 2020/11/23 0:54
 * @版本 1.0
 */
public interface CoreLibCommand {

    /**
     * 免除使用plugin.yml文件的注册
     * @param name 指令
     * @param cmd 指令类
     * @param handle 权限处理
     * @param help 指令帮助
     * @param title 提示
     * @param <T>
     * @return 返回指令类
     */
    <T> T addCommand(String name , T cmd , PermissionHandle handle , CommandHelp help , String title);

    /**
     * 需要在plugin.yml提前注册
     * @param name 指令
     * @param cmd 指令类
     * @param commandHelp 指令帮助
     * @param title 提示
     * @param <T>
     * @return 返回指令类
     */
    <T extends CommandExecutor> T setCommand(String name , T cmd , CommandHelp commandHelp , String title);
}
