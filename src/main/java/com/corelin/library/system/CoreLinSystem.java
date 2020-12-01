package com.corelin.library.system;

import com.corelin.library.CoreLib;
import lombok.Getter;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * @author 择忆霖心
 * @简述 TODO
 * @时间 23:26
 * @版本 1.0
 */
public class CoreLinSystem {

    private CoreLib lib;
    @Getter
    private PrintStream oldStream;

    public CoreLinSystem(CoreLib lib){
        this.lib = lib;
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream(1024);
        PrintStream newPrintStream = new PrintStream(arrayOutputStream){

            @Override
            public void print(boolean b) {
                super.print(b);
                oldStream.print(b);
            }

            @Override
            public void print(char c) {
                super.print(c);
                oldStream.print(c);
            }

            @Override
            public void print(int i) {
                super.print(i);
                oldStream.print(i);
            }

            @Override
            public void print(long l) {
                super.print(l);
                oldStream.print(l);
            }

            @Override
            public void print(float f) {
                super.print(f);
                oldStream.print(f);
            }

            @Override
            public void print(double d) {
                super.print(d);
                oldStream.print(d);
            }

            @Override
            public void print(char[] s) {
                super.print(s);
                oldStream.print(s);
            }

            @Override
            public void print(String s) {
                super.print(s);
                oldStream.print(s);
            }

            @Override
            public void print(Object obj) {
                super.print(obj);
                oldStream.print(obj);
            }

            @Override
            public void println() {
                super.println();
                oldStream.println();
            }

            @Override
            public void println(boolean x) {
                super.println(x);
                oldStream.println(x);
            }

            @Override
            public void println(char x) {
                super.println(x);
                oldStream.println(x);
            }

            @Override
            public void println(int x) {
                super.println(x);
                oldStream.println(x);
            }

            @Override
            public void println(long x) {
                super.println(x);
                oldStream.println(x);
            }

            @Override
            public void println(float x) {
                super.println(x);
                oldStream.println(x);
            }

            @Override
            public void println(double x) {
                super.println(x);
                oldStream.println(x);
            }

            @Override
            public void println(char[] x) {
                super.println(x);
                oldStream.println(x);
            }

            @Override
            public void println(String x) {
                super.println(x);
                oldStream.println(x);
            }

            @Override
            public void println(Object x) {
                super.println(x);
                oldStream.println(x);
            }
        };
        this.oldStream = System.out;
        System.setOut(newPrintStream);
        System.setErr(newPrintStream);
    }

}
