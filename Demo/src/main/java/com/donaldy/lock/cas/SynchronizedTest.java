package com.donaldy.lock.cas;

public class SynchronizedTest {

    static int count = 0;

    public static void main(String[] args) throws InterruptedException {

        final Object lock = new Object();

        Thread[] ths = new Thread[10000];
        for(int i=0;i<ths.length;i++) {
            Thread thread = new Thread(new Runnable() {

                public void run() {
                    for(int i=0;i<10000;i++) {
                        synchronized (lock) {
                            count ++;
                        }
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
        System.out.println(count);// 100000000
        System.out.println(System.currentTimeMillis() - begin); //4073
    }
}
