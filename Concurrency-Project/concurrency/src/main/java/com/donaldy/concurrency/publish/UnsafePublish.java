package com.donaldy.concurrency.publish;

import java.util.Arrays;

/**
 * Created by DonaldY on 2018/7/15.
 */
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
