package com.donaldy.thread;

public class InterruptedThread {

    static class MyThread implements Runnable {

        private int i = 0;
        private int j = 0;

        public void run() {

            synchronized (this) {

                ++i;
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ++j;
            }
        }

        public void print() {

            System.out.println("i = " + i + "  j = " + j);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        MyThread myThread = new MyThread();

        myThread.run();

        Thread.sleep(1000);

        Thread.currentThread().interrupt();

        myThread.print();
    }
}
