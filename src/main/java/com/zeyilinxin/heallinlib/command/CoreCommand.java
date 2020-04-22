package com.zeyilinxin.heallinlib.command;

import com.heallin.api.bukkit.CoreBukkit;
import com.zeyilinxin.heallinlib.command.annotation.HCD;
import com.zeyilinxin.heallinlib.command.type.CommandType;
import com.zeyilinxin.heallinlib.plugin.HealLinPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 择忆霖心
 * @version 1.0
 * @date 2020/3/27 2:23
 */
public class CoreCommand<T> extends Command {

    private HealLinPlugin healLinPlugin;
    private Map<HCD, Method> cmdMap = new HashMap<>();
    private CommandHelp commandHelp;
    private String title;
    private T cmd;

    public CoreCommand(String name, HealLinPlugin healLinPlugin, T cmd, CommandHelp help, String title) {
        super(name);
        this.healLinPlugin = healLinPlugin;
        this.title = title;
        Method[] methods = cmd.getClass().getDeclaredMethods();
        try {
            for (Method m : methods){
                m.setAccessible(true);
                if (m.isAnnotationPresent(HCD.class)){
                    HCD hgc = m.getAnnotation(HCD.class);
                    this.cmdMap.put(hgc , m);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        this.commandHelp = help;
        this.cmd =  cmd;
    }

    public CoreCommand(String name, T cmd, CommandHelp help, String title) {
        super(name);
        this.title = title;
        Method[] methods = cmd.getClass().getDeclaredMethods();
        try {
            for (Method m : methods){
                m.setAccessible(true);
                if (m.isAnnotationPresent(HCD.class)){
                    HCD hgc = m.getAnnotation(HCD.class);
                    this.cmdMap.put(hgc , m);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        this.commandHelp = help;
        this.cmd =  cmd;
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        for (HCD h : this.cmdMap.keySet()){
            if (h.length() == args.length  || h.length() == -1){
                if (isArgs(h.cmd() , args)){
                    if (this.isType(h.type() , sender)){
                        String per = this.hasPermission(h.permission() , sender);
                        if (per.isEmpty()){
                            if (h.type().length == 1 && h.type()[0] == CommandType.CorePlayer){
                                try {
                                    Player player = (Player) sender;
                                    this.cmdMap.get(h).invoke(this.cmd , CoreBukkit.getCorePlayer(player), args);
                                    return true;
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                } catch (InvocationTargetException e) {
                                    e.printStackTrace();
                                }
                                return true;
                            }
                            try {
                                this.cmdMap.get(h).invoke(this.cmd , sender , args);
                                return true;
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }
                            return true;
                        }
                        sender.sendMessage(this.title + h.noPermission().replace("<permission>" , per));
                        return true;
                    }
                    //使用指令的类型不正确
                    sender.sendMessage(this.title + h.trueType());
                    return true;
                }
            }
        }
        //帮助
        this.commandHelp.run(sender , s , this , args);
        return true;
    }

    String hasPermission(String[] permission , CommandSender sender){
        if (permission.length == 0){
            return "";
        }
        for (String p : permission){
            if (!sender.hasPermission(p)){
                return p;
            }
        }
        return "";
    }



    boolean isArgs(String[] setCmds, String[] nowCmds){
        if (setCmds.length == 0){
            return true;
        }
        for (int i = 0 ; i < setCmds.length ; i++){
            if (!setCmds[i].equalsIgnoreCase(nowCmds[i])){
                return false;
            }
        }
        return true;
    }

    boolean isType(CommandType[] hgcTypes , CommandSender sender){
        for (CommandType t : hgcTypes){
            if (t == this.getType(sender) || (t == CommandType.CorePlayer && this.getType(sender) == CommandType.Player)){
                return true;
            }
        }
        return false;
    }

    CommandType getType(CommandSender sender){
        if (sender instanceof Player){
            return CommandType.Player;
        }else{
            return CommandType.Sender;
        }
    }

}
