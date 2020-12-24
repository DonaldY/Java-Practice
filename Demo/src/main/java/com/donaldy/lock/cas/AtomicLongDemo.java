package com.donaldy.lock.cas;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 描述： 在16个线程下使用AtomicLong
 *
 * @author donald
 * @date 2020/12/24
 */
public class AtomicLongDemo {

    public static void main(String[] args) throws InterruptedException {
        AtomicLong counter = new AtomicLong(0);
        ExecutorService service = Executors.newFixedThreadPool(16);
        for (int i = 0; i < 100; i++) {
            service.submit(new Task(counter));
        }

        Thread.sleep(2000);
        System.out.println(counter.get());
    }

    static class Task implements Runnable {

        private final AtomicLong counter;

        public Task(AtomicLong counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            counter.incrementAndGet();
        }
    }
}
