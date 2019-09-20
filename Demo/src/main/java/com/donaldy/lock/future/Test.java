package com.donaldy.lock.future;

import java.util.concurrent.*;

public class Test {

    public static void main(String[] args) {

        Test test = new Test();

        Future<String> future = test.search("yyf");

        try {
            System.out.println("result : " + future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public Future<String> search(String prodName) {
        MyFutureTask<String> future = new MyFutureTask<String>(new Callable<String>() {
            @Override
            public String call()  {
                try {
                    System.out.println(String.format("searching %s",prodName));
                    Thread.sleep(3000);
                    return "hha";
                }catch(InterruptedException e){
                    System.out.println("search function is Interrupted!");
                }
                return null;
            }
        });
        new Thread(future).start();// 或提交到线程池
        return future;
    }
}
