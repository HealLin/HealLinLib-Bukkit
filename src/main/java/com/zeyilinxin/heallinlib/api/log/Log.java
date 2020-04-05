package com.zeyilinxin.heallinlib.api.log;

import com.zeyilinxin.heallinlib.api.time.TimeApi;
import com.zeyilinxin.heallinlib.plugin.ServerPlugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Log {

    private JavaPlugin javaPlugin;
    private static File file;
    private Debug debug;

    public Log(ServerPlugin server, File directoryFile){
        directoryFile = new File(directoryFile + File.separator + "log");
        if (!directoryFile.exists()){
            directoryFile.mkdirs();
        }
        this.debug = new Debug(server , directoryFile);
        this.javaPlugin = server.getHealLinPlugin();
        this.file = new File(directoryFile + File.separator + "log.txt");
        if (!this.file.exists()){
            try {
                this.file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        server.getHealLinPlugin().info("日志位置:" + server.getHealLinPlugin().getPluginName() + "\\" + "log.txt");
    }

    public Debug getDebug() {
        return debug;
    }

    public static void info(String data){
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
