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
    
    // 1. memory = allocate() 分配对象的内存空间
    // 2. instance = memory 设置instance指向刚分配的内存
    // 3. ctorInstance() 初始化对象
    public static SingletonExample4 getInstance() {
        if (instance == null) {
            synchronized (SingletonExample4.class) {
                instance = new SingletonExample4();
            }
        }
        
        return instance;
    }
}
