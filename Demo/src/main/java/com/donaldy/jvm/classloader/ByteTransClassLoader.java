package com.donaldy.jvm.classloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;

/**
 * @author donald
 * @date 2021/03/06
 */
public class ByteTransClassLoader extends ClassLoader {

    public static void main(String[] args) {
        ByteTransClassLoader byteTransClassLoader = new ByteTransClassLoader();
        Class clazz = null;
        try {
            clazz = byteTransClassLoader.loadClass("Hello");
            Object hello = clazz.newInstance();
            Method method = clazz.getMethod("hello");
            method.invoke(hello);
        } catch (ClassNotFoundException
                | IllegalAccessException
                | InvocationTargetException
                | InstantiationException
                | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private static final String HELLO_CLASS_PATH = "Hello.xlass";

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classBytes = getBytesInResource(HELLO_CLASS_PATH);
        if (null == classBytes) {
            throw new ClassNotFoundException("File Not Found");
        }

        classBytes = transferBytes(classBytes);

        return defineClass(name, classBytes, 0, classBytes.length);
    }

    private byte[] transferBytes(byte[] classBytes) {
        for (int i = 0; i < classBytes.length; i++) {
            classBytes[i] = (byte) (255 - classBytes[i]);
        }
        return classBytes;
    }

    private byte[] getBytesInResource(String path) {
        URL url = this.getClass().getClassLoader().getResource(path);
        if (url == null) {
            return null;
        }
        File file = new File(url.getPath());
        try {
            FileInputStream fis = new FileInputStream(url.getFile());
            byte[] res = new byte[fis.available()];
            fis.read(res);
            return res;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
