package com.corelin.library.module.basis;

import com.corelin.library.CoreLib;
import com.corelin.library.plugin.interfaces.CoreLibCommand;
import com.corelin.library.version.CoreLinVersion;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 择忆霖心
 * @简述 对外模块基础服务
 * @时间 2020/11/23 1:03
 * @版本 1.0
 */
public class CoreLinBasis {

    private CoreLib lib;

    /**
     * 获取关于服务端的版本相关内容
     */
    @Getter
    protected CoreLinVersion version;

    @Getter
    protected CoreLibCommand libCommand;

    @Getter
    private  boolean hasUse = false;

    public CoreLinBasis(CoreLib lib){
        this.lib = lib;
        this.version = new CoreLinVersion();
    }

    public void setMain(boolean main){
        if (this.hasUse){
            throw new RuntimeException("未知的异常");
        }else{
            this.hasUse = main;
        }
    }


}
