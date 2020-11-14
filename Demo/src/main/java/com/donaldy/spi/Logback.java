package com.donaldy.spi;

/**
 * @author donald
 * @date 2020/11/14
 */
public class Logback implements Log {

    @Override
    public void log(String info) {

        System.out.println("Logback:" + info);
    }
}