package com.donaldy.lock.deadlock;

/**
 * 顺序死锁
 */
public class LeftRightDeadLock {

    private final Object left = new Object();
    private final Object right = new Object();

    public void leftRight() {
        synchronized (left) {
            synchronized (right) {
                // doSomething
            }
        }
    }

    public void rightLeft() {
        synchronized (right) {
            synchronized (left) {
                // doSomething
            }
        }
    }
}
