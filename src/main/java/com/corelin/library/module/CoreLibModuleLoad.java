package com.corelin.library.module;

import com.corelin.library.CoreLib;
import com.corelin.library.module.annotation.CoreModule;
import com.corelin.library.module.java.ModuleClassLoader;
import lombok.Getter;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author 择忆霖心
 * @简述 TODO
 * @时间 2020/11/9 23:40
 * @版本 1.0
 */
public class CoreLibModuleLoad {

    private CoreLib coreLib;
    private CoreLibModuleManager manager;

    @Getter
    private Map<String , CoreModuleInfo> infoMap = new HashMap<>();
    private List<String> loadList = new ArrayList<>();

    public CoreLibModuleLoad(CoreLib coreLib, CoreLibModuleManager coreModuleManager){
        this.coreLib = coreLib;
        this.manager = coreModuleManager;
    }



    /**
     * 开始加载模块
     * @param ps
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public CoreModuleInfo loadModule(File ps) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, InvalidPluginException {
        JarFile jar = new JarFile(ps);
        ModuleClassLoader classLoader = new ModuleClassLoader(ps , this.getClass().getClassLoader());
        Enumeration<JarEntry> entry = jar.entries();
        while (entry.hasMoreElements()){
            JarEntry jarEntry = entry.nextElement();
            if (jarEntry.isDirectory() || (!jarEntry.getName().endsWith(".class"))){
                continue;
            }
            String name = jarEntry.getName().replace("/" , ".").replace(".class" , "");
            Class<?> clazz = classLoader.loadClass(name);
            if (clazz.isAnnotationPresent(CoreModule.class)){
                CoreModule module = clazz.getAnnotation(CoreModule.class);
                if ((!module.useVersion().isEmpty()) && !module.useVersion().equals(this.coreLib.getBasis().getVersion().getVersion()) && !module.useVersion().equals("*")){
                    this.coreLib.info("模块" + module.name() + "版本不符-使用版本" + module.useVersion());
                    return null;
                }
                if (module.main()){
                    this.coreLib.getBasis().setMain(true);
                }
                Class<? extends CoreLibModule> pluginClass;
                try {
                    pluginClass = clazz.asSubclass(CoreLibModule.class);
                } catch (ClassCastException ex) {
                    throw new InvalidPluginException("模块主类，没有继承CoreLibModule,无法启用", ex);
                }
                //已经new了但是插件没有模块没有启动
                CoreLibModule coreModule = pluginClass.newInstance();
                CoreModuleInfo coreModuleInfo = new CoreModuleInfo(
                        module, coreModule , ps , classLoader
                );
                return coreModuleInfo;
            }
            return null;
        }
        return null;
    }

    public Collection<CoreModuleInfo> values(){
        return this.infoMap.values();
    }


    /**
     * 加载前置模块
     * @param info
     * @return
     */
    public boolean loadPremise(CoreModuleInfo info){
        for (String n : info.getRely()){
            CoreModuleInfo coreModules = this.infoMap.get(n);
            if (coreModules != null){
                if (this.loadList.contains(coreModules.getName())){
                    continue;
                }
                register(coreModules);
                this.loadList.add(n);
                continue;
            }
            coreLib.info("模块:" + info.getName() + "(" + info.getFile().getName() + ")" + "缺少前置");
            for (String p : info.getRely()){
                coreLib.info("-" + p);
            }
            return false;
        }
        register(info);
        return true;
    }

    void register(CoreModuleInfo info){
        this.manager.addModule(info);
    }



}
