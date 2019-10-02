package com.donaldy.thread;

/**
 * 线程中断本质：一个线程被另外一个线程打断执行，一个线程被中断会引发中断异常
 */
public class InterruptedThreadTest {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(20000);
                System.out.println("开始休息。。。");
            } catch (InterruptedException e) {
                System.out.println("休息被中断了，于是开始学习。。。");
            }
        });

        thread.start();
        System.out.println(thread.getName() + "线程的中断状态" + thread.isInterrupted());
        Thread.sleep(2000);
        thread.interrupt();
        System.out.println(thread.getName() + "线程的中断状态" + thread.isInterrupted());
    }
}
