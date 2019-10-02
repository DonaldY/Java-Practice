package com.donaldy.thread.interrupt;

public class InterruptedThread {

    static class MyThread extends Thread {

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

        myThread.start();

        Thread.sleep(1000);

        myThread.interrupt();

        while (myThread.isAlive()) {

        }

        myThread.print();
    }
}
