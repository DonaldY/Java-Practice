package com.donaldy.concurrency.singleton;

/**
 * Created by DonaldY on 2018/7/16.
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
