package com.donaldy.concurrency.singleton;

import com.donaldy.concurrency.annoations.NoRecommendation;
import com.donaldy.concurrency.annoations.SafeThread;
import com.donaldy.concurrency.annoations.UnsafeThread;

/**
 * Created by DonaldY on 2018/7/16.
 */
@NoRecommendation
@UnsafeThread
public class SingletonExample4 {
    
    private SingletonExample4() {
        
    }
    
    private static SingletonExample4 instance = null;

    /**
     * 可能发生指令重排
     * @return
     */
    public static SingletonExample4 getInstance() {
        if (instance == null) {
            synchronized (SingletonExample4.class) {
                instance = new SingletonExample4();
            }
        }
        
        return instance;
    }
}
