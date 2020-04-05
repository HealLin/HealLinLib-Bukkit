package com.zeyilinxin.heallinlib.config;

import com.zeyilinxin.heallinlib.HealLinCatServer;
import com.zeyilinxin.heallinlib.config.annotation.HGC;
import com.zeyilinxin.heallinlib.plugin.HealLinPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

@Deprecated
public class HealLinConfig  {

    @Deprecated
    public static <T> void iniHGC(String path , FileConfiguration fileConfig , T config , String needTitle){
        if (!path.isEmpty()){
            path = path + ".";
        }
        Field[] fields = config.getClass().getDeclaredFields();
        try {
            config.getClass().getDeclaredField(needTitle).set(config , getConfig( path + needTitle , fileConfig , config.getClass().getDeclaredField(needTitle).getType() , false , "" , true));
            String title = (String) config.getClass().getDeclaredField(needTitle).get(config);
            for (Field f : fields){
                f.setAccessible(true);
                String fieldName = f.getName();
                if (fieldName.equalsIgnoreCase(needTitle)){
                    continue;
                }
                if (f.isAnnotationPresent(HGC.class)){
                    HGC hgc = f.getAnnotation(HGC.class);
                    f.set(config, getConfig(path  + fieldName, fileConfig , f.getType() , hgc.title() , title , true));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <T> void iniHGC(String path , FileConfiguration fileConfig , T config , String needTitle , boolean useTitle , boolean replace){
        if (!path.isEmpty()){
            path = path + ".";
        }
        Field[] fields = config.getClass().getDeclaredFields();
        try {
            String title = "";
            if (useTitle){
                config.getClass().getDeclaredField(needTitle).set(config , getConfig( path + needTitle , fileConfig , config.getClass().getDeclaredField(needTitle).getType() , false , "" , true));
                title = (String) config.getClass().getDeclaredField(needTitle).get(config);
            }
            for (Field f : fields){
                f.setAccessible(true);
                String fieldName = f.getName();
                if (fieldName.equalsIgnoreCase(needTitle)){
                    continue;
                }
                if (f.isAnnotationPresent(HGC.class)){
                    HGC hgc = f.getAnnotation(HGC.class);
                    f.set(config, getConfig(path  + fieldName, fileConfig , f.getType() , hgc.title() , title , replace));
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

    private static Object getConfig(String path , FileConfiguration config , Class type , boolean isTitle , String title , boolean replace){
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
