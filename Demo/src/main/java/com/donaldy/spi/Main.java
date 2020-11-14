package com.donaldy.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author donald
 * @date 2020/11/14
 */
public class Main {

    public static void main(String[] args) {

        ServiceLoader<Log> serviceLoader = ServiceLoader.load(Log.class);
        Iterator<Log> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {

            Log log = iterator.next();
            log.log("JDK SPI");
        }
    }
}
