package com.donaldy.thread.threadpool.demo;

public interface ThreadPool<Task extends Runnable> {

    void execute(Task task);

    void shutdown();

    void addWorkers(int num);

    void removeWorker(int num);

    int getTaskNum();
}