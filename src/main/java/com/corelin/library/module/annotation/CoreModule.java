package com.corelin.library.module.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 择忆霖心
 * @简述 TODO
 * @时间 2020/11/22 19:42
 * @版本 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CoreModule {

    String name();

    String description();

    String version();

    String[] rely() default {};

    String useVersion() default "";

    boolean main() default false;


}
