package com.donaldy.thread.homework;

/**
 * @author donald
 * @date 2021/03/02
 */
public class PrintABCWithWaitNotify {
    private int state; // 状态
    private int times; // 次数
    private static final Object LOCK = new Object(); // 锁

    private PrintABCWithWaitNotify(int times) {
        this.times = times;
    }

    public static void main(String[] args) {
        // 打印 10 轮
        PrintABCWithWaitNotify printABC = new PrintABCWithWaitNotify(10);
        new Thread(() -> printABC.printLetter("A", 0), "A").start();
        new Thread(() -> printABC.printLetter("B", 1), "B").start();
        new Thread(() -> printABC.printLetter("C", 2), "C").start();
    }

    private void printLetter(String name, int targetState) {
        for (int i = 0; i < times; i++) {
            synchronized (LOCK) {
                while (state % 3 != targetState) {
                    try {
                        // 把当前线程等待
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                state++;
                System.out.print(name);
                // 唤醒其他线程
                LOCK.notifyAll();
            }
        }
    }
}
