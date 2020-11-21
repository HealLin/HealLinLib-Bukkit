package com.corelin.library.plugin.log;

import com.corelin.library.api.time.TimeApi;
import com.corelin.library.plugin.CoreLinPlugin;
import com.corelin.library.plugin.ServerPlugin;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Log {

    private CoreLinPlugin plugin;
    private File file;
    private Debug debug;

    public Log(ServerPlugin server, File directoryFile){
        directoryFile = new File(directoryFile + File.separator + "log");
        if (!directoryFile.exists()){
            directoryFile.mkdirs();
        }
        this.debug = new Debug(server , directoryFile);
        this.plugin = server.getPlugin();
        file = new File(directoryFile + File.separator + "log.txt");
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        plugin.info("日志位置:" + plugin.getPluginName() + "\\" + "log.txt");
    }

    public Debug getDebug() {
        return debug;
    }

    public void info(String data){
        PrintWriter fileWriter = null;
        try {
            fileWriter = new PrintWriter(new FileWriter(file, true));
            fileWriter.println("[Info]:" + TimeApi.getTime("") + data);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void war(){

    }
}
