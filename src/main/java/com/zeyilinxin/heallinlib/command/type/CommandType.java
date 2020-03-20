package com.zeyilinxin.heallinlib.command.type;

public enum  CommandType {

    Sender , Player;



    public static String getName(CommandType commandType){
        switch (commandType){
            case Player:{
                return "玩家";
            }
            default:{
                return "控制台";
            }
        }
    }
}
