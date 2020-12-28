package com.donaldy.lock.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author donald
 * @date 2020/12/28
 */
public class DontVolatile implements Runnable {
    volatile int a;
    AtomicInteger realA = new AtomicInteger();
    public static void main(String[] args) throws InterruptedException {
        Runnable r =  new DontVolatile();
        Thread thread1 = new Thread(r);
        Thread thread2 = new Thread(r);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(((DontVolatile) r).a);
        System.out.println(((DontVolatile) r).realA.get());
    }
    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            a++;
            realA.incrementAndGet();
        }
    }
}
