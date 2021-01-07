package com.donaldy.spi;

import java.sql.Driver;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author donald
 * @date 2021/01/07
 */
public class TestSpi {

    public static void main(String[] args) {

        ServiceLoader<Driver> loader = ServiceLoader.load(Driver.class);

        Iterator<Driver> iterator = loader.iterator();

        while (iterator.hasNext()) {

            Driver driver = iterator.next();

            System.out.println("driver: " + driver.getClass() + ", loader: "
                    + driver.getClass().getClassLoader());

            System.out.println("current thread contextLoader: "
                    + Thread.currentThread().getContextClassLoader());

            System.out.println("ServiceLoader loader: " + ServiceLoader.class.getClassLoader());
        }
    }
}
