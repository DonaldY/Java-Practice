package com.donaldy.concurrency.singleton;

import com.donaldy.concurrency.annoations.NoRecommendation;
import com.donaldy.concurrency.annoations.SafeThread;

/**
 * Created by DonaldY on 2018/7/16.
 */
@NoRecommendation
@SafeThread
public class SingletonExample5 {
    
    private SingletonExample5() {
        
    }
    
    private volatile static SingletonExample5 instance = null;
    
    public static SingletonExample5 getInstance() {
        if (instance == null) {
            synchronized (SingletonExample5.class) {
                instance = new SingletonExample5();
            }
        }
        
        return instance;
    }
}
