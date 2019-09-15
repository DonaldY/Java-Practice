package com.donaldy.lock.aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch count = new CountDownLatch(3);

        Thread thread1 = new TranslateThread("1st content", count);
        Thread thread2 = new TranslateThread("2st content", count);
        Thread thread3 = new TranslateThread("3st content", count);

        thread1.start();
        thread2.start();
        thread3.start();

        count.await(10, TimeUnit.SECONDS);

        System.out.println("所有线程执行完成  ");
        // 给调用端返回翻译结果
    }
}

class TranslateThread extends Thread {
    private String content;
    private final CountDownLatch count;

    TranslateThread(String content, CountDownLatch count) {

        this.content = content;
        this.count = count;
    }

    @Override
    public void run() {
        // 在某种情况下, 执行翻译解析时, 抛出异常
        /*if (Math.random() > 0.5) {
            throw new RuntimeException("原文存在非法字符");
        }*/

        System.out.println(content + " 的翻译已经完成, 译文是...");
        count.countDown();
    }
}
