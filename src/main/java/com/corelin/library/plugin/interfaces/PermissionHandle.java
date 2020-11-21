package com.corelin.library.plugin.interfaces;

/**
 * @描述: TODO
 * @作者: 择忆霖心
 * @author: 择忆霖心
 * @时间: 2020/10/28 1:08
 * @版本: 1.0
 */
public interface PermissionHandle {

    /**
     * 当没有权限的时候使用此方法
     * @param permission
     * @return
     */
    String run(String permission);
}
