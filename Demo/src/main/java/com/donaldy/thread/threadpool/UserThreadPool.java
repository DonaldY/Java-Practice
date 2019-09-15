package com.donaldy.thread.threadpool;

import java.util.concurrent.*;

public class UserThreadPool {

    public static void main(String[] args) {

        // 缓存队列设置固定长度为2, 为了快速触发 rejectHandler
        BlockingDeque<Runnable> blockingDeque = new LinkedBlockingDeque<>(2);

        // 假设外部任务线程的来源由机房1 和 机房2的混合调用
        UserThreadFactory f1 = new UserThreadFactory(" 第 1 机房 ");
        UserThreadFactory f2 = new UserThreadFactory(" 第 2 机房 ");

        UserRejectHandler handler = new UserRejectHandler();

        // 核心线程为1, 最大线程为2, 为了保证触发 rejectHandler
        ThreadPoolExecutor threadPoolFirst = new ThreadPoolExecutor(1, 2,
                60, TimeUnit.SECONDS, blockingDeque, f1, handler);

        // 利用第二个线程工厂实例创建第二个线程池
        ThreadPoolExecutor threadPoolSecond = new ThreadPoolExecutor(1, 2,
                60, TimeUnit.SECONDS, blockingDeque, f2, handler);

        // 创建 400 个任务线程
        Runnable task = new Task();
        for (int i = 0; i < 200; ++i) {
            threadPoolFirst.execute(task);
            threadPoolSecond.execute(task);
        }
    }
}
