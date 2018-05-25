package cn.donaldy.threadExample;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by DonaldY on 2018/5/24.
 */
public class Interruptible {

    public static void main(String[] args) {
        final ReentrantLock l1 = new ReentrantLock();
        final ReentrantLock l2 = new ReentrantLock();

        Thread t1 = new Thread() {
            public void run() {
                try {
                    l1.lockInterruptibly();
                    Thread.sleep(1000);
                    l2.lockInterruptibly();
                } catch (InterruptedException e) {
                    System.out.println("t1 interrupted");
                }
            }
        };
        
    }
    
}
