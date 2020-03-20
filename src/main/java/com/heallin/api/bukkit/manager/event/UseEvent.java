package com.heallin.api.bukkit.manager.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UseEvent {

    CoreEventPriority priority() default CoreEventPriority.NORMAL;

}
