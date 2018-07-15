package com.donaldy.concurrency.publish;

/**
 * Created by DonaldY on 2018/7/15.
 */
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
