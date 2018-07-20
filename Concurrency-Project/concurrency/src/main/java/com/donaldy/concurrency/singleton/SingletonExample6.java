package com.donaldy.concurrency.singleton;

/**
 * Created by DonaldY on 2018/7/16.
 */
public class SingletonExample6 {
    
    private SingletonExample6() {
        
    }
    private static SingletonExample6 instance;
    
    static {
        instance = new SingletonExample6();
    }
    
    
    public static SingletonExample6 getInstance() {
        return instance;
    }
}
