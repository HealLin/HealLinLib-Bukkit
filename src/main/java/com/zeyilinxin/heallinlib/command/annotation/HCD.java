package com.zeyilinxin.heallinlib.command.annotation;

import com.zeyilinxin.heallinlib.command.type.CommandType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface HCD {

    String[] cmd();

    int length();

    CommandType[] type();

    String description() default "";

    String trueType() default "";

    String[] permission();

    String noPermission() default "";
}
