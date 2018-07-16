package com.donaldy.concurrency.singleton;

import com.donaldy.concurrency.annoations.NoRecommendation;
import com.donaldy.concurrency.annoations.SafeThread;

/**
 * Created by DonaldY on 2018/7/16.
 */
@NoRecommendation
@SafeThread
public class SingletonExample3 {
    
    private SingletonExample3() {
        
    }
    
    private static SingletonExample3 instance = null;
    
    public static synchronized SingletonExample3 getInstance() {
        if (instance == null) {
            instance = new SingletonExample3();
        }
        
        return instance;
    }
}
