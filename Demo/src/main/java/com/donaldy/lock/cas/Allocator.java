package com.donaldy.lock.cas;

import java.util.List;

public class Allocator {

    private List<Object> als;

    // 申请资源
    synchronized void apply(Object from, Object to){

        while (als.contains(from) || als.contains(to)) {

            try {
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        als.add(from);
        als.add(to);
    }

    // 归还资源
    synchronized void free(Object from, Object to) {
        als.remove(from);
        als.remove(to);
        notifyAll();
    }
}
