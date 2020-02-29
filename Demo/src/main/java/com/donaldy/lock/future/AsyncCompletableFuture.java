package com.donaldy.lock.future;

import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * @author donald
 * @date 2020/2/28
 */
public class AsyncCompletableFuture {

    private final static int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();

    private final static ThreadPoolExecutor POOL_EXECUTOR = new ThreadPoolExecutor(AVAILABLE_PROCESSORS,
            AVAILABLE_PROCESSORS * 2, 1,
            TimeUnit.MINUTES, new LinkedBlockingDeque<>(5),
            new ThreadPoolExecutor.CallerRunsPolicy());

    public static void runAsync() throws ExecutionException, InterruptedException {

        CompletableFuture future = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {

                System.out.println(Thread.currentThread().getName() + " running...");

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + " over...");
            }
        }, POOL_EXECUTOR);
    }

    // 有返回值的异步执行
    public static void supplyAsync() throws ExecutionException, InterruptedException {

        CompletableFuture future = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {

                // sleep 2s
                try {

                    Thread.sleep(2000);
                } catch (InterruptedException e) {

                    e.printStackTrace();
                }

                return "hello, donald";
            }
        });

        System.out.println(future.get());
    }



    public static void main(String[] args) {

        for (int i = 0; i < 20; ++i) {

            try {
                runAsync();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        POOL_EXECUTOR.shutdown();
    }
}
