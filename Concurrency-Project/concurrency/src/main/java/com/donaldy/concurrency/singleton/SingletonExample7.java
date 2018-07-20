package com.donaldy.concurrency.singleton;

/**
 * Created by DonaldY on 2018/7/21.
 */

import com.donaldy.concurrency.annoations.Recommendation;
import com.donaldy.concurrency.annoations.SafeThread;

/**
 * 采用enum， 最安全
 */
@SafeThread
@Recommendation
public class SingletonExample7 {
    
    private SingletonExample7() {
        
    }
    
    public static SingletonExample7 getInstance() {
        return Singleton.INSTANCE.getInstance();
    }
    
    private enum Singleton {
        INSTANCE;
        
        private SingletonExample7 singleton;
        
        // JVM 保证这个方法绝对只调用一次
        Singleton() {
            singleton = new SingletonExample7();
        }
        
        public SingletonExample7 getInstance() {
            return singleton;
        }
    }
}
