package com.donaldy.lock.future;

import com.sun.xml.internal.ws.util.CompletedFuture;

import java.util.concurrent.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
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

    // 异步A 完成，激活异步B
    public static void thenRun() throws ExecutionException, InterruptedException {

        CompletableFuture<String> oneFuture = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {

                try {

                    Thread.sleep(2000);
                } catch (InterruptedException e) {

                    e.printStackTrace();
                }

                return "hello";
            }
        });

        // 在future上增加事件，当future计算完成后回调该事件，并返回新future
        CompletableFuture twoFuture = oneFuture.thenRun(new Runnable() {

            @Override
            public void run() {

                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName());
                System.out.println("---after oneFuture over doSomething---");
            }
        });

        System.out.println(twoFuture.get());
    }

    // then accept
    public static void thenAccept() {

        CompletableFuture<String> oneFuture = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {

                    e.printStackTrace();
                }

                return "hello";
            }
        });

        CompletableFuture twoFuture = oneFuture.thenAccept(new Consumer<String>() {
            @Override
            public void accept(String s) {

                try {

                    Thread.sleep(1000);
                } catch (Exception e) {

                    e.printStackTrace();
                }

                System.out.println("---after oneFuture over doSomething---" + s);
            }
        });
    }

    // then accept callback
    public static void thenAcceptAndCallback() throws ExecutionException, InterruptedException {

        CompletableFuture<String> oneFuture = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {

                try {

                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return "hello";
            }
        });

        CompletableFuture<String> twoFuture = oneFuture.thenApply(new Function<String, String>() {
            @Override
            public String apply(String s) {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return s + " donald.";
            }
        });

        System.out.println(twoFuture.get());
    }

    // when complete
    public static void whenComplete() throws InterruptedException {

        CompletableFuture<String> future = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return "hello, donald";
            }
        });

        future.whenComplete(new BiConsumer<String, Throwable>() {
            @Override
            public void accept(String s, Throwable throwable) {

                if (null == throwable) {

                    System.out.println(s);
                } else {

                    System.out.println(throwable.getLocalizedMessage());
                }
            }
        });

        // 挂起当前线程，等待异步任务执行完毕
        Thread.currentThread().join();
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
