package com.corelin.library.system;

import com.corelin.library.CoreLib;

import java.security.Permission;

/**
 * @author 择忆霖心
 * @简述 TODO
 * @时间 23:10
 * @版本 1.0
 */
public class CoreLibSecurityManager extends SecurityManager{

    private CoreLib lib;

    public CoreLibSecurityManager(CoreLib lib){
        this.lib = lib;
    }

    @Override
    public void checkPermission(Permission permission) {
        String permissionName = permission.getName() != null ? permission.getName() : "missing";
        if ("setSecurityManager".equals(permissionName)) {
            throw new SecurityException("无法替换CoreLib安全管理器");
        }
        return;
    }
}
