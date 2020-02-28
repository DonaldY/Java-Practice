package com.donaldy.lock.future;

import java.util.concurrent.*;

/**
 * @author donald
 * @date 2020/2/27
 */
public class TestCompletableFutureSet {

    private final static int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();

    private final static ThreadPoolExecutor POOL_EXECUTOR = new ThreadPoolExecutor(AVAILABLE_PROCESSORS,
            AVAILABLE_PROCESSORS * 2, 1,
            TimeUnit.MINUTES, new LinkedBlockingDeque<>(5),
            new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<String> future = new CompletableFuture<>();

        POOL_EXECUTOR.execute(() -> {

            try {

                Thread.sleep(3000);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }

            System.out.println("------" + Thread.currentThread().getName() + " SET FUTURE RESULT ----");

            future.complete("hello, donald");
        });

        System.out.println("---main thread wait future result---");

        System.out.println(future.get());

        System.out.println("---main thread got future result---");

        POOL_EXECUTOR.shutdown();
    }

}
