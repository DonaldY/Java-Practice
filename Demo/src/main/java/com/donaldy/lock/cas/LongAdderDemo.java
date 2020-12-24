package com.donaldy.lock.cas;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;

/**
 * 描述： 在16个线程下使用LongAdder
 *
 * @author donald
 * @date 2020/12/24
 */
public class LongAdderDemo {

    public static void main(String[] args) throws InterruptedException {
        LongAdder counter = new LongAdder();
        ExecutorService service = Executors.newFixedThreadPool(16);
        for (int i = 0; i < 100; i++) {
            service.submit(new Task(counter));
        }

        Thread.sleep(2000);
        System.out.println(counter.sum());
    }
    static class Task implements Runnable {

        private final LongAdder counter;

        public Task(LongAdder counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            counter.increment();
        }
    }
}