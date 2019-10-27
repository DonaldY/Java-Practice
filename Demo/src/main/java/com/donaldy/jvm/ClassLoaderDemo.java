package com.donaldy.jvm;

public class ClassLoaderDemo {

    public static void main(String[] args) throws ClassNotFoundException {

        // 加载核心类库的 Bootstrap ClassLoader
        System.out.println("核心类库加载器： " + ClassLoaderDemo.class.getClassLoader()
                .loadClass("java.lang.String").getClassLoader());

        // 加载拓展库的 Extension ClassLoader
        System.out.println("扩展类库加载器： " + ClassLoaderDemo.class.getClassLoader()
                .loadClass("com.sun.nio.zipfs.ZipCoder").getClassLoader());

        // 加载应用程序
        System.out.println("应用程序库加载器: " + ClassLoaderDemo.class.getClassLoader());

        // 双亲委派模型 Parents Delegation Model
        System.out.println("应用程序库加载器的父类： " + ClassLoaderDemo.class.getClassLoader().getParent());
        System.out.println("应用程序库加载器的父类的父类： " + ClassLoaderDemo.class.getClassLoader().getParent().getParent());
    }
}
