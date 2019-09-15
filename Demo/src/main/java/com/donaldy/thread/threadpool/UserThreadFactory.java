package com.donaldy.thread.threadpool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class UserThreadFactory implements ThreadFactory {

    private final String namePrefix;
    private final AtomicInteger nextId = new AtomicInteger(1);

    // 定义线程组名称，在使用 jstack 来排查线程问题时，非常有帮助
    UserThreadFactory(String whatFeatureOfGroup) {

        this.namePrefix = "UserThreadFactory's " + whatFeatureOfGroup + "-Worker-";
    }

    public Thread newThread(Runnable runnable) {

        String name = this.namePrefix + nextId.getAndIncrement();
        Thread thread = new Thread(null, runnable, name, 0);
        System.out.println(thread.getName());
        return thread;
    }
}

// 任务执行体
class Task implements Runnable {

    private final AtomicLong count = new AtomicLong(0L);

    public void run() {

        System.out.println("running_" + count.getAndIncrement());
    }
}
