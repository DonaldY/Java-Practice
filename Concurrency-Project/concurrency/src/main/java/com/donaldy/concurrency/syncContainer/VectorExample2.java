package com.donaldy.concurrency.syncContainer;

import com.donaldy.concurrency.annoations.UnsafeThread;

import java.util.Vector;

/**
 * 运行会抛异常
 *
 * 同步容器，线程不安全
 */
@UnsafeThread
public class VectorExample2 {

    private static Vector<Integer> vector = new Vector<>();

    public static void main(String [] args) {
        for (int i = 0; i < 10; ++i) {
            vector.add(i);
        }

        Thread thread1 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < vector.size(); ++i) {
                    vector.remove(i);
                }
            }
        };

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < vector.size(); ++i) {
                    vector.get(i);
                }
            }
        };

        thread1.start();
        thread2.start();
    }
}
