package com.donaldy.jvm.stack;

/**
 * @author donald
 * @date 2021/04/28
 */
public class StackDemo {

    public static long cnt = 0;

    public static void main(String[] args) {

        start();
    }

    public static void start() {
        System.out.println("第 " + (++cnt) + " 次调用方法");
        start();
    }
}
