package com.donaldy.thread.interrupt;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

public class BrokenPrimeProducer extends Thread {

    private final BlockingQueue<BigInteger> queue;

    private volatile boolean canclled = false;

    BrokenPrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            BigInteger p = BigInteger.ONE;
            while (!canclled) {
                queue.put(p = p.nextProbablePrime());
            }
        } catch (InterruptedException consumed) {}
    }

    public void cancel() {
        this.canclled = true;
    }
}
