package com.zeyilinxin.heallinlib.api.utils;

/**
 * @author 择忆霖心
 * @version 1.0
 * @date 2020/3/21 19:19
 */
public interface RunReplace {


    /**
     * 处理替换的数据
     * @param t 需要处理的数据
     * @param <T>
     * @return 返回处理好的数据
     */
    <T> T run(T t);
}