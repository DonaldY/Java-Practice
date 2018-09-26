package com.donaldy.concurrency.singleton;

/**
 * Created by DonaldY on 2018/7/16.
 */

/**
 * 安全发布对象：
 *
 * 1. 在静态初始化函数中初始化一个对象引用
 * 2. 将对想的引用保存到volatile类型域或者AtomicReference对象中
 * 3. 将对象的引用保存到某个正确构造对象的final类型域中
 * 4. 将对象的引用保存到一个由锁保护的域中
 */
public class SingletonExample1 {
    
    private SingletonExample1() {
        
    }
    
    private static SingletonExample1 instance = null;
    
    public static SingletonExample1 getInstance() {
        if (instance == null) {
            instance = new SingletonExample1();
        }
        return instance;
    }
}
