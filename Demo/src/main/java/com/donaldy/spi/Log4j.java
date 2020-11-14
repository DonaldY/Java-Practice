package com.donaldy.spi;

/**
 * @author donald
 * @date 2020/11/14
 */
public class Log4j implements Log {

    @Override
    public void log(String info) {

        System.out.println("Log4j:" + info);
    }
}
