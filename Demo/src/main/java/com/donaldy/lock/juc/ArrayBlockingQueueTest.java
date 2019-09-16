package com.donaldy.lock.juc;

import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockingQueueTest {

    private ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) {

        ArrayBlockingQueueTest test = new ArrayBlockingQueueTest();

        Consumer consumer = test.new Consumer();
        Producer producer = test.new Producer();

        consumer.start();
        producer.start();
    }

    class Consumer extends Thread {
        @Override
        public void run() {

            try {

                Integer num = queue.take();

                System.out.println(Thread.currentThread().getName() + " 拿到数字： " + num);

            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        }
    }

    class Producer extends Thread {

        @Override
        public void run() {

            try {

                int num = 1;

                queue.put(num);

                System.out.println(Thread.currentThread().getName()  + " 放入数字： " + num);

            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        }
    }
}
