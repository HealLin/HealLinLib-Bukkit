package com.corelin.library.api.event.manager;

import com.corelin.library.CoreLib;
import com.corelin.library.api.event.CoreLibEvent;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 择忆霖心
 * @简述 TODO
 * @时间 2020/11/24 15:48
 * @版本 1.0
 */
public class CoreLibEventManager {

    private CoreLib lib;
    private List<Object> list = new ArrayList<>();

    public CoreLibEventManager(CoreLib lib){
        this.lib = lib;

    }

    public void postEvent(CoreLibEvent event){
        for (Object o : list){
            for (Method method : o.getClass().getDeclaredMethods()){
                method.setAccessible(true);
                if (method.isAnnotationPresent(UseCoreLibEvent.class)){
                    try {
                        method.invoke(o , event);
                    } catch (IllegalAccessException e) {
                      //  e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        //e.printStackTrace();
                    }
                }
            }
        }
    }

    public void registerEvent(Object o){
        list.add(o);
    }
}
