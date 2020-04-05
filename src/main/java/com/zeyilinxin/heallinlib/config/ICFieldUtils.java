package com.zeyilinxin.heallinlib.config;

import java.util.Optional;

/**
 * @author 择忆霖心
 * @version 1.0
 * @date 2020/3/29 18:13
 */
class ICFieldUtils implements FieldUtils{

    boolean has;
    private Object data;

    public ICFieldUtils(boolean has ,Object data){
        this.has = has;
        this.data = data;
    }

    @Override
    public boolean has() {
        return false;
    }

    @Override
    public <T> T get() throws Exception {
        if (this.has){
            return (T) data;
        }
        throw new Exception("无法强制访问");
    }
}
