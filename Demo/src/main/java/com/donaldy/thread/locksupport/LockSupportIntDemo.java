package com.donaldy.thread.locksupport;

import java.util.concurrent.locks.LockSupport;

public class LockSupportIntDemo {

    static ObjectThread t1 = new ObjectThread("t1");
    static ObjectThread t2 = new ObjectThread("t2");

    public static class ObjectThread extends Thread {

        public ObjectThread(String name) {
            super.setName(name);
        }

        @Override
        public void run() {
            synchronized (LockSupportIntDemo.class) {
                System.out.println("in " + getName());

                // 阻塞
                LockSupport.park();

                // 若为 true，则被中断
                if (Thread.interrupted()) {
                    System.out.println(getName() + " 被中断");
                }
            }
            System.out.println(getName() + "执行结束");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        t1.start();
        Thread.sleep(100);
        t2.start();
        t1.interrupt();
        LockSupport.unpark(t2);
    }
}
