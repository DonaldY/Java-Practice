package com.donaldy.concurrency.singleton;

/**
 * Created by DonaldY on 2018/7/16.
 */
public class SingletonExample2 {
    
    private SingletonExample2() {
        
    }
    
    private static SingletonExample2 instance = new SingletonExample2();
    
    public static SingletonExample2 getInstance() {
        return instance;
    }
}
