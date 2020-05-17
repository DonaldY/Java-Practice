package com.donaldy.jvm.younggc;

/**
 * @author donald
 * @date 20205/18
 */
public class Demo {
    public static void main(String[] args) {
        byte[] array1 = new byte[1024*1024];
        array1 = new byte[1024*1024];
        array1 = new byte[1024*1024];
        array1 = null;

        byte[] array2 = new byte[2 * 1024 * 1024];
    }
}
