package com.zeyilinxin.heallinlib.api.utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public class ArrayListUtils {

    public static <T> T[] asSFL(T[] cls , RunSFL sfl){
        for (T l : cls){
            for(int i = 0 ; i < cls.length - 1 ; i++){
                T temp;
                if (sfl.run(cls)){
                    temp = cls[i];
                    cls[i] = cls[i+1];
                    cls[i+1]= temp;
                }
            }
        }
        return cls;

    }

    public static <T> T[] listToArray(Collection<T> collection , Class cls){
        T[] object = (T[]) Array.newInstance(cls , collection.size());
        return collection.toArray(object);
    }


    public static <T> T[] getCS(Set<?> keys , T[] cls , Class<T> type){
        cls = (T[]) Array.newInstance(type, keys.size());
        return keys.toArray(cls);
    }



}
