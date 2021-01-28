package com.donaldy.lock.future;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CompletableFuture;

/**
 * @author donald
 * @date 2021/01/28
 */
public class TestCommonPool {

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 100; ++i) {

            int finalI = i;

            CompletableFuture.supplyAsync(() -> {

                System.out.println(currDateString() + " " + Thread.currentThread() + " 执行父任务-" + finalI);

                // 模拟耗时操作，确保 100 个任务先提交到线程池
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
                    System.out.println(currDateString() + " " + Thread.currentThread() + " 执行子任务-" + finalI);
                    return null;
                });

                CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
                    System.out.println(currDateString() + " " + Thread.currentThread() + " 执行子任务-" + finalI);
                    return null;
                });


                CompletableFuture.allOf(future1, future2).join();

                System.out.println(currDateString() + " " + Thread.currentThread() + " 执行父任务完成 父任务-" + finalI);

                return null;
            });
        }

        Thread.currentThread().join();
    }

    private static String currDateString() {

        Date currDate = new Date();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return format.format(currDate);
    }
}
