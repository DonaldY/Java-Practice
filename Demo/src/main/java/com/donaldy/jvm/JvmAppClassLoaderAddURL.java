package com.donaldy.jvm;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author donald
 * @date 2020/12/06
 */
public class JvmAppClassLoaderAddURL {

    public static void main(String[] args) {

        String appPath = "/home/donald";
        URLClassLoader urlClassLoader = (URLClassLoader) JvmAppClassLoaderAddURL.class.getClassLoader();
        try {
            Method addURL = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            addURL.setAccessible(true);
            URL url = new URL(appPath);
            addURL.invoke(urlClassLoader, url);
            Class.forName("jvm.Hello"); // 效果跟 class.forName("jvm.Hello").newInstance() 一样
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
