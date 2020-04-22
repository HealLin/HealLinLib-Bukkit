package com.zeyilinxin.heallinlib.config;

import com.zeyilinxin.heallinlib.HealLinCatServer;
import com.zeyilinxin.heallinlib.config.annotation.CoreConfig;
import com.zeyilinxin.heallinlib.config.annotation.HGC;
import com.zeyilinxin.heallinlib.plugin.HealLinPlugin;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * @描述: TODO
 * @作者: 择忆霖心
 * @author: 择忆霖心
 * @时间: 2020/4/5 23:48
 * @版本: 1.0
 */
public class CoreLinConfig {

    private HealLinCatServer healLinCatServer;
    private HealLinPlugin javaPlugin;
    private File directoryFile;
    private File file;
    private YamlConfiguration configuration;
    @Getter
    private HealLinConfig healLinConfig;


    public CoreLinConfig(HealLinCatServer healLinCatServer, HealLinPlugin javaPlugin) {
        this.healLinCatServer = healLinCatServer;
        this.javaPlugin = javaPlugin;

        this.directoryFile = new File(System.getProperty("user.dir") + File.separator + "plugins" + File.separator +  javaPlugin.getPluginName());
        if (!this.directoryFile.exists()){
            this.directoryFile.mkdirs();
        }
        this.file = new File(this.directoryFile , "config.yml");
    }

    public void ini(){
        if (!this.file.exists()){
            saveResource("config.yml" , false);
        }
        this.configuration = YamlConfiguration.loadConfiguration(this.file);
    }

    public void copyFile(String path , boolean replace){
        saveResource(path , replace);
    }


    public void save(){
        try {
            this.configuration.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reload(){
        this.configuration = YamlConfiguration.loadConfiguration(this.file);
    }

    public File getDirectoryFile() {
        return directoryFile;
    }

    public YamlConfiguration getConfiguration() {
        return configuration;
    }

    public void saveResource(String resourcePath, boolean replace) {
        if (resourcePath != null && !resourcePath.equals("")) {
            resourcePath = resourcePath.replace('\\', '/');
            InputStream in = this.getResource(resourcePath);
            if (in != null) {
                File outFile = new File(this.directoryFile, resourcePath);
                int lastIndex = resourcePath.lastIndexOf(47);
                File outDir = new File(this.directoryFile, resourcePath.substring(0, lastIndex >= 0 ? lastIndex : 0));
                if (!outDir.exists()) {
                    outDir.mkdirs();
                }

                try {
                    if (outFile.exists() && !replace) {
                    } else {
                        OutputStream out = new FileOutputStream(outFile);
                        byte[] buf = new byte[1024];

                        int len;
                        while((len = in.read(buf)) > 0) {
                            out.write(buf, 0, len);
                        }

                        out.close();
                        in.close();
                    }
                } catch (IOException var10) {
                }
            }
        } else {
        }
    }

    public InputStream getResource(String filename) {
        if (filename != null) {
            try {
                URL url = this.javaPlugin.getClass().getClassLoader().getResource(filename);
                if (url == null) {
                    return null;
                } else {
                    URLConnection connection = url.openConnection();
                    connection.setUseCaches(false);
                    return connection.getInputStream();
                }
            } catch (IOException var4) {
                return null;
            }
        }
        return null;
    }

    public static <T> void iniHGC(String path , FileConfiguration fileConfig , T config , String needTitle , boolean useTitle , boolean replace){
        if (!path.isEmpty()){
            path = path + ".";
        }
        Field[] fields = config.getClass().getDeclaredFields();
        try {
            String title = "";
            if (useTitle){
                config.getClass().getDeclaredField(needTitle).set(config , getConfig( path + needTitle , fileConfig , config.getClass().getDeclaredField(needTitle).getType() , "" ,false, "" , true));
                title = (String) config.getClass().getDeclaredField(needTitle).get(config);
            }
            for (Field f : fields){
                f.setAccessible(true);
                String fieldName = f.getName();
                if (fieldName.equalsIgnoreCase(needTitle)){
                    continue;
                }
                if (f.isAnnotationPresent(CoreConfig.class)){
                    CoreConfig coreConfig = f.getAnnotation(CoreConfig.class);
                    f.set(config, getConfig(path  + fieldName, fileConfig , f.getType() ,coreConfig.type() , coreConfig.title() , title , replace));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static <T> String getTitle(T config , String title){
        try {
            String data = (String) config.getClass().getDeclaredField(title).get(config);
            return data;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static Object getConfig(String path , FileConfiguration config , Class type ,  String customizeType , boolean isTitle , String title , boolean replace){
        if (customizeType.isEmpty()){
            try {
                if (type.equals(String.class)){
                    if (isTitle){
                        if (replace){
                            return title + config.getString(path , "").replace("&" , "§");
                        }
                        return title + config.getString(path , "");
                    }
                    if (replace){
                        return config.getString(path , "").replace("&" , "§");
                    }
                    return config.getString(path , "");
                }else if (type.equals(int.class)){
                    return config.getInt(path , 0);
                }else if (type.equals(boolean.class)){
                    return config.getBoolean(path , false);
                }else if (type.equals(List.class)){
                    ArrayList<String> arrayList = new ArrayList<>();
                    config.getStringList(path).forEach((l) ->{
                        arrayList.add(l.replace("&" , "§"));
                    });
                    return arrayList;
                }else{
                    return null;
                }
            }catch (NullPointerException e){
                System.out.println("问题字段" + path);
                System.out.println("问题类型" + type.getName());
                e.printStackTrace();
                return null;
            }
        }else{
            switch (customizeType.toLowerCase()){
                case "string":{
                    if (isTitle){
                        if (replace){
                            return title + config.getString(path , "").replace("&" , "§");
                        }
                        return title + config.getString(path , "");
                    }
                    if (replace){
                        return config.getString(path , "").replace("&" , "§");
                    }
                    return config.getString(path , "");
                }
                case "int":{
                    return config.getInt(path , 0);
                }
                case "boolean":{
                    return config.getBoolean(path , false);
                }
                case "maplist":{
                    return config.getMapList(path);
                }
                case "stringlist":{
                    return config.getStringList(path);
                }
                default:{
                    return null;
                }
            }
        }
    }

    public static <T> FieldUtils getClassField (T config , String name){
        try {
            Object o = config.getClass().getDeclaredField(name).get(config);
            return new ICFieldUtils(true , o);
        } catch (Exception e) {
            return new ICFieldUtils(false , "");
        }
    }

}
