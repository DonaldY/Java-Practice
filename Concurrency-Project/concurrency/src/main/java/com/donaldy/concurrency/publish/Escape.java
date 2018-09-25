package com.donaldy.concurrency.publish;

/**
 * Created by DonaldY on 2018/7/15.
 */

import com.donaldy.concurrency.annoations.NoRecommendation;
import com.donaldy.concurrency.annoations.UnsafeThread;

/**
 * 线程不安全写法。
 * 对象逸出：一种错误的发布。当一个对象还没有构造完成时，就使它被其他线程所见
 */
@UnsafeThread
@NoRecommendation
public class Escape {
    
    private int thisCanBeEscape = 0;
    
    public Escape() {
        new InnerClass();
    }
    
    private class InnerClass {
        
        public InnerClass() {
            System.out.println(Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }
}
