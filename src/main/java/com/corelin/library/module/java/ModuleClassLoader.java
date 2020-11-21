package com.corelin.library.module.java;

import com.corelin.library.module.CoreModuleInfo;
import lombok.Getter;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author 择忆霖心
 * @简述 TODO
 * @时间 2020/11/22 23:02
 * @版本 1.0
 */
public class ModuleClassLoader extends URLClassLoader {



    public ModuleClassLoader(File file, ClassLoader parent) throws MalformedURLException {
        super(new URL[] {file.toURI().toURL()}, parent);
    }



}
