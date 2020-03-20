package com.zeyilinxin.heallinlib.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public interface CommandHelp {

    void run(CommandSender sender , String s , Command cmd , String[] args);
}
