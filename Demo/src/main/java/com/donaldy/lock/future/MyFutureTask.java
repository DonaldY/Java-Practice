package com.donaldy.lock.future;


import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class MyFutureTask<V> implements Future<V>,Runnable {
    private Callable<V> callable;
    private boolean running = false;
    private boolean done = false;
    private boolean cancel = false;
    private ReentrantLock lock ;
    private V outcome;

    MyFutureTask(Callable<V> callable) {
        if(callable == null) {
            throw new NullPointerException("callable cannot be null!");
        }
        this.callable = callable;
        this.done = false;
        this.lock = new ReentrantLock();
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        callable = null;
        cancel = true;
        return true;
    }

    @Override
    public boolean isCancelled() {
        return cancel;
    }

    @Override
    public boolean isDone() {
        return done;
    }

    @Override
    public V get() {
        try {
            // 这里需要判断任务是否完成
            // 若没完成，则阻塞
            // 若完成，则返回
            this.lock.lock();
            return outcome;
        }finally{
            this.lock.unlock();
        }
    }

    @Override
    public V get(long timeout, TimeUnit unit) {
        try {
            this.lock.tryLock(timeout, unit);
            return outcome;
        }catch (InterruptedException e) {
            return null;
        }finally{
            this.lock.unlock();
        }
    }
    @Override
    public void run() {
        try {
            this.lock.lock();
            running = true;
            try {
                outcome = callable.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
            done = true;
            running = false;
        }finally {
            this.lock.unlock();
        }
    }
}
