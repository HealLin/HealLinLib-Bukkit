package com.corelin.library.api.config;

/**
 * @author 择忆霖心
 * @version 1.0
 * @date 2020/3/29 18:14
 */
public interface FieldUtils {

    boolean has();

    <T> T get() throws Exception;
}
