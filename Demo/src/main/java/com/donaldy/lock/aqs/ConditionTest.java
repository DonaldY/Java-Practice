package com.donaldy.lock.aqs;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {

    final Lock lock = new ReentrantLock();
    final Condition condition = lock.newCondition();

    public static void main(String[] args) {

        ConditionTest conditionTest = new ConditionTest();

        Producer producer = conditionTest.new Producer();
        Consumer consumer = conditionTest.new Consumer();

        consumer.start();
        producer.start();
    }

    class Consumer extends Thread {
        @Override
        public void run() {

            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " 我在等一个新信号");
                condition.await();
            } catch (InterruptedException e) {

                e.printStackTrace();
            } finally {

                System.out.println(Thread.currentThread().getName() +"  拿到一个新信号");

                lock.unlock();
            }
        }
    }

    class Producer extends Thread {

        @Override
        public void run() {

            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " 发出一个信号");
                condition.signal();
            } finally {
                lock.unlock();
            }
        }
    }
}
