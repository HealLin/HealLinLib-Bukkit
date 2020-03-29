package com.zeyilinxin.heallinlib.api.utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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

    public static <T> T[] fromSmallToLarge(T[] cls , FSTL<T> sfl){
        for (T l : cls){
            for(int i = 0 ; i < cls.length - 1 ; i++){
                T temp;
                if (sfl.run(cls , i)){
                    temp = cls[i];
                    cls[i] = cls[i+1];
                    cls[i+1]= temp;
                }
            }
        }
        return cls;

    }


    List<String> filter(String data , List<String> list){
        ArrayList<String> arrayList = new ArrayList<>();
        for (String l : list){
            if (data.indexOf(l) != -1){
                arrayList.add(l);
            }
        }
        return arrayList;
    }


    public List<String> handler(String data , List<String> list){
        list = filter(data , list);
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0 ; i < list.size() ; i++){
            String l = list.get(i);
            if (i == 0){
                arrayList.add(data.substring(0 , data.indexOf(l)));
            }
            if (i == list.size() -1){
                arrayList.add(data.substring(data.lastIndexOf(l) + l.length()));
                continue;
            }
            arrayList.add(data.substring(data.indexOf(l) + l.length() , data.indexOf(list.get(i+1))));
        }
        return arrayList;
    }


    public static <T> List<T> handleReplace(List<T> list , RunReplace runReplace){
        ArrayList<T> arrayList = new ArrayList<>();
        for (T t : list){
           // arrayList.add(runReplace.run(t));
        }
        return arrayList;
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
