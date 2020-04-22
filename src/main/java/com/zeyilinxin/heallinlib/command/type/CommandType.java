package com.zeyilinxin.heallinlib.command.type;

public enum  CommandType {

    /**
     * 控制台
     */
    Sender ,
    /**
     * 玩家
     */
    Player ,
    /**
     * 玩家
     */
    CorePlayer;



    public static String getName(CommandType commandType){
        switch (commandType){
            case Player:
            case CorePlayer: {
                return "玩家";
            }
            default:{
                return "控制台";
            }
        }
    }
}
