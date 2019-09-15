package com.donaldy.lock.cas;

import java.util.concurrent.atomic.AtomicInteger;

public class CASTest {

    static final AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {

        Thread ths [] = new Thread[10000];
        for(int i=0;i<ths.length;i++) {
            Thread thread = new Thread(new Runnable() {

                public void run() {
                    for(int i=0;i<10000;i++) {
                        count.incrementAndGet();
                    }

                }
            });
            ths[i] = thread;
        }
        long begin = System.currentTimeMillis();

        for(int i = 0; i < ths.length; i++) {

            ths[i].start();
            ths[i].join();
        }

        System.out.println(count.get());// 100000000
        System.out.println(System.currentTimeMillis() - begin); // 1785
    }
}
