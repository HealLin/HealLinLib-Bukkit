package com.corelin.library.api.event.manager;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 择忆霖心
 * @简述 TODO
 * @时间 2020/11/24 15:50
 * @版本 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface UseCoreLibEvent {
}
