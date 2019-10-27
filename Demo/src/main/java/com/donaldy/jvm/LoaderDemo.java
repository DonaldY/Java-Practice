package com.donaldy.jvm;

import java.net.URL;
import java.net.URLClassLoader;

public class LoaderDemo {

    public static void main(String[] args) throws Exception {
        URL classURL = new URL("file:/home/donald/Documents/Code/Java/Java-Practice/Demo/src/main/java/com.");

        while (true) {
            // 创建一个新的类加载器
            URLClassLoader loader = new URLClassLoader(new URL[] {classURL});

            Class clazz = loader.loadClass("HelloService");

            System.out.println("HelloService 所使用的类加载器： " + clazz.getClassLoader());
            Object newInstance = clazz.newInstance();
            Object value = clazz.getMethod("test").invoke(newInstance);
            System.out.println("调用getValue获得返回值为： " + value);

            Thread.sleep(3000L);
            System.out.println();

            // GC
            newInstance = null;
            loader = null;

            System.gc();
        }
    }
}
