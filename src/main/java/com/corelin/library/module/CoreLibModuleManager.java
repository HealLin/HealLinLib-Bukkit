package com.corelin.library.module;

import com.corelin.library.CoreLib;
import com.corelin.library.http.DownloadModule;
import com.corelin.library.module.annotation.CoreModule;
import lombok.SneakyThrows;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.PluginDescriptionFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author 择忆霖心
 * @简述 TODO
 * @时间 2020/11/22 19:51
 * @版本 1.0
 */
public class CoreLibModuleManager {

    private boolean isEnd = false;
    private CoreLib coreLib;
    private File directoryFile;
    private CoreLibModuleLoad moduleLoad;
    private List<CoreModuleInfo> enableModuleInfo = new ArrayList<>();

    public CoreLibModuleManager(CoreLib coreLib){
        this.coreLib = coreLib;

    }

    public void load(){
        this.directoryFile = new File(System.getProperty("user.dir") + File.separator + "plugins" + File.separator +  coreLib.getPluginName() );

        if (!this.directoryFile.exists()){
            this.directoryFile.mkdirs();
        }
        this.moduleLoad  = new CoreLibModuleLoad(coreLib , this);
        //开始预加载模块
        loadLibsFile();
        //检查是否有主要的模块
        checking();
        //开始加载
        this.coreLib.info("共计:" + this.enableModuleInfo.size() + "个模块需要加载");
        this.enableModuleInfo.forEach(this::loadAllModule);
    }

    private void checking() {
        if (!this.coreLib.getBasis().isHasUse()){
            this.coreLib.info("找不到主要模块，正在为您准备下载");
            DownloadModule downloadModule = new DownloadModule(this.coreLib);
           // downloadModule.downloadMainModule();
            //DownloadModule downloadModule = new DownloadModule();

        }
    }

   /* public List<CoreLibModuleFileInfo> getFileInfo() throws IOException, InvalidDescriptionException {
        List<CoreLibModuleFileInfo> fileInfoList = new ArrayList<>();
        for (File ps : Objects.requireNonNull(this.directoryFile.listFiles())){
            //如果是文件夹或者不是jar包就跳过
            if (ps.isDirectory() || !(ps.getName().endsWith(".jar"))){
                continue;
            }
            JarFile jar = new JarFile(ps);
            JarEntry entry = jar.getJarEntry("plugin.yml");
            InputStream stream = null;
            if (entry == null){
                continue;
            }
            stream = jar.getInputStream(entry);
            PluginDescriptionFile descriptionFile = new PluginDescriptionFile(stream);
            CoreLibModuleFileInfo fileInfo = new CoreLibModuleFileInfo(
                    descriptionFile.getName() , descriptionFile.getName() , descriptionFile.getMain()
            );
            fileInfoList.add(fileInfo);
        }
        return fileInfoList;
    }
*/
    public void addModule(CoreModuleInfo moduleInfo){
        this.enableModuleInfo.add(moduleInfo);
    }

    public void loadAllModule(CoreModuleInfo moduleInfo){
        this.coreLib.info("§a模块§6" + moduleInfo.getName() + "§a开始被加载!");
        moduleInfo.getModule().setCoreModuleInfo(this.coreLib , moduleInfo);
        moduleInfo.getModule().onLoad(moduleInfo);
    }

    /**
     * 关闭服务器会执行这个
     */
    public void onEnd(){
        this.isEnd = true;
        this.coreLib.info("正在关闭模块....");
        this.enableModuleInfo.forEach(this::disableModule);
        this.enableModuleInfo.clear();
    }

    /**
     * 将模块卸载
     * @param moduleInfo
     */
    public void disableModule(CoreModuleInfo moduleInfo){
        CoreLibModule module = moduleInfo.getModule();
        this.coreLib.info("模块:" + moduleInfo.getName() + "正在被卸载");
        if (module.enable){
            module.enable = false;
            //告诉模块他将被卸载
            module.onRmove();
            this.coreLib.info("模块:" + moduleInfo.getName() + "卸载完成");
            if (!this.isEnd){
                this.enableModuleInfo.remove(moduleInfo);
            }
            moduleInfo.setClassLoader(null);
        }else{
            this.coreLib.info("模块:" + moduleInfo.getName() + "卸载完成");
        }
    }

    @SneakyThrows
    public void loadLibsFile()  {
        for (File ps : Objects.requireNonNull(this.directoryFile.listFiles())){
            //如果是文件夹或者不是jar包就跳过
            if (ps.isDirectory() || !(ps.getName().endsWith(".jar"))){
                continue;
            }
            CoreModuleInfo moduleInfo = moduleLoad.loadModule(ps);
            if (moduleInfo != null){
                this.moduleLoad.getInfoMap().put(moduleInfo.getName() , moduleInfo);
            }
        }
        moduleLoad.values().forEach(moduleLoad::loadPremise);
    }


}
