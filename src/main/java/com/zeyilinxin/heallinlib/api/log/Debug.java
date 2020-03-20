package com.zeyilinxin.heallinlib.api.log;

import com.zeyilinxin.heallinlib.api.time.TimeApi;
import com.zeyilinxin.heallinlib.plugin.ServerPlugin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Debug {

    private static File file;

    Debug(ServerPlugin server , File directoryFile) {
        File file = new File(directoryFile + File.separator + "debug.txt");
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Debug.file = file;
    }

    public static void debug(String data){
        PrintWriter fileWriter = null;
        try {
            fileWriter = new PrintWriter(new FileWriter(file, true));
            fileWriter.println("[DEBUG]:" + TimeApi.getTime() + data);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
