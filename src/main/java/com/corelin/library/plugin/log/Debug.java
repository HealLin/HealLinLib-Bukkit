package com.corelin.library.plugin.log;

import com.corelin.library.api.time.TimeApi;
import com.corelin.library.plugin.ServerPlugin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Debug {

    private  File file;

    Debug(ServerPlugin server , File directoryFile) {
        File file = new File(directoryFile + File.separator + "debug.txt");
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.file = file;
    }

    public void debug(String data){
        PrintWriter fileWriter = null;
        try {
            fileWriter = new PrintWriter(new FileWriter(file, true));
            fileWriter.println("[DEBUG]:" + TimeApi.getTime("") + data);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
