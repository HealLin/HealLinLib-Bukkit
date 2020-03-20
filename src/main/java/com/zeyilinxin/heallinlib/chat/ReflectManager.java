package com.zeyilinxin.heallinlib.chat;

import java.lang.reflect.*;
import org.bukkit.*;

public class ReflectManager {
    public static Class<?> getClass(final ClassType type, final String className) {
        try {
            return Class.forName(type.getPack() + className);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Field getField(final ClassType type, final String className, final String fieldName) {
        try {
            return getClass(type, className).getDeclaredField(fieldName);
        }
        catch (NoSuchFieldException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Constructor<?> getConstructor(final ClassType type, final String className, final Class... params) {
        try {
            return getClass(type, className).getDeclaredConstructor((Class<?>[])params);
        }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Method getMethod(final ClassType type, final String className, final String methodName, final Class... params) {
        try {
            return getClass(type, className).getDeclaredMethod(methodName, params);
        }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object getObject(final ClassType type, final String className, final String methodName, final Class[] params, final Object obj, final Object... param_objs) {
        try {
            return getMethod(type, className, methodName, params).invoke(obj, param_objs);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public enum ClassType
    {
        NMS("net.minecraft.server."),
        OBC("org.bukkit.craftbukkit.");

        private String pack;

        private ClassType(final String prefix) {
            this.pack = prefix + Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3] + ".";
        }

        public String getPack() {
            return this.pack;
        }
    }
}
