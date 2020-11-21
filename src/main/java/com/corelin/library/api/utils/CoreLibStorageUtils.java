package com.corelin.library.api.utils;

/**
 * @author 择忆霖心
 * @简述 TODO
 * @时间 2020/11/22 21:36
 * @版本 1.0
 */
public class CoreLibStorageUtils {

    /**
     * 这里用来确定连接的地址是否是mysql数据库
     * @param type
     * @return
     */
    public static boolean isMySQL(String type){
        return isMySQL(type.toCharArray());
    }


    /**
     * 这里用来确定连接的地址是否是sqlite数据库
     * @param type
     * @return
     */
    public static boolean isSQLite(String type){
        return isSQLite(type.toCharArray());
    }


    static boolean isMySQL(char... chars){
        if (chars[0] == 'j' &&
                chars[1] == 'd' &&
                chars[2] == 'b' &&
                chars[3] == 'c' &&
                chars[4] == ':' &&
                chars[5] == 'm' &&
                chars[6] == 'y' &&
                chars[7] == 's' &&
                chars[8] == 'q' &&
                chars[9] == 'l'){
            return true;
        }
        return false;
    }

    static boolean isSQLite(char... chars){
        if (chars[0] == 'j' &&
                chars[1] == 'd' &&
                chars[2] == 'b' &&
                chars[3] == 'c' &&
                chars[4] == ':' &&
                chars[5] == 's' &&
                chars[6] == 'q' &&
                chars[7] == 'l' &&
                chars[8] == 'i' &&
                chars[9] == 't' &&
                chars[10] == 'e'){
            return true;
        }
        return false;
    }

}
