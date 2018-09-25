package com.donaldy.concurrency.publish;

import com.donaldy.concurrency.annoations.NoRecommendation;
import com.donaldy.concurrency.annoations.UnsafeThread;

import java.util.Arrays;

/**
 * Created by DonaldY on 2018/7/15.
 */

/**
 * 线程不安全，对象引用不安全。
 * 使一个对象能够被当前范围之外的代码所使用
 */
@UnsafeThread
@NoRecommendation
public class UnsafePublish {
    
    private String [] states = {"a", "b", "c"};
    
    public String [] getStates() {
        return this.states;
    }

    public static void main(String[] args) {
        UnsafePublish unsafePublish = new UnsafePublish();

        System.out.println(Arrays.toString(unsafePublish.getStates()));
        
        unsafePublish.getStates()[0] = "D";
        System.out.println(Arrays.toString(unsafePublish.getStates()));
    }
}
