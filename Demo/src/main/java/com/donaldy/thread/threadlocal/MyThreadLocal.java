package com.donaldy.thread.threadlocal;

import java.util.Random;

public class MyThreadLocal {

    public static void main(String[] args) {

        Task task = new Task();

        Thread[] tasks = new Thread[10];

        for(int i = 0 ;i < 10; i++) {

            tasks[i] = new Thread(task);
        }

        for(int i = 0; i < 10; i++) {

            tasks[i].start();
        }

    }

    static class Task implements Runnable{

        ThreadLocal<Float> local = new ThreadLocal<Float>();

        public void run() {
            Float process = new Random().nextFloat();

            local.set(process);

            try {
                Thread.sleep(new Random().nextInt(1000));

            } catch (InterruptedException ignored) {

            }

            System.out.println(Thread.currentThread().getName() +" "+local.get() +" "+ (local.get().equals(process)));
        }
    }
}
