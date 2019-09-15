package com.donaldy.lock.aqs;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest extends Thread {

    private static ReentrantLock lock = new ReentrantLock();
    private String name;
    private static int i = 0;

    private ReentrantLockTest(String name) {
        this.name = name;
    }

    @Override
    public void run() {

        System.out.println(name + " 开始增加");

        for (int j = 0; j < 100; j++) {

            lock.lock();

            try {

                ++i;
            } finally {
                lock.unlock();
            }
        }

        System.out.println(name + " 结束增加");
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockTest test1 = new ReentrantLockTest("thread1");
        ReentrantLockTest test2 = new ReentrantLockTest("thread2");

        test1.start();
        test2.start();
        test1.join();
        test2.join();
        System.out.println(i);
    }
}




