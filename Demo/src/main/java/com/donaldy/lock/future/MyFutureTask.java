package com.donaldy.lock.future;


import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class MyFutureTask<V> implements Future<V>,Runnable {
    private Callable<V> callable; //业务逻辑
    private boolean running = false ,done = false,cancel = false;// 业务逻辑执行状态
    private ReentrantLock lock ;//锁
    private V outcome;//结果

    public MyFutureTask(Callable<V> callable) {
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
    public V get() throws InterruptedException, ExecutionException {
        try {
            this.lock.lock();//先获取锁，获得后说明业务逻辑已经执行完毕
            return outcome;
        }finally{
            this.lock.unlock();
        }
    }

    @Override
    public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
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
            this.lock.lock(); // 启动线程，先上锁，防止get时直接返回
            running = true;
            try {
                outcome = callable.call(); // 业务逻辑
            } catch (Exception e) {
                e.printStackTrace();
            }
            done = true;
            running = false;
        }finally {
            this.lock.unlock(); // 解锁后get可获取
        }
    }
}
