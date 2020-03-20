package com.zeyilinxin.heallinlib.storage;


import com.zeyilinxin.heallinlib.plugin.HealLinPlugin;

public class HealLinStorage {

    /**
     * jdbc:mysql
     * @param chars
     * @return
     */
    boolean isMySQL(char... chars){
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

    /**
     * jdbc:sqlite
     * @return
     */
    boolean isSQlite(char... chars){
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



    public static Storage getStorage(HealLinPlugin healLinPlugin , String url , String[] args){
        char[] urls = url.toCharArray();
        HealLinStorage linStorage = new HealLinStorage();
        if (linStorage.isMySQL(urls)){
            return new MysqlStorage(healLinPlugin , url , args);
        }else if (linStorage.isSQlite(urls)){
            return new SqliteStorage(healLinPlugin , url , args);
        }else {
            return new YamlStorage(healLinPlugin , url ,args);
        }
    }
}
